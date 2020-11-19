package cn.how2j.trend.service;

import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.trend.util.SpringContextUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-30 1:27
 */
@Service
@CacheConfig(cacheNames="indexes")
public class IndexService {
    private List<Index> indexes;
    @Autowired
    RestTemplate restTemplate;

    //@HystrixCommand(fallbackMethod = "third_part_not_connected")
    //@Cacheable(key="'all_codes'")
    public List<Index> fetch_indexes_from_third_part(){
        List<Map> forObjectMap = restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json", List.class);
        return map2Index(forObjectMap);
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<Index> fresh(){
        indexes=fetch_indexes_from_third_part();
        IndexService indexService = SpringContextUtil.getBean(IndexService.class);
        indexService.remove();
        return indexService.store();
    }

    @CacheEvict(allEntries = true)
    public void remove(){

    }

    @Cacheable(key="'all_codes'")
    public List<Index> store(){
        System.out.println("store()刷新输出"+this);
        return indexes;
    }

    @Cacheable(key = "'all_codes'")
    public List<Index> get(){
        return CollUtil.toList();
    }



    private List<Index> map2Index(List<Map> forObjectMap) {
        List<Index> indexs = new ArrayList<>();
        for (Map map:forObjectMap){
            String objCode = map.get("code").toString();
            String objName =map.get("name").toString();
            Index index = new Index();
            index.setCode(objCode);
            index.setName(objName);
            indexs.add(index);
        }
        return indexs;
    }


    private List<Index> third_part_not_connected(){
        System.out.println("third_part_not_connected()");
        Index index = new Index();
        index.setName("无效指数代码");
        index.setCode("000000000");
        return CollectionUtil.toList(index);
    }



}
