package cn.how2j.trend.job;

import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.service.IndexDataService;
import cn.how2j.trend.service.IndexService;
import cn.hutool.core.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-31 18:34
 */
public class IndexDataSyncJob extends QuartzJobBean {

    @Autowired
    private IndexDataService indexDataService;

    @Autowired
    private IndexService indexService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时启动:"+ DateUtil.now());
        //指数数据
        List<Index> fresh = indexService.fresh();
        for (Index index:fresh){
            //指数代码
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束:"+DateUtil.now());
    }
}
