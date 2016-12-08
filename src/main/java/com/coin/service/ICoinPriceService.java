package com.coin.service;

import com.coin.pojo.Customer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-15 10:38
 */
public interface ICoinPriceService{

    /**
     * 查询结果集
     * @param coinDataParam 需要查询的接口url的参数
     * @return 查询的结果集
     * @throws IOException
     */
    List<String> queryFrom3Party(String coinDataParam) throws IOException;
    Map<String, String> fillInputTag() throws IOException;


    //----------用户信息-------
    Customer getCustomerInfo(int id);

    /**
     * 通过userName查询用户密码
     * @param userName 用户名
     * @return
     */
    String getPswByName(String userName);

    /**
     * 判断用户登录是否合法（用户名和密码是否正确）
     * @param userName 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    boolean isLeagalLogin(String userName, String password) throws Exception;
}
