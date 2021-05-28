package com.jw.cloud.jwprovider;

import com.jw.cloud.jwprovider.entity.Course;
import com.jw.cloud.jwprovider.mapper.CourseMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class MapperTest extends JwProviderApplicationTests {

    @Resource
    private CourseMapper courseMapper;
    @Autowired
    private DataSource dataSource;

    @Test
    public void testInsert() throws SQLException {
        for (int i = 3; i < 10; i++) {
            Course t = new Course();
            t.setName("名称:" + i);
            t.setDesct("描述:10000" + i);
            t.setStatus(1);
            courseMapper.insert(t);
        }
    }
}
