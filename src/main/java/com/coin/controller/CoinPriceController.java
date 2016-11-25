package com.coin.controller;

import com.coin.service.ICoinPriceService;
import com.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
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
    public String index(Model model) {
        try{
            model.addAttribute("inputParam", JsonUtil.toJSon(coinPriceService.queryFrom3Party()));
            return "coin/coinManage";
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
            String result = JsonUtil.toJSon(resultList);
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
}