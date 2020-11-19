package cn.how2j.trend.config;

import cn.how2j.trend.job.IndexDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.SimpleBeanInfo;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description: 定时器配置
 * @create 2020-10-31 18:40
 */
@Configuration
public class QuartzConfiguration {
    private static final int interval=1;
    @Bean
    public JobDetail weatherDataSyncJobDetail(){
        return JobBuilder.newJob(IndexDataSyncJob.class).withIdentity("indexDataSyncJob").storeDurably().build();
    }
    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder schedBuilder =SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(interval).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).withIdentity("indexDataSyncTrigger").withSchedule(schedBuilder).build();
    }
}
