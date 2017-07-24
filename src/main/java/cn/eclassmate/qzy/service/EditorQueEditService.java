package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.QueChoice;
import cn.eclassmate.qzy.domain.QueZsd;
import cn.eclassmate.qzy.domain.Zsd1;
import cn.eclassmate.qzy.domain.Zsd2;
import cn.eclassmate.qzy.domain.ZsdCatalog;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.EditorQueEditModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EditorQueEditService extends AbstractService
{
    private static final long serialVersionUID = -7416505185916253593L;
    private static final Logger logger = LogManager.getLogger();

    public void initQueEditCreate(EditorQueEditModel viewModel)
    {
        List<Zsk> zskList = queMapper.getAllZskWithZsd();
        viewModel.setZskList(zskList);
        viewModel.setOperationType(EditorQueEditModel.OPER_TYPE_CREATE);
    }

    public void initQueEditModify(EditorQueEditModel viewModel, int queId)
    {
        List<Zsk> zskList = queMapper.getAllZskWithZsd();

        Que que = queMapper.getQue(queId);

        List<QueZsd> queZsdList = queMapper.getQueZsd(queId);

        viewModel.setZskList(zskList);
        viewModel.setQue(que);
        viewModel.setQueZsdList(queZsdList);
        viewModel.setOperationType(EditorQueEditModel.OPER_TYPE_MODIFY);
    }

    public void initQueEditSaveAs(EditorQueEditModel viewModel, int queId)
    {
        List<Zsk> zskList = queMapper.getAllZskWithZsd();

        Que que = queMapper.getQue(queId);

        List<QueZsd> queZsdList = queMapper.getQueZsd(queId);

        viewModel.setZskList(zskList);
        viewModel.setQue(que);
        viewModel.setQueZsdList(queZsdList);
        viewModel.setOperationType(EditorQueEditModel.OPER_TYPE_SAVE_AS);
    }

    public void doCreateQue(EditorQueEditModel viewModel, String source, String kaodian, String zsdValue,
            String contentHtml, String analysisHtml, String answerHtml, Map<Integer, String> queChoiceHtmlMap,
            int answerNum, String imageSrcList) throws IOException
    {
        try
        {
            String[] imageSrcArray = imageSrcList.split("%");
            for (int i = 0; i < imageSrcArray.length; i++)
            {
                String imageSrc = imageSrcArray[i];
                if (Utl.stringEmpty(imageSrc))
                {
                    continue;
                }

                String relativeDir = "question/image/" + Utl.getTimeString("yyyyMMdd") + "/";
                String imageFileDir = Cst.BASE_PATH + relativeDir;
                Utl.makeSureDirExists(imageFileDir);

                String imageFileName = "question_" + Utl.getNextLongId() + "." + getWebImageSuffix(imageSrc);
                String imageFilePath = imageFileDir + imageFileName;
                String newImageSrc = Cst.BASE_URL + relativeDir + imageFileName;

                // String umEditorUpload= "/migu/resources/thirdparty/umeditor/jsp/upload/20150612/33811434080246900.gif";
                String umEditorUpload = "/resources/thirdparty/umeditor/jsp/upload/";

                if (imageSrc.startsWith(Cst.BASE_URL + "question/image/"))
                {
                    continue; // 已经存入本地
                }
                else if (imageSrc.contains(umEditorUpload))
                {
                    String umEditorImagePath = Cst.APP_PATH + imageSrc.substring(imageSrc.indexOf(umEditorUpload));
                    Utl.copyFile(umEditorImagePath, imageFilePath);
                }
                else
                {
                    saveWebFileToLocal(imageSrc, imageFilePath);
                }

                contentHtml = contentHtml.replace(imageSrc, newImageSrc);
                analysisHtml = analysisHtml.replace(imageSrc, newImageSrc);
                answerHtml = answerHtml.replace(imageSrc, newImageSrc);
                for (int key : queChoiceHtmlMap.keySet())
                {
                    queChoiceHtmlMap.put(key, queChoiceHtmlMap.get(key).replace(imageSrc, newImageSrc));
                }
            }
        }
        catch (Exception e)
        {
            logger.warn(e);
            e.printStackTrace();
        }

        doEditQue(viewModel, source, kaodian, zsdValue, contentHtml, analysisHtml, answerHtml, queChoiceHtmlMap,
                answerNum);
    }

    // transactional
    // --------------------------------------------------------------------------------
    @Transactional
    public void doEditQue(EditorQueEditModel viewModel, String source, String kaodian, String zsdValue,
            String contentHtml, String analysisHtml, String answerHtml, Map<Integer, String> queChoiceHtmlMap,
            int answerNum)
    {
        Que que;
        if (viewModel.getOperationType() == EditorQueEditModel.OPER_TYPE_MODIFY) //编辑题目
        {
            que = viewModel.getQue();
            que.setCreatorId(que.getCreatorId());
            que.setCreateTime(que.getCreateTime());

            queMapper.updateQueFeedBackStatus(que.getQueId());
        }
        else
        {
            que = new Que();
            que.setCreatorId(viewModel.getEditor().getEditorId());
            que.setCreateTime(Utl.currentSeconds());
        }

        //初始化que
        que.setQueType(1);
        que.setDifficulty(3);
        que.setSource(source);
        que.setKaodian(kaodian);
        que.setUpdaterId(viewModel.getEditor().getEditorId());
        que.setUpdateTime(Utl.currentSeconds());
        que.setContentHtml(contentHtml);
        que.setAnalysisHtml(analysisHtml);
        que.setAnswerHtml(answerHtml);
        if (viewModel.getEditor().getIsChecker())
        {
            que.setQueStatus(0);
        }
        else
        {
            que.setQueStatus(2);
        }

        if (viewModel.getOperationType() == EditorQueEditModel.OPER_TYPE_MODIFY)
        {
            queMapper.updateQue(que);//更新que
        }
        else
        {
            queMapper.insertQue(que);//插入que
        }

        //初始化queChoiceList
        List<QueChoice> queChoiceList = new ArrayList<QueChoice>();
        for (int queChoiceNum : queChoiceHtmlMap.keySet())
        {
            QueChoice queChoice = new QueChoice();
            queChoice.setQueId(que.getQueId());
            queChoice.setQueChoiceHtml(queChoiceHtmlMap.get(queChoiceNum));
            if (answerNum == queChoiceNum)
            {
                queChoice.setIsRightAnswer(true);
            }
            queChoiceList.add(queChoice);
        }

        //初始化queZsdList
        String[] zsdArr = zsdValue.substring(1, zsdValue.length() - 1).replaceAll("\"", "").split(",");

        List<QueZsd> queZsdList = new ArrayList<QueZsd>();
        for (int i = 0; i < zsdArr.length; i++)
        {
            String[] tempArray = zsdArr[i].split("_");

            QueZsd queZsd = new QueZsd();
            queZsd.setQueId(que.getQueId());
            queZsd.setZskId(Integer.parseInt(tempArray[0]));
            queZsd.setZsd1Id(Integer.parseInt(tempArray[1]));
            queZsd.setZsd2Id(Integer.parseInt(tempArray[2]));

            queZsdList.add(queZsd);
        }

        if (viewModel.getOperationType() == EditorQueEditModel.OPER_TYPE_MODIFY)
        {
            for (QueChoice queChoice : viewModel.getQue().getQueChoiceList())
            {
                queMapper.deleteQueChoice(queChoice);//删除原来的queChoice
            }

            for (QueZsd queZsd : viewModel.getQueZsdList())
            {
                queMapper.deleteQueZsd(queZsd);//删除原来的queZsd
            }
        }
        queMapper.insertQueChoiceList(queChoiceList);//插入新的queChoice
        queMapper.insertQueZsd(queZsdList);//插入新的queZsd

        que.setZsdCatalogList(buildZsdCatalogList(viewModel, queZsdList));
        que.setQueChoiceList(queChoiceList);
        viewModel.setLastQue(que);
    }

    // private
    // --------------------------------------------------------------------------------
    private List<ZsdCatalog> buildZsdCatalogList(EditorQueEditModel viewModel, List<QueZsd> queZsdList)
    {
        List<ZsdCatalog> zsdCatalogList = new ArrayList<ZsdCatalog>();
        for (QueZsd queZsd : queZsdList)
        {
            ZsdCatalog zsdCatalog = new ZsdCatalog();

            for (Zsk zsk : viewModel.getZskList())
            {
                if (zsk.getZskId() == queZsd.getZskId())
                {
                    // 找到zsk了
                    zsdCatalog.setZskName(zsk.getZskName());
                    zsdCatalog.setZskId(zsk.getZskId());

                    for (Zsd1 zsd1 : zsk.getZsd1List())
                    {
                        if (zsd1.getZsd1Id() == queZsd.getZsd1Id())
                        {
                            // 找到zsd1了
                            zsdCatalog.setZsd1Name(zsd1.getZsd1Name());
                            zsdCatalog.setZsd1Id(zsd1.getZsd1Id());

                            for (Zsd2 zsd2 : zsd1.getZsd2List())
                            {
                                if (zsd2.getZsd2Id() == queZsd.getZsd2Id())
                                {
                                    // 找到zsd2了
                                    zsdCatalog.setZsd2Name(zsd2.getZsd2Name());
                                    zsdCatalog.setZsd2Id(zsd2.getZsd2Id());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            zsdCatalogList.add(zsdCatalog);
        }

        return zsdCatalogList;
    }

    private String getWebImageSuffix(String webImagePath)
    {
        String webImageType = Utl.getFileSuffix(webImagePath);

        List<String> imageTypes = new ArrayList<String>();
        imageTypes.add("bmp");
        imageTypes.add("png");
        imageTypes.add("gif");
        imageTypes.add("jpg");
        imageTypes.add("jpeg");
        imageTypes.add("pjpeg");

        if (!imageTypes.contains(webImageType))
        {
            webImageType = "img";
        }
        return webImageType;
    }

    private void saveWebFileToLocal(String webImageUrl, String localImagePath) throws IOException
    {
        InputStream is = null;
        OutputStream os = null;
        try
        {
            URL url = new URL(webImageUrl);
            URLConnection conn = url.openConnection();
            is = conn.getInputStream();
            Utl.writeFile(localImagePath, is);
        }
        finally
        {
            Utl.safeClose(is);
            Utl.safeClose(os);
        }
    }

    public void resetoperationType(EditorQueEditModel viewModel)
    {
        //更新viewModel里的que和queZsdList
        viewModel.setOperationType(EditorQueEditModel.OPER_TYPE_CREATE);
        viewModel.setQue(null);
        viewModel.setQueZsdList(null);
    }

}
