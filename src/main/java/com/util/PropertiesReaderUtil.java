package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * properties文件处理
 * @author: Raygong
 * @date: 2016/11/20 10:58.
 */
public class PropertiesReaderUtil {
    /**
     * properties文件读取转换为Properties对象（读取的文件路径在resources文件夹下）
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Properties reader(String fileName) throws IOException {
        InputStream in = PropertiesReaderUtil.class.getClassLoader().getResourceAsStream(fileName);
        Properties prop = new Properties();
        prop.load(in);
        return prop;
    }

    public static Map<String, String> prop2Map(String fileName) throws IOException {
        Map<String, String> propFileMap = new HashMap<>();
        Properties prop = reader(fileName);

        Iterator it = prop.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String key = String.valueOf(entry.getKey()).trim();
            String value = String.valueOf(entry.getValue()).trim();
            propFileMap.put(key, value);
        }
        return propFileMap;
    }


    public static void main(String[] args) {
        try {
            Map<String, String> propFileMap = prop2Map("coinDataSource.properties");
            System.out.println(propFileMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
