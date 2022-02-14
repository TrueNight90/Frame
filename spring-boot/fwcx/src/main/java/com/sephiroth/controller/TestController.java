package com.sephiroth.controller;

import com.alibaba.fastjson.JSON;
import com.sephiroth.common.CsvImportUtil;
import com.sephiroth.entity.FWCX;
import com.sephiroth.service.FWCSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class TestController {
    @Autowired
    FWCSService fwcsService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/fwcx/{id}")
    public Object getResult(@PathVariable String id){
        Map map = new HashMap();
        FWCX fwcx = fwcsService.selectOne(id);
        if( null != fwcx){
            map.put("code",1);
            map.put("data",fwcx);
        }else{
            map.put("code",0);
        }
        return map;
    }

    @PutMapping("/fwcx/{id}")
    public Object importOne(@PathVariable String id){
        Map map = new HashMap();
        try{
            FWCX fwcx = new FWCX();
            fwcx.setFwid(id);
            int i = fwcsService.importOne(fwcx);

            map.put("code",1);
            map.put("data",i);
            return map;
        }catch (Exception e){
            map.put("code",0);
            return map;
        }
    }

    @PostMapping("/fwcx")
    public Object importList(@RequestParam("file") MultipartFile file){
        Map map = new HashMap();
        try{
            File csvFile = CsvImportUtil.uploadFile(file);
            List<List<String>> lists = CsvImportUtil.readCSV(csvFile.getPath(), 1);
            List<FWCX> fwcxList = new ArrayList<>();
            lists.stream().forEach( item ->{
                FWCX fwcx = new FWCX();
                fwcx.setFwid(item.get(0));
                fwcxList.add(fwcx);
            });
            int i = fwcsService.importList(fwcxList);
            map.put("code",1);
            map.put("data",i);
            return map;
        }catch (Exception e){
            map.put("code",0);
            return map;
        }
    }

    @GetMapping("/sessionId")
    public String sessionId(HttpServletRequest request){
        return request.getSession().getId();
    }
}
