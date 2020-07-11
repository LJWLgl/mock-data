package cn.ganzhiqiang.mockdemo.proxy;

import cn.ganzhiqiang.mockdemo.model.Person;

import java.util.List;

/**
 * @author zq_gan
 * @since 2020/7/11
 **/

public class SchoolManageProxy {

    private static SchoolManageProxy instance;
    private static SchoolManageClient schoolManageClient;

    static {
        init();
    }

    private SchoolManageProxy() {
        init();
    }

    private static void init() {
        schoolManageClient = new SchoolManageClient();
        schoolManageClient.setResponseTime(1000);
    }

    public static SchoolManageProxy getInstance() {
        if (instance == null) {
            instance = new SchoolManageProxy();
        }
        return instance;
    }

    public List<Person> queryPerson(List<Integer> ids) {
        System.out.println("invoke SchoolManageProxy.queryPerson ...");
        return schoolManageClient.queryPerson(ids);
    }


}
