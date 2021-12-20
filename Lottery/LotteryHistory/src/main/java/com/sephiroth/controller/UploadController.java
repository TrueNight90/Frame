package com.sephiroth.controller;

import com.sephiroth.test.test;
import com.sephiroth.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

@RestController
public class UploadController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("upload")
    public Object upload(@RequestParam(value = "file",required = false) MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String dateTimeString = String.valueOf(DateUtil.dateToUnixDate(new Date()));
        String info = "";
        try{
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.info("上传的后缀名为：" + suffixName);
            String fileName = dateTimeString+"_image"+suffixName;
            File f = new File(fileName);
            info = test.getInfo(file.getInputStream());
            if(!f.exists()){
                file.transferTo(f);
            }


//            fileReName(file,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return info;
    }

    public void fileReName(MultipartFile file,String name) throws Exception{
        File f = new File(name);
        if(!f.exists()){
            file.transferTo(f);
        }else{
            String[] s = f.getName().split("_")[0].split("-");
            String newFileName = s[0];
            boolean flag = true;
            int i = 1;
            File newFile = new File("");
            while(flag){
                if(s.length <= 1){
                    i = 1;
                }
                newFileName = newFileName + "-" + String.valueOf(i);
                newFile = new File(newFileName);
                if(newFile.exists()){
                    i++;
                }else{
                    f.renameTo(newFile);
                    flag = false;
                }

            }
        }
    }
}
