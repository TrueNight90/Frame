package org.sephiroth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeMenu {

    public static void main(String[] args) {
        String a = "[\n" +
                "\t{\"id\":1,\"name\":\"1\",\"parent\":0,\"children\":[]},\n" +
                "\t{\"id\":2,\"name\":\"2\",\"parent\":0,\"children\":[]},\n" +
                "\t{\"id\":3,\"name\":\"3\",\"parent\":1,\"children\":[]},\n" +
                "\t{\"id\":4,\"name\":\"4\",\"parent\":3,\"children\":[]},\n" +
                "\t{\"id\":5,\"name\":\"5\",\"parent\":2,\"children\":[]},\n" +
                "\t{\"id\":6,\"name\":\"6\",\"parent\":3,\"children\":[]}\n" +
                "]";
        JSONArray jsonArray = JSON.parseArray(a);
        List<JSONObject> list = jsonArray.toJavaList(JSONObject.class);
        Object child = getChild(null, list);

        System.out.println(JSON.toJSONString(child));
    }

    public static List getChild(JSONObject obj,List<JSONObject> list){
        List returnlist = new ArrayList();
        Iterator<JSONObject> iterator = list.iterator();
        while (iterator.hasNext()){
            JSONObject next = iterator.next();
            if((obj == null && next.getInteger("parent").equals(0))
                    || (next.getInteger("parent").equals(obj.getInteger("id")))){
                    iterator.remove();
                    next.getJSONArray("children").addAll(getChild(next,list));
                    returnlist.add(next);
                    iterator = list.iterator();
            }
        }
        return returnlist;
    }
}
