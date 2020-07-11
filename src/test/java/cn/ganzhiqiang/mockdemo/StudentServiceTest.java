package cn.ganzhiqiang.mockdemo;

import cn.ganzhiqiang.mockdemo.StudentDao;
import cn.ganzhiqiang.mockdemo.StudentService;
import cn.ganzhiqiang.mockdemo.model.StudentBo;
import cn.ganzhiqiang.mockdemo.proxy.SchoolManageProxy;
import cn.ganzhiqiang.mockdemo.utils.RedisUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SchoolManageProxy.class, RedisUtils.class, StudentService.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*"})
@SuppressStaticInitializationFor({"cn.ganzhiqiang.ares.unittest.SchoolManageProxy"})
public class StudentServiceTest {

    @Mock
    private StudentDao mockStudentDao;

    @InjectMocks
    private StudentService studentServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testQueryStudentScoreByKeyword() throws Exception {
        studentServiceUnderTest = PowerMockito.spy(studentServiceUnderTest);
        PowerMockito.mockStatic(RedisUtils.class);
        PowerMockito.mockStatic(SchoolManageProxy.class);

        // mock单例调用
        SchoolManageProxy mockSchoolManageProxy = PowerMockito.mock(SchoolManageProxy.class);
        PowerMockito.when(SchoolManageProxy.getInstance()).thenReturn(mockSchoolManageProxy);
        when(mockSchoolManageProxy.queryPerson(anyList())).thenReturn(Collections.emptyList());

        // mock掉对Redis的静态调用
        PowerMockito.when(RedisUtils.getArray(eq("tom"), eq(StudentBo.class))).thenReturn(Collections.emptyList());
        // 显示的mock掉静态的void的方法（可以不mock）
        PowerMockito.doNothing().when(RedisUtils.class, "setArray", anyString(), anyList(), anyInt());

        // mock私有方法processKeyword
        PowerMockito.doReturn("tom").when(studentServiceUnderTest, "processKeyword", anyString());

        // 跳过私有方法highlightResult的执行
        PowerMockito.suppress(PowerMockito.method(StudentService.class, "highlightResult"));

        // 使用Mockito来mock服务的调用
        when(mockStudentDao.queryStudentByKeyWord(anyString())).thenReturn(Collections.emptyList());

        // Run the test
        final List<StudentBo> result = studentServiceUnderTest.queryStudentScoreByKeyword("tom");

    }

}
