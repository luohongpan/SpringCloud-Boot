package cn.how2j.trend.service;

import cn.how2j.trend.pojo.IndexData;
import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description: 从Redis获取数据
 * @create 2020-11-01 14:38
 */
@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    @Cacheable(key="'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }

}
