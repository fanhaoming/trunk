package com.trunk.core.listeners;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author fanhaoming
 * @ClassName LoadPropertiesListener
 * @Description TODO
 * @Date 2019/10/23 15:20
 * @Version
 **/
public class LoadPropertiesListener implements ServletContextListener {

    private static Map<String,Object> data = new HashMap();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Properties prop = new Properties();
            String initPropertiesUrls = servletContextEvent.getServletContext().getInitParameter("initPropertiesUrls");
            if(StringUtils.isNotBlank(initPropertiesUrls)) {
                String[] urlArray = initPropertiesUrls.split(";");
                for (String url : urlArray) {
                    prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(url));
                    data.putAll(new HashMap<String,Object>((Map)prop));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static Object get(String key) {
        return data.get(key);
    }

    public static Map getMap() {
        return data;
    }

}
