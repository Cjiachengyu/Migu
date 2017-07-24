package cn.eclassmate.qzy.exception;

public class WeixinAuthException extends Exception
{
    private static final long serialVersionUID = -7133363981324676106L;

    public WeixinAuthException()
    {
        super();
    }

    public WeixinAuthException(String message)
    {
        super(message);
    }
}
