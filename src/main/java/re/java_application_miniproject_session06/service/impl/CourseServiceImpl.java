package re.java_application_miniproject_session06.service.impl;

import org.springframework.stereotype.Service;
import re.java_application_miniproject_session06.model.Course;
import re.java_application_miniproject_session06.repository.CourseRepository;
import re.java_application_miniproject_session06.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }



    @Override
    public List<Course> filterCourses(String level, Double maxPrice) {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> result = new ArrayList<>();

        for (Course course : allCourses) {
            boolean matchLevel = true;
            boolean matchPrice = true;

            if (level != null && !level.trim().isEmpty()) {
                matchLevel = course.getLevel() != null
                        && course.getLevel().equalsIgnoreCase(level.trim());
            }

            if (maxPrice != null && maxPrice > 0) {
                matchPrice = course.getPrice() <= maxPrice;
            }

            if (matchLevel && matchPrice) {
                result.add(course);
            }
        }

        return result;
    }

    @Override
    public  Optional<Course> findById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return Optional.empty();
        }
        return courseRepository.findByCode(code.trim());
    }

    @Override
    public boolean updateCourse(Course updatedCourse) {
        if (updatedCourse == null || updatedCourse.getId() == null) {
            return false;
        }

        Course oldCourse = courseRepository.findById(updatedCourse.getId());
        if (oldCourse == null) {
            return false;
        }

        if (updatedCourse.getFee() < 0) {
            return false;
        }

        oldCourse.setFee(updatedCourse.getFee());
        oldCourse.setStartDate(updatedCourse.getStartDate());

        courseRepository.update(oldCourse);
        return true;
    }

    @Override
    public boolean deleteCourse(Integer id) {
        Course course = courseRepository.findById(id);

        if (course == null) {
            return false;
        }

        if (course.getStudentCount() > 0) {
            return false;
        }

        courseRepository.delete(id);
        return true;
    }

    @Override
    public String getDeleteMessage(Integer id) {
        Course course = courseRepository.findById(id);

        if (course == null) {
            return "Khóa học không tồn tại";
        }

        if (course.getStudentCount() > 0) {
            return "Không thể hủy khóa học đã có học viên đăng ký";
        }

        return "Hủy khóa học thành công";
    }

    @Override
    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code);
    }
}