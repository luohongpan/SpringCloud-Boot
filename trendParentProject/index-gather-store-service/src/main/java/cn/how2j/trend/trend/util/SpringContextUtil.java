package cn.how2j.trend.trend.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-30 20:15
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private  SpringContextUtil(){
        System.out.println("SpringContextUtil()");
    }

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("注意:applicationContext:"+applicationContext);
        SpringContextUtil.applicationContext=applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
