package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Prize;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.viewmodel.EditorPrizeListModel;
import cn.eclassmate.qzy.viewmodel.EditorPrizeEditModel;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorService extends AbstractService
{
    private static final long serialVersionUID = -7416505185916253593L;

    public void initEditorPrizeListModel(EditorPrizeListModel viewModel)
    {

        List<Prize> prizeList = bagMapper.getprizeList();
        viewModel.setPrizeList(prizeList);
    }

    public void deletePrize(EditorPrizeListModel viewModel, int prizeId)
    {
        bagMapper.deletePrize(prizeId);
        List<Prize> prizeList = viewModel.getPrizeList();
        for (int i = prizeList.size() - 1; i >= 0; i--)
        {
            if (prizeList.get(i).getPrizeId() == prizeId)
            {
                prizeList.remove(i);
            }

        }

        viewModel.setPrizeList(prizeList);
    }

    public void modifyPrize(EditorPrizeEditModel viewModel, int prizeId)
    {
        Prize prize = bagMapper.getPrize(prizeId);
        viewModel.setPrize(prize);

        viewModel.setOperationType(EditorPrizeEditModel.OPER_TYPE_MODIFY);

    }

    public Prize savePrice(EditorPrizeEditModel viewModel, String prizeName, String prizeMoney, String prizeImage)
    {
        Prize prize;
        if (viewModel.getOperationType() == 1)
        {
            prize = new Prize();
        }
        else
        {
            prize = viewModel.getPrize();
        }
        prize.setPrizeName(prizeName);
        prize.setPrizeMoney(Integer.parseInt(prizeMoney));
        prize.setPrizeImage(prizeImage.replace(Cst.BASE_URL, ""));

        if (viewModel.getOperationType() == 1)
        {
            bagMapper.insertPrize(prize);
        }
        else
        {
            bagMapper.updatePrize(prize);
        }

        viewModel.setPrize(null);
        viewModel.setOperationType(EditorPrizeEditModel.OPER_TYPE_CREATE);

        return prize;
    }

}
