package cn.eclassmate.qzy.boot;

import cn.eclassmate.qzy.domain.Editor;
import cn.eclassmate.qzy.domain.RequestRecord;
import cn.eclassmate.qzy.domain.User;
import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;
import cn.eclassmate.qzy.service.SystemService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.text.DecimalFormat;

@Component
public class LoggingFilter implements Filter
{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            putThreadContextVariables(httpRequest);

            if (Cst.DEBUG_MODE)
            {
                httpRequest.setAttribute("resVersion", System.currentTimeMillis());
            }

            detectClient(httpRequest);

            long enterTime = System.currentTimeMillis();
            logger.info("enter, " + enterTime + ", " + Utl.getRequestParamsString(httpRequest, "&") + ", "
                    + Utl.getRequestHeadersString(httpRequest, "&"));
            chain.doFilter(request, response);
            long leaveTime = System.currentTimeMillis();
            logger.debug("leave, " + enterTime + ", " + leaveTime + ", " + (leaveTime - enterTime));

            insertRequestRecord(httpRequest, enterTime, leaveTime);
        }
        finally
        {
            ThreadContext.clearAll();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        ServletContext sc = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        systemService = ctx.getBean(SystemService.class);
    }

    @Override
    public void destroy()
    {

    }

    // private
    // --------------------------------------------------------------------------------
    SystemService systemService;

    private void putThreadContextVariables(HttpServletRequest httpRequest)
    {
        String dstUrl = httpRequest.getRequestURL().toString();
        int indexOfQzy = dstUrl.indexOf("/migu/");
        if (indexOfQzy >= 0)
        {
            dstUrl = dstUrl.substring(indexOfQzy);
        }
        ThreadContext.put("dst_url", dstUrl);

        String srcIp = httpRequest.getHeader("x-forwarded-for");
        if (srcIp == null)
        {
            srcIp = httpRequest.getRemoteAddr();
        }
        ThreadContext.put("src_ip", srcIp);

        String threadId = (new DecimalFormat("00000").format(Thread.currentThread().getId()));
        ThreadContext.put("thread_id", threadId);

        User user = (User) httpRequest.getSession().getAttribute("user");
        if (user != null)
        {
            ThreadContext.put("userid", String.valueOf(user.getUserId()));
            ThreadContext.put("username", user.getUserName());
        }
        else
        {
            Editor editor = (Editor) httpRequest.getSession().getAttribute("editor");
            if (editor != null)
            {
                ThreadContext.put("userid", String.valueOf(editor.getEditorId()));
                ThreadContext.put("username", editor.getEditorName());
            }
            else
            {
                ThreadContext.put("userid", "null");
                ThreadContext.put("username", "null");
            }
        }
    }

    private void insertRequestRecord(HttpServletRequest httpRequest, long enterTime, long leaveTime)
    {
        logger.info("enter insertRequestRecord");
        String params = Utl.getRequestParamsString(httpRequest, "\n");
        String headers = Utl.getRequestHeadersString(httpRequest, "\n");

        String userIdStr = ThreadContext.get("userid");
        int userId = 0;
        if (!"null".equals(userIdStr))
        {
            userId = Integer.parseInt(userIdStr);
        }

        // 创建RequestRecord实体类
        RequestRecord requestRecord = new RequestRecord();
        requestRecord.setEnterTime(enterTime);
        requestRecord.setLeaveTime(leaveTime);
        requestRecord.setConsumeTime(leaveTime - enterTime);
        requestRecord.setUserId(userId);
        requestRecord.setFromIp(ThreadContext.get("src_ip"));
        requestRecord.setRequestUrl(ThreadContext.get("dst_url"));
        requestRecord.setRequestParam(params);
        requestRecord.setRequestHeader(headers);

        systemService.insertRequestRecord(requestRecord);

        logger.info("leave insertRequestRecord");
    }

    private void detectClient(HttpServletRequest httpRequest)
    {
        String userAgent = httpRequest.getHeader("User-Agent");
        if (Utl.stringEmpty(userAgent))
        {
            userAgent = httpRequest.getHeader("user-agent");
        }
        if (Utl.stringEmpty(userAgent))
        {
            userAgent = httpRequest.getHeader("USER-AGENT");
        }
        if (Utl.stringEmpty(userAgent))
        {
            httpRequest.setAttribute("isInvalidClient", true);
            return;
        }
        userAgent = userAgent.toLowerCase();

        boolean isFromWeixin = userAgent.contains("micromessenger");
        httpRequest.setAttribute("isFromWeixin", isFromWeixin);

        boolean isFromQQ = userAgent.contains("qq") && !isFromWeixin;
        httpRequest.setAttribute("isFromQQ", isFromQQ);

        boolean isFromClientAndroid = userAgent.contains("qzyandroid");
        httpRequest.setAttribute("isFromClientAndroid", isFromClientAndroid);

        boolean isFromClientIOS = userAgent.contains("qzyios");
        httpRequest.setAttribute("isFromClientIOS", isFromClientIOS);

        boolean isFromClientWP = userAgent.contains("qzywp");
        httpRequest.setAttribute("isFromClientWP", isFromClientWP);

        boolean isFromClient = isFromClientAndroid || isFromClientIOS || isFromClientWP;
        httpRequest.setAttribute("isFromClient", isFromClient);

        if (Cst.DEBUG_MODE)
        {
            httpRequest.setAttribute("isInvalidClient", false);
        }
        else
        {
            boolean isInvalidClient = !isFromWeixin && !isFromQQ && !isFromClient;
            httpRequest.setAttribute("isInvalidClient", isInvalidClient);
        }
    }

}
