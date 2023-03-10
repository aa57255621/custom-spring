package com.lp.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liupeng1
 */
public class UserDao {

    private static Map<String, String> hasMap = new HashMap<>();

    static {
        hasMap.put("1001","寒风");
        hasMap.put("1002","窗外的寒风");
        hasMap.put("1003","窗外寒风");
    }

    public String queryUserName(String uId) {
        return hasMap.get(uId);
    }

}
