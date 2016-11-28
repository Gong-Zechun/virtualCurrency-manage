package com.coin.service;

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
}
