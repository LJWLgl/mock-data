package cn.ganzhiqiang.mockdemo.proxy;

import cn.ganzhiqiang.mockdemo.model.Person;

import java.util.Collections;
import java.util.List;

/**
 * @author zq_gan
 * @since 2020/7/11
 **/

public class SchoolManageClient {

    private int responseTime;

    public List<Person> queryPerson(List<Integer> ids) {
        return Collections.emptyList();
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}
