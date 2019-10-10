package com.mayikt.impl.pay.service.factory;

import com.mayikt.impl.pay.service.strategy.PayStrategy;
import com.mayikt.impl.pay.service.strategy.PaymentCompensationStrategy;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : CompensationStrategyFactory  //类名
 * @Description :   //描述
 * @Author : 焦荣国  //作者
 * @Date: 2019-10-08 21:43  //时间
 * 《身无彩凤双飞翼，心有灵犀一点通》
 */
public class CompensationStrategyFactory {
    private static CompensationStrategyFactory instance = new CompensationStrategyFactory();
    private static Map<String, PaymentCompensationStrategy> strategyBean;
    private CompensationStrategyFactory(){
        strategyBean = new ConcurrentHashMap<String, PaymentCompensationStrategy>();
    }

    public static CompensationStrategyFactory getInstance(){
        if (instance == null) {
            instance = new CompensationStrategyFactory();
        }
        return instance;
    }

    // 思考几个点：
    public static PaymentCompensationStrategy getPaymentCompensationStrategy(String classAddres) {
        try {
            if (StringUtils.isEmpty(classAddres)) {
                return null;
            }
            PaymentCompensationStrategy beanPayStrategy = strategyBean.get(classAddres);
            if (beanPayStrategy != null) {
                return beanPayStrategy;
            }
            // 1.使用Java的反射机制初始化子类
            Class<?> forName = Class.forName(classAddres);
            // 2.反射机制初始化对象
            PaymentCompensationStrategy payStrategy = (PaymentCompensationStrategy) forName.newInstance();
            if (null!= payStrategy){
                strategyBean.put(classAddres, payStrategy);
            }
            return payStrategy;
        } catch (Exception e) {
            return null;
        }
    }
}
