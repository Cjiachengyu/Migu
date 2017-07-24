package cn.eclassmate.qzy.boot;

import cn.eclassmate.qzy.global.Cst;
import cn.eclassmate.qzy.global.Utl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import java.io.File;
import java.util.Properties;

public class ApplicationBootstrap implements WebApplicationInitializer
{
    public static Logger logger = LogManager.getLogger();

    private static final String[] POSSIBLE_FILE_PATHS = {
            "/var/webapp/file.war/migu/",
            "D:\\Software\\Tomcat\\host_root\\qzy\\",
            "C:\\var\\webapp\\file.war\\qzy\\",
            "D:\\var\\webapp\\file.war\\qzy\\",
            "F:\\var\\webapp\\file.war\\qzy\\",
            "G:\\var\\webapp\\file.war\\qzy\\",
    };

    @Override
    public void onStartup(ServletContext container) throws ServletException
    {
        container.setAttribute("resRoot", "/migu/resources");
        container.setAttribute("resVersion", System.currentTimeMillis());

        Cst.APP_PATH = container.getRealPath("/") + File.separator;

        logger.info("================================================================================");
        logger.info("reading external application config...");
        try
        {
            readSystemConfig();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info("current domain ip: " + Cst.DOMAIN_IP);
        logger.info("current domain url: " + Cst.DOMAIN_NAME);
        logger.info("current base path: " + Cst.BASE_PATH);
        logger.info("current base url: " + Cst.BASE_URL);
        logger.info("================================================================================");
        if (Cst.DEBUG_MODE)
        {
            logger.warn("Application Starting in DEBUG Mode!!!");
        }
        else
        {
            logger.info("Application Starting");
        }
        logger.info("================================================================================");
    }

    private void readSystemConfig() throws Exception
    {
        for (String p : POSSIBLE_FILE_PATHS)
        {
            if (Utl.fileExist(p))
            {
                Cst.BASE_PATH = p;
                break;
            }
        }

        String defaultConfigPath = Cst.BASE_PATH + "system.properties";
        Properties prop = Utl.readFromProperties(defaultConfigPath);

        String urlDomainIp = prop.getProperty("domain_ip", null);
        Cst.DOMAIN_IP = urlDomainIp.trim();

        String urlDomainName = prop.getProperty("domain_name", null);
        Cst.DOMAIN_NAME = urlDomainName.trim();

        String isDebugMode = prop.getProperty("is_debug_mode", "false");
        Cst.DEBUG_MODE = isDebugMode.equalsIgnoreCase("true");

        Cst.BASE_URL = Cst.DOMAIN_NAME + "/file/migu/";
    }

}
