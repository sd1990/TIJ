package org.songdan.tij.array.property;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件管理器，可以动态的管理.properties文件的属性
 * @author SONGDAN
 */
public final class PropertiesManager {
    private static Map<String, PropertiesManager> map = new HashMap<>();
    
    private File m_file = null;
    
    private long m_lastModifiedTime = 0;
    
    private Properties m_props = null;
    
    /**
     * 获取配置文件中的属性
     * @param fileName 配置文件名称
     * @param name 属性名称
     * @param defaultVal 默认值
     * @return
     */
    public static String getConfigItem(String fileName, String name, String defaultVal) {
        String val = PropertiesManager.getInstance(fileName).getConfigItem(name, defaultVal);
        if (val == null) {
            return defaultVal;
        }
        else {
            return val;
        }
    }

    /**
     * 私有化构造方法
     * @param fileName
     */
    private PropertiesManager(String fileName) {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        m_file = new File(path + fileName);
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(m_file),"utf-8");
            m_props = new Properties();
            m_props.load(in);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取properties的方法
     * @param fileName
     * @return
     */
    private static PropertiesManager getInstance(String fileName) {
        PropertiesManager propertiesManager = map.get(fileName);
        if (propertiesManager == null) {
            propertiesManager = new PropertiesManager(fileName);
            map.put(fileName, propertiesManager);
        }
        return propertiesManager;
    }

    public String getConfigItem(String name, String defaultVal) {
        long newTime = m_file.lastModified();
        if (newTime == 0) {
            if (m_lastModifiedTime == 0) {
                System.err.println(m_file.getName() + " file does not exist!");
            }
            else {
                System.err.println(m_file.getName() + " file was deleted!!");
            }
            return defaultVal;
        }
        else if (newTime > m_lastModifiedTime) {
            m_props.clear();
            try {
                m_props.load(new InputStreamReader(new FileInputStream(m_file),"utf-8"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        m_lastModifiedTime = newTime;

        String val = m_props.getProperty(name);
        if (val == null) {
            return defaultVal;
        }
        else {
            return val;
        }
    }

    public static void main(String[] args) {
        System.out.println(PropertiesManager.getConfigItem("general.properties", "wsdlUrl", null));
    }
}