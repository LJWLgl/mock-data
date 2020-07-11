package cn.ganzhiqiang.mockdemo;

import cn.ganzhiqiang.mockdemo.model.Student;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author zq_gan
 * @since 2020/7/11
 **/

@Component
public class StudentDao {

    public List<Student> queryStudentByKeyWord(String name) {
        System.out.println("invoke StudentDao.queryStudentByKeyWord ...");
        return Collections.emptyList();
    }

}
