package cn.how2j.trend.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description: 获取当前的端口号
 * @create 2020-10-31 19:14
 */
@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {

    private  int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort=event.getWebServer().getPort();
    }

    public int getPort(){
        return this.serverPort;
    }

}
