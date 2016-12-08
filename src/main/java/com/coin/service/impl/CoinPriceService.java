package com.coin.service.impl;

import com.coin.dao.CustomerMapper;
import com.coin.pojo.Customer;
import com.coin.service.ICoinPriceService;
import com.common.HttpRequest;
import com.util.JsonUtil;
import com.util.PropertiesReaderUtil;
import com.util.StringUtil;
import com.util.encrypt.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-15 10:38
 */
@Service
public class CoinPriceService implements ICoinPriceService{
    @Resource
    private CustomerMapper customerDao;

    //前端传过来的参数值对应的url参数名
    public static Map<String, String> propMap = new HashMap<>();

    /**
     * 接口返回结果集
     * @param coinDataParam 前端传递的查询参数，类似“okcoinUrlParam,coinbaseUrlParam”
     * @return
     * @throws IOException
     */
    @Override
    public List<String> queryFrom3Party(String coinDataParam) throws IOException{
        propMap = PropertiesReaderUtil.prop2Map("coinDataSource.properties");
        String btc123Url = propMap.get("btc123Url");
        String[] tempArray = coinDataParam.split(",");
        List<String> paramList = new ArrayList<>();

        for(String meta : tempArray) {
            String urlParam = propMap.get(meta);
            paramList.add(urlParam);
        }
        return queryFrom3Party(btc123Url, paramList);
    }

    /**
     *
     * @param baseUrl 查询url
     * @param paramList url后的参数的List结果集
     * @return
     */
    public List<String> queryFrom3Party(String baseUrl, List<String> paramList) {
        List<String> resultList = new ArrayList<>();
        for(String meta : paramList) {
            StringBuilder urlSb = new StringBuilder(baseUrl + meta);
            String result = HttpRequest.sendGet(urlSb.toString());
            resultList.add(result);
        }
        return resultList;
    }

    /**
     *
     * @param baseUrl 查询url
     * @param paramStr url后的参数字符串
     * @return
     */
    public String queryFrom3Party(String baseUrl, String paramStr) {
        StringBuilder urlSb = new StringBuilder(baseUrl + paramStr);
        String resultStr = HttpRequest.sendGet(urlSb.toString());
        return resultStr;
    }

    /**
     * 返回cName和properties文件中的key所组成了Map
     * （用于填充页面的input标签，cName是给人看的，properties文件中的key是给机器用的）
     * @return
     * @throws IOException
     */
    @Override
    public Map<String, String> fillInputTag() throws IOException {
        Map<String, String> propMap = PropertiesReaderUtil.prop2Map("coinDataSource.properties");
        String btc123Url = propMap.get("btc123Url");
        Map<String, String> resultMap = new HashMap<>();

        Iterator iterator = propMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            String val = entry.getValue().toString();
            if("btc123Url".equals(key)) {
                continue;
            }else {
                String resultStr = queryFrom3Party(btc123Url, val);
                Map<String, Map<String, String>> tempMap = JsonUtil.json2Map(resultStr);
                String cName = tempMap.get("datas").get("cName");
				if(StringUtil.isEmpty(cName)) {
                    continue;
                }
                resultMap.put(cName, key);
            }
        }
        return resultMap;
    }

    @Override
    public Customer getCustomerInfo(int id) {
        return customerDao.selectCustomerById(id);
    }

    @Override
    public String getPswByName(String userName) {
        return customerDao.getPswByName(userName);
    }

    @Override
    public boolean isLeagalLogin(String userName, String password) throws Exception {
        String passwordFromDB = this.getPswByName(userName);
        if(Md5Utils.getMD5Str(password).equals(passwordFromDB)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try{
            CoinPriceService cps = new CoinPriceService();
            cps.fillInputTag();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
