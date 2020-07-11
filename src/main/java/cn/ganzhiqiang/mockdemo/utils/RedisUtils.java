package cn.ganzhiqiang.mockdemo.utils;

import java.util.Collections;
import java.util.List;

/**
 * @author zq_gan
 * @since 2020/7/11
 **/

public class RedisUtils {

    public static <T> List<T> getArray(String key, Class<T> clazz) {
        System.out.println("invoke RedisUtils.getArray ...");
        return Collections.emptyList();
    }

    public static <T> void setArray(String key, List<T> list, int time) {
        System.out.println("invoke RedisUtils.setArray ...");
    }

}
