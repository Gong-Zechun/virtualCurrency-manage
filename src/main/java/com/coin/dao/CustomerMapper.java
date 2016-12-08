package com.coin.dao;

import com.coin.pojo.Customer;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-12-8 11:11
 */
public interface CustomerMapper {
    Customer selectCustomerById(int id);

    String getPswByName(String userName);
}
