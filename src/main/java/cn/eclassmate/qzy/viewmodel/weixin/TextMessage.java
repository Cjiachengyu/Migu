package cn.eclassmate.qzy.viewmodel.weixin;

public class TextMessage extends BaseRespMessage
{
    private static final long serialVersionUID = 5475726070445647507L;

    //回复消息的内容
    private String Content;

    public String getContent()
    {
        return Content;
    }

    public void setContent(String content)
    {
        Content = content;
    }
}
