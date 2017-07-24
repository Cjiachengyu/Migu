package cn.eclassmate.qzy.service;

import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.viewmodel.TuiGuangModel;

import org.springframework.stereotype.Service;

@Service
public class TuiGuangService extends AbstractService
{
    private static final long serialVersionUID = -1882603606653618110L;

    public void addTuiGuangMoney(TuiGuangModel tgModel)
    {
        User user = tgModel.getUser();
        user.setTuiGuangStatus(1);
        userMapper.updateUser(user);

        changeUserMoney(tgModel, user.getUserId(), Cst.BillType.TUI_GUANG_BAG,
                Cst.Money.TUI_GUANG_MONEY, "领取新手红包，获得");
    }

}
