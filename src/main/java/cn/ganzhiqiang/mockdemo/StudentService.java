package cn.ganzhiqiang.mockdemo;

import cn.ganzhiqiang.mockdemo.model.Person;
import cn.ganzhiqiang.mockdemo.model.Student;
import cn.ganzhiqiang.mockdemo.model.StudentBo;
import cn.ganzhiqiang.mockdemo.proxy.SchoolManageProxy;
import cn.ganzhiqiang.mockdemo.utils.CommonUtils;
import cn.ganzhiqiang.mockdemo.utils.RedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zq_gan
 * @since 2020/7/11
 **/

@Component
public class StudentService {

    @Resource
    private StudentDao studentDao;

    public List<StudentBo> queryStudentScoreByKeyword(String name) {
        System.out.println("invoke StudentService.queryStudentScoreByKeyword ...");

        List<StudentBo> cacheList = RedisUtils.getArray(name, StudentBo.class);
        if (CollectionUtils.isNotEmpty(cacheList)) {
            return cacheList;
        }
        String keyword = processKeyword(name);
        List<Student> students = studentDao.queryStudentByKeyWord(keyword);
        List<Integer> ids = students.stream().map(Student::getId).collect(Collectors.toList());
        List<Person> personList = SchoolManageProxy.getInstance().queryPerson(ids);

        List<StudentBo> studentBos = CommonUtils.toBo(personList, students);
        // 高亮结果
        highlightResult(studentBos, name);
        // 缓存到Redis
        RedisUtils.setArray(name, studentBos, 10 * 60);
        return studentBos;
    }

    private String processKeyword(String name) {
        System.out.println("invoke StudentService.processKeyword ...");
        String newName = name;
        // do somethings
        return newName;
    }

    private void highlightResult(List<StudentBo> result, String name) {
        System.out.println("invoke StudentService.highlightResult ...");
        // do keyword highlight
    }

}
