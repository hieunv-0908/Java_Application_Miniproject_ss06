package re.java_application_miniproject_session06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import re.java_application_miniproject_session06.model.Course;
import re.java_application_miniproject_session06.service.impl.CourseServiceImpl;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    // Trang danh sách khoá học, với đường dẫn mặc định /
    @GetMapping("/")
    public String homeCourses(
            @RequestParam(name = "level", defaultValue = "") String level,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            Model model
    ){
        return listCourses(level, maxPrice, model);
    }

    @GetMapping("/course/list")
    public String listCourses(
            @RequestParam(name = "level", defaultValue = "") String level,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            Model model
    ){
        // Mock data
        List<Course> courses = courseServiceImpl.filterCourses(level, maxPrice);
        model.addAttribute("courses",courses);
        model.addAttribute("level",level);
        model.addAttribute("maxPrice",maxPrice);

        return "course/course-list";
    }

    @GetMapping("/course/detail/{code}")
    public String detailCourse(
            @PathVariable(name = "code") String code,
            Model model
    ){
        Course course = courseServiceImpl.getCourseByCode(code);
        model.addAttribute("course", course);
        return "course/course-detail";
    }

    @GetMapping("/course/edit/{id}")
    public String showUpdateForm(
            @PathVariable(name = "id") int id,
            Model model
    ) {
        Course course = courseServiceImpl.findById(id).orElse(null);
        model.addAttribute("course", course);

        return "layout/course/course-form";
    }

    @PostMapping("/update")
    public String updateCourse(
            @ModelAttribute(name = "course") Course course
    ) {
        courseServiceImpl.updateCourse(course);

        // PRG Pattern
        return "redirect:/course/list";
    }

    @PostMapping("course/delete/{id}")
    public String deleteCourse(
            @PathVariable(name = "id") int id,
            Model model
    ) {
        boolean success = courseServiceImpl.deleteCourse(id);

        if (!success) {
            model.addAttribute("error",
                    "Không thể hủy khóa học đã có học viên đăng ký");

            // load lại danh sách
            List<Course> courses = courseServiceImpl.findAll();
            model.addAttribute("courses", courses);

            return "course/course-list";
        }

        return "redirect:/course/list";
    }
}
