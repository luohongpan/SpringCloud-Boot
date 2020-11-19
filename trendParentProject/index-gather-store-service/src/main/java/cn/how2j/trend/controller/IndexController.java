package cn.how2j.trend.controller;

import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 医保产品二部_罗洪盼_CN32230
 * @Description:
 * @create 2020-10-30 1:53
 */
@RestController
public class IndexController {
    @Autowired
    IndexService indexService;

//    @GetMapping("/getList")
//    public List<Index> getList() {
//       return  indexService.fetch_indexes_from_third_part();
//    }
    @GetMapping("/freshCodes")
    public List<Index> fresh() throws Exception{
        return indexService.fresh();
    }

    @GetMapping("/getCodes")
    public List<Index> get() throws Exception{
        return indexService.get();
    }

    @GetMapping("/removeCodes")
    public String remove() throws Exception{
        indexService.remove();
        return "remove codes successfully";
    }


}
