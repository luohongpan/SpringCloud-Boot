package cn.how2j.trend;

import cn.hutool.core.net.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-29 23:54
 */
@SpringBootApplication //启动类
@EnableEurekaServer //表示它是个注册中心服务器
public class EurekaServerApplication {


    public static void main(String[] args) throws Exception {
        //默认端口号
        int port=8761;
        if(!NetUtil.isUsableLocalPort(port)){
            throw new Exception(String.format("端口号被占用" + port+"无法启动"));
        }
        new SpringApplicationBuilder(EurekaServerApplication.class).properties("server.port="+port).run(args);
    }
}
