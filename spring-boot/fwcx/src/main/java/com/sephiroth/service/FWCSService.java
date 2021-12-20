package com.sephiroth.service;

import com.sephiroth.entity.FWCX;
import com.sephiroth.mapper.FWCXMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FWCSService {
    @Autowired
    FWCXMapper fwcxMapper;

    public FWCX selectOne(String id){
        FWCX fwcx = new FWCX();
        fwcx.setFwid(id);
        int i = fwcxMapper.updateOne(fwcx);
        if(i > 0){
            return fwcxMapper.selectOne(id);
        }else{
            return null;
        }
    }

    public int importOne(FWCX fwcx){
        return fwcxMapper.insert(fwcx);
    }

    public int importList(List<FWCX> list){
        List<String> strings = fwcxMapper.selectList(list);
        List<FWCX> collect = list.stream().filter(fw -> !strings.contains(fw.getFwid())).collect(Collectors.toList());
        return fwcxMapper.insertList(collect);
    }
}
