package cn.how2j.trend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-30 0:35
 */
@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataApplication {
    public static void main(String[] args) {
        int port = 8090;
        int eurekaServerPort = 8761;

        if (NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("检查到端口%d 未启动，判断 eureka服务注册中心未启动，本服务无法使用，故退出%n", eurekaServerPort);
            System.exit(1);
        }
        if (null != args && 0!=args.length ) {
            for (String str: args){
                if (str.startsWith("port=")){
                    String subPort = StrUtil.subAfter(str, "port=", true);
                    if (NumberUtil.isNumber(subPort)){
                        port= Convert.toInt(subPort);
                    }
                }
            }
        }
        if (!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d被占用了，无法启动端口:", port);
            System.exit(1);
        }
        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).properties("server.port="+port).run(args);
    }
}
