package com.sephiroth.mapper;

import com.sephiroth.entity.FWCX;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FWCXMapper {

    FWCX selectOne(String id);

    int updateOne(FWCX fwcx);

    int insert(FWCX fwcx);

    int insertList(List<FWCX> list);

    List<String> selectList(List<FWCX> list);
}
