package cn.how2j.trend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import sun.applet.Main;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-30 1:56
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCaching
public class IndexGatherStoreApplication {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        int port = 0;
        int defaultPort = 8001;
        int eurekaServerPort = 8761;
        int redisPort=6379;
        port = defaultPort;
        if (NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("检查到端口%d 未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n", eurekaServerPort);
            System.exit(1);
        }
        if(NetUtil.isUsableLocalPort(redisPort)){
            System.err.printf("检查到端口%d 未启用，判断 Redis 服务器没有启动，本服务无法使用，故退出%n", redisPort);

        }


        if (null != args && 0 != args.length) {
            for (String str : args) {
                if (str.startsWith("port=")) {
                    String s = StrUtil.subAfter(str, "port=", true);
                    if (NumberUtil.isNumber(s)) {
                        port = Convert.toInt(s);
                    }
                }
            }
        }

        if (!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port);
            System.exit(1);
        }

        new SpringApplicationBuilder(IndexGatherStoreApplication.class).properties("server.port="+port).run(args);

    }


}
