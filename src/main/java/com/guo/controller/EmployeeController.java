package com.guo.controller;


import com.guo.dao.DepartmentMapper;
import com.guo.dao.EmployeeMapper;
import com.guo.pojo.Department;
import com.guo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;


    @RequestMapping("/emps")//显示员工列表
    public String List(Model model){
        List<Employee> employees = employeeMapper.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")//跳转添加员工页面
    public String toAddpage(Model model){
        //查出所有部门的信息
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")//post提交
    public String addEmp(Employee employee){
        System.out.println("save==>"+employee);
        //添加的操作
        employeeMapper.save(employee);//调用底层业务方法保存员工信息
        return "redirect:/emps";
    }

    //去员工下面的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        //查出原来的数据
        Employee employee = employeeMapper.getEmployeeByid(id);
        model.addAttribute("emp",employee);
        //查出所有部门的信息
        List<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    //员工信息修改
    @PostMapping("/updateEmp")
    public String updataEmp(Employee employee){

        employeeMapper.update(employee);//调用底层业务方法保存员工信息
        return "redirect:/emps";

    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id,Model model){
        employeeMapper.delete(id);
        return "redirect:/emps";
    }

}
