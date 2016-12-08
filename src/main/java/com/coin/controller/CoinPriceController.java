package com.coin.controller;

import com.coin.pojo.Customer;
import com.coin.service.ICoinPriceService;
import com.util.JsonUtil;
import com.util.encrypt.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-14 15:38
 */
@Controller
@RequestMapping("coin")
public class CoinPriceController {
    private static final Logger log = LoggerFactory.getLogger(CoinPriceController.class);

    @Resource
    private ICoinPriceService coinPriceService;

    @RequestMapping("price")
    @ResponseBody
    public String price() {
        try{
            String result = JsonUtil.toJsonStr(coinPriceService.fillInputTag());
            return result;
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("data")
    @ResponseBody
    public String getData(String coinDataParam) {
        try{
            List<String> resultList = coinPriceService.queryFrom3Party(coinDataParam);
            String result = JsonUtil.toJsonStr(resultList);
            return result;
        }catch(IOException e) {
            e.printStackTrace();
            log.error("读取properties文件出错", e.getMessage(), e);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("login")
    public String login() {
        return "coin/login";
    }

    @RequestMapping("main")
    public String toCoinManage(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            if(coinPriceService.isLeagalLogin(userName, password)) {
                return "coin/coinManage";
            }else{
                return "coin/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
