package cn.eclassmate.qzy.global;

import java.text.SimpleDateFormat;

public class Cst
{
    // 启动时需要初始化的参数
    // --------------------------------------------------------------------------------
    public static boolean DEBUG_MODE = false;
    public static String APP_PATH = null;       // "/var/webapp/tomcat-xxx/webapp/migu/";
    public static String BASE_PATH = null;      // "/var/webapp/file.war/migu/";
    public static String BASE_URL = null;       // "http://www.xxx.com/file/migu/"
    public static String DOMAIN_IP = null;      // "http://12.34.56.78"
    public static String DOMAIN_NAME = null;    // "http://www.xxx.com"

    // 其它常数
    // --------------------------------------------------------------------------------
    public static SimpleDateFormat SDF_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat SDF_MM_dd_HH_mm = new SimpleDateFormat("MM-dd HH:mm");
    public static SimpleDateFormat SDF_MM_dd = new SimpleDateFormat("MM-dd");

    public interface Money
    {
        // 得钱
        int USER_RIGISTER_MONEY = 1000;      // 用户注册默认给多少钱
        int TUI_GUANG_MONEY = 1000;          // 新手红包
        int ANSWER_RIGHT_BUT_LATE = 1;       // 答对了，但没来得及，奖励多少钱
        int SHUATI_ALL_RIGHT = 10;           // 刷题全部答对了
        int SHUATI_FOUR_RIGHT = 5;           // 刷题答对4题
        int SHUATI_THREE_RIGHT = 2;          // 刷题答对3题
        int GOT_STAR_ONE_MONEY = 50;         // 获取第一颗星星
        int GOT_STAR_TWO_MONEY = 200;        // 获取第二颗星星
        int GOT_STAR_THREE_MONEY = 750;      // 获取第三颗星星
        int GOT_MOON_ONE_MONEY = 150;        // 获取第一颗月亮
        int GOT_MOON_TWO_MONEY = 600;        // 获取第二颗月亮
        int GOT_MOON_THREE_MONEY = 2250;     // 获取第三颗月亮

        // 扣钱
        int PAY_ANALYSIS_PER_QUE = 10;      // 查看解析
        int SHOW_GRAB_BAG = 1;              // 显摆抢到红包
        int SHOW_PRIZE = 1;                 // 显摆奖品
        int SHOW_GIFT_SEND = 1;             // 显摆送出礼物
        int SHOW_GIFT_RECEIVE = 1;          // 显摆收到礼物
        int CHEAT_BAG_PER_QUE = 100;        // 答错题目作弊
        int LOTTERY = 10000;        // 答错题目作弊
    }

    public interface BillType
    {
        int CREATE_BAG = 1;     // 塞红包
        int SPEND = 2;          // 显摆，嘲笑，赞美，兑换奖品，查看解析
        int PUNISH = 3;         // 答错扣钱
        int LOTTERY = 4;         // 抽奖

        int SPEND_OR_GET_SEP = 50;   // 花费还是得到的分界

        int SYSTEM_GIVE = 51;   // 系统赠送(注册的时候)
        int BAG_RETURN = 52;    // 发红包返还的钱
        int GOT_BAG = 53;       // 抢到红包
        int SHUATI_RESULT = 54;  // 刷题结果
        int GOT_STAR = 55;      // 获取星星
        int GOT_MOON = 56;      // 获取月亮
        int TIMEOUT_RETURN_BACK = 57;      // 红包过期返回
        int TUI_GUANG_BAG = 58;      // 新手红包
        int LOTTERY_RESULT = 59;      // 新手红包
    }

    public interface UserBagChanceType
    {
        int HAS_CHANCE = 1;     // 有机会
        int TOO_LATE = 2;      // 来晚了，已经被抢完
        int SUBMITTED = 3;      // 已经抢过了
        int TIMEOUT = 4;        // 已经过期
    }

    public interface UserBagStatus
    {
        // 提交前
        int BEGIN_GRAB = 0;

        // 抢红包结果
        int FINISH_GRAB_RIGHT_WIN = 1;
        int FINISH_GRAB_RIGHT_SLOW = 2;
        int FINISH_GRAB_WRONG = 3;

        // 练一练结果
        int FINISH_PRACTICE_RIGHT = 4;
        int FINISH_PRACTICE_WRONG = 7;

        // 刷题结果
        int FINISH_SHUATI_RIGHT = 5;
        int FINISH_SHUATI_WRONG = 6;
    }

    public interface Email
    {
        String MAIL_HOST = "smtp.exmail.qq.com";
        String MAIL_ACCOUNT = "reg@eclassmate.cn";
        String MAIL_PASSWORD = "newpass123";
        String EMAIL_PATTERN = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    }

    public interface Gender
    {
        int UNKNOWN = 0;
        int MALE = 1;
        int FEMALE = 2;
    }

    public interface EditorPrivilege
    {
        int CAN_OPERATE_QUE = 1;    // 0b00001
        int CAN_OPERATE_PRIZE = 2;  // 0b00010
    }

    public interface QueType
    {
        int OBJ_SINGLE = 1;
        int OBJ_MULTIPULE = 2;

        String[] StrArr = { "未知题型", "单选题", "多选题" };
    }

    public interface ShowType
    {
        int SHOW_GRAB_BAG = 1;
        int SHOW_PRIZE = 2;
        int SHOW_GIFT = 3;
    }

    public interface ShowGrabBagType
    {
        int SHOW_GRAB_BAG_MY = 1;
        int SHOW_GRAB_BAG_LAUGH = 2;
        int SHOW_GRAB_BAG_PRAISE = 3;
    }

    public interface ShowGiftType
    {
        int SHOW_GIFT_SEND = 1;
        int SHOW_GIFT_RECEIVE = 2;
    }

    public interface BagStatus
    {
        int NEW_BAG = 1;
        int ALL_GRABBED = 2;
        int TIMEOUT = 3;
    }

    public interface BagCategory
    {
        int HONGBAO = 1;     // 红包
        int SHUATI = 2;     // 刷题
    }

    public interface BagType
    {
        int PINSHOUQI = 1;     // 拼手气红包
    }

    public interface UserStarStatus
    {
        int CANNOT_GET = 1;     // 没资格
        int CAN_GET = 2;        // 有资格，没领取
        int ALREADY_GOT = 3;    // 已经领取
    }

    public interface Weixin
    {
        String RESP_MESSAGE_TYPE_TEXT = "text";
        String RESP_MESSAGE_TYPE_MUSIC = "music";
        String RESP_MESSAGE_TYPE_NEWS = "news";

        String REQ_MESSAGE_TYPE_TEXT = "text";
        String REQ_MESSAGE_TYPE_IMAGE = "image";
        String REQ_MESSAGE_TYPE_LINK = "link";
        String REQ_MESSAGE_TYPE_LOCATION = "location";
        String REQ_MESSAGE_TYPE_VOICE = "voice";
        String REQ_MESSAGE_TYPE_EVENT = "event";

        String EVENT_TYPE_SUBSCRIBE = "subscribe";
        String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
        String EVENT_TYPE_CLICK = "click";
    }

    public interface WeixinAuthType
    {
        int FORCE_FULL = 1;
        int ENSURE_FULL = 2;
        int SIMPLE = 3;
    }

    public interface MobileErrorCode
    {
        int NoError = 0;
        int ServerException = 10001;
        int InvalidParameter = 10002;
        int AccountNotExit = 10003;
        // int PasswordNotMatch = 10004;
    }

}
