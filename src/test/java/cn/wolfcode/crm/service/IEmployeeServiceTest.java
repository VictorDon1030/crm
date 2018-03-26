package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IEmployeeServiceTest {

    @Autowired
    IEmployeeService employeeService;

    @Test
    public void insert() {
        for (int i = 11 ; i < 15 ; i++){
            Employee employee = new Employee();
            employee.setUsername("test"+i);
            employee.setPassword("ssssssssssss");
            employee.setHireDate(new Date());
            employeeService.insert(employee, null);
        }

    }
}