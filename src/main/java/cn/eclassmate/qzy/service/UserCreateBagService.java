package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.Bag;
import cn.eclassmate.qzy.domain.BagQue;
import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.domain.Zsd1;
import cn.eclassmate.qzy.domain.Zsd2;
import cn.eclassmate.qzy.domain.ZsdCatalog;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.domain.Zsl;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.viewmodel.UserCreateBagModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserCreateBagService extends AbstractService
{
    private static final long serialVersionUID = -4055813186717370244L;

    public void initUcbModel(UserCreateBagModel ucbModel)
    {
        refreshUserMoney(ucbModel);

        Bag bag = new Bag();
        ucbModel.setBag(bag);

        List<Zsl> zslList = queMapper.getAllZslWithZsd();
        ucbModel.setZslList(zslList);
    }

    public void selectQueListForBag(UserCreateBagModel ucbModel, int zskId, int zsd1Id, int zsd2Id, int queCount)
    {
        List<Que> queList = getRandomQueList(ucbModel.getUser().getUserId(), zskId, zsd1Id, zsd2Id, queCount);
        ucbModel.setQueList(queList);

        Bag bag = ucbModel.getBag();

        //从学习报告点击星星后刷题，到这边bag为空
        if (bag == null)
        {
            bag = new Bag();
        }
        bag.setZskId(zskId);
        bag.setZsd1Id(zsd1Id);
        bag.setZsd2Id(zsd2Id);

        setBagZsdCatalog(ucbModel, zskId, zsd1Id, zsd2Id, bag);
        ucbModel.setBag(bag);
    }

    public void setZhuanFaBag(UserCreateBagModel ucbModel, int bagId)
    {
        Bag oldBag = bagMapper.getBagForUserGrabBag(bagId);

        List<Que> queList = oldBag.getQueList();
        ucbModel.setQueList(queList);

        Bag bag = ucbModel.getBag();
        if (bag == null)
        {
            bag = new Bag();
            ucbModel.setBag(bag);
        }

        bag.setZskId(oldBag.getZskId());
        bag.setZsd1Id(oldBag.getZsd1Id());
        bag.setZsd2Id(oldBag.getZsd2Id());
        setBagZsdCatalog(ucbModel, oldBag.getZskId(), oldBag.getZsd1Id(), oldBag.getZsd2Id(), bag);
    }

    @Transactional
    public void setZhuanFaBag2(UserCreateBagModel ucbModel, int bagId)
    {
        Bag oldBag = bagMapper.getBagForUserGrabBag(bagId);

        List<Que> queList = oldBag.getQueList();
        ucbModel.setQueList(queList);

        Bag bag = new Bag();
        ucbModel.setBag(bag);

        bag.setZskId(oldBag.getZskId());
        bag.setZsd1Id(oldBag.getZsd1Id());
        bag.setZsd2Id(oldBag.getZsd2Id());
        setBagZsdCatalog(ucbModel, oldBag.getZskId(), oldBag.getZsd1Id(), oldBag.getZsd2Id(), bag);

        doCreateBag(ucbModel, oldBag.getBagCount(), oldBag.getBagMoney(), oldBag.getBagMsg(),
                oldBag.getBagType(), Cst.BagCategory.HONGBAO);
    }

    @Transactional
    public void doCreateBag(UserCreateBagModel ucbModel, int bagCount, int bagMoney, String bagMsg, int bagType,
            int bagCategory)
    {
        User user = ucbModel.getUser();

        Bag bag = ucbModel.getBag();
        bag.setCreatorId(user.getUserId());
        bag.setCreateTime(Utl.currentSeconds());
        bag.setBagType(bagType);
        bag.setBagCount(bagCount);
        bag.setBagMoney(bagMoney);
        bag.setBagMsg(Utl.validateUtf8(bagMsg));
        bag.setBagCategory(bagCategory);
        bag.setBagStatus(Cst.BagStatus.NEW_BAG);
        bagMapper.insertBag(bag);

        List<Que> queList = ucbModel.getQueList();
        List<BagQue> bagQueList = new ArrayList<BagQue>();
        for (Que que : queList)
        {
            BagQue bagQue = new BagQue();
            bagQue.setBagId(bag.getBagId());
            bagQue.setQueId(que.getQueId());
            bagQueList.add(bagQue);
        }
        bagMapper.insertBagQue(bagQueList);

        if (bagCategory == Cst.BagCategory.HONGBAO)
        {
            changeUserMoney(ucbModel, user.getUserId(), Cst.BillType.CREATE_BAG, bagMoney, "发出一个红包，塞了");
            ucbModel.getUser().setTodayCreatedBagCount(ucbModel.getUser().getTodayCreatedBagCount() + 1);
        }
    }

    public void recordCreateBagZskId(User user, int zskId)
    {
        user.setCreateBagZskId(zskId);
        userMapper.updateUser(user);
    }

    // private
    // --------------------------------------------------------------------------------
    private List<Que> getRandomQueList(int userId, int zskId, int zsd1Id, int zsd2Id, int queCount)
    {
        int allQueCount = queMapper.getRandomQueToCreateBagCount(userId, zskId, zsd1Id, zsd2Id);
        if (allQueCount < queCount)
        {
            userId = 0;
            allQueCount = queMapper.getRandomQueToCreateBagCount(userId, zskId, zsd1Id, zsd2Id);
        }

        if (allQueCount < queCount)
        {
            zsd2Id = 0;
            allQueCount = queMapper.getRandomQueToCreateBagCount(userId, zskId, zsd1Id, zsd2Id);
        }

        if (allQueCount < queCount)
        {
            zsd1Id = 0;
            allQueCount = queMapper.getRandomQueToCreateBagCount(userId, zskId, zsd1Id, zsd2Id);
        }

        if (allQueCount < queCount)
        {
            zskId = 0;
            allQueCount = queMapper.getRandomQueToCreateBagCount(userId, zskId, zsd1Id, zsd2Id);
        }

        List<Que> queList = new ArrayList<Que>();
        Random rand = new Random();
        List<Integer> generatedIndexList = new ArrayList<Integer>();
        while (queList.size() < queCount)
        {
            int selectIndex = rand.nextInt(allQueCount);
            while (generatedIndexList.contains(selectIndex))
            {
                selectIndex = rand.nextInt(allQueCount);
            }
            generatedIndexList.add(selectIndex);
            Que que = queMapper.getRandomQueToCreateBag(userId, zskId, zsd1Id, zsd2Id, selectIndex);

            if (que != null)
            {
                boolean dupQue = false;
                for (Que tmpQue : queList)
                {
                    if (tmpQue.getQueId() == que.getQueId())
                    {
                        dupQue = true;
                        continue;
                    }
                }

                if (!dupQue)
                {
                    queList.add(que);
                }
            }
        }

        return queList;
    }

    private void setBagZsdCatalog(UserCreateBagModel ucbModel, int zskId, int zsd1Id, int zsd2Id, Bag bag)
    {
        bag.setZsdCatalog(new ZsdCatalog());
        if (ucbModel.getZslList() == null)
        {
            ucbModel.setZslList(queMapper.getAllZslWithZsd());
        }
        for (Zsl zsl : ucbModel.getZslList())
        {
            for (Zsk zsk : zsl.getZskList())
            {
                if (zsk.getZskId() == zskId)
                {
                    bag.getZsdCatalog().setZskId(zskId);
                    bag.getZsdCatalog().setZskName(zsk.getZskName());
                    for (Zsd1 zsd1 : zsk.getZsd1List())
                    {
                        if (zsd1.getZsd1Id() == zsd1Id)
                        {
                            bag.getZsdCatalog().setZsd1Id(zsd1.getZsd1Id());
                            bag.getZsdCatalog().setZsd1Name(zsd1.getZsd1Name());
                            for (Zsd2 zsd2 : zsd1.getZsd2List())
                            {
                                if (zsd2.getZsd2Id() == zsd2Id)
                                {
                                    bag.getZsdCatalog().setZsd2Id(zsd2.getZsd2Id());
                                    bag.getZsdCatalog().setZsd2Name(zsd2.getZsd2Name());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
