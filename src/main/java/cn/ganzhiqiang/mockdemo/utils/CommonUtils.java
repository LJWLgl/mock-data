package cn.ganzhiqiang.mockdemo.utils;

import cn.ganzhiqiang.mockdemo.model.Person;
import cn.ganzhiqiang.mockdemo.model.Student;
import cn.ganzhiqiang.mockdemo.model.StudentBo;

import java.util.Collections;
import java.util.List;

/**
 * @author zq_gan
 * @since 2020/7/11
 **/

public class CommonUtils {

    public static int calc(List<Integer> scores) {
        return scores.stream().mapToInt((i) -> i).sum() / scores.size();
    }

    public static List<StudentBo> toBo(List<Person> personList, List<Student> students) {
        return Collections.emptyList();
    }

}
