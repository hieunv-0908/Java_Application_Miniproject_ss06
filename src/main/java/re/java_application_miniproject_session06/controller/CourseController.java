package re.java_application_miniproject_session06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import re.java_application_miniproject_session06.model.Course;
import re.java_application_miniproject_session06.service.impl.CourseServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private static CourseServiceImpl courseServiceImpl;

    // Trang danh sách khoá học, với đường dẫn mặc định /
    @GetMapping("/")
    public String listCourses(
            @RequestParam(defaultValue = "") String level,
            @RequestParam(defaultValue = "999999999") double price,
            Model model
    ){
        // Mock data
        List<Course> courses = courseServiceImpl.filterCourses(level, price);
        model.addAttribute("list",courses);
        model.addAttribute("level",level);
        model.addAttribute("price",price);

        return "coures/list";
    }

    @GetMapping("/detail/{code}")
    public String detailCourse(
            @RequestParam(defaultValue = "") String code,
            Model model
    ){
        Course course = courseServiceImpl.getCourseByCode(code);
        model.addAttribute("course", course);
        return "coures/detail";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(
            @PathVariable int id,
            Model model
    ) {
        Course course = courseServiceImpl.getById(id);
        model.addAttribute("course", course);

        return "course/edit";
    }

    @PostMapping("/update")
    public String updateCourse(
            @ModelAttribute Course course
    ) {
        courseServiceImpl.updateCourse(course);

        // PRG Pattern
        return "redirect:/course/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(
            @PathVariable int id,
            Model model
    ) {
        boolean success = courseServiceImpl.deleteCourse(id);

        if (!success) {
            model.addAttribute("error",
                    "Không thể hủy khóa học đã có học viên đăng ký");

            // load lại danh sách
            List<Course> courses = courseServiceImpl.getAll();
            model.addAttribute("courses", courses);

            return "course/list";
        }

        return "redirect:/course/list";
    }
}
