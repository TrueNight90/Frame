package org.org.sephiroth.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapDemo {

    private String name;

    private String group;

    public MapDemo(String name,String group){
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static void main(String[] args) {
        MapDemo m1 = new MapDemo("zhangsan","1");
        MapDemo m2 = new MapDemo("lisi","1");
        MapDemo m3 = new MapDemo("wangwu","2");
        MapDemo m4 = new MapDemo("zhaoliu",null);
        List<MapDemo> list = new ArrayList<MapDemo>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

        Map<Boolean, List<MapDemo>> noNullList = list.stream().collect(Collectors.partitioningBy(m -> StringUtils.isEmpty(m.getGroup())));

        System.out.println(JSON.toJSONString(noNullList.get(false)));
    }
}
