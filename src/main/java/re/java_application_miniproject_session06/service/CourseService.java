package re.java_application_miniproject_session06.service;
import re.java_application_miniproject_session06.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    List<Course> filterCourses(String level, Double maxFee);

    Optional<Course> findById(Integer id);

    Optional<Course> findByCode(String code);

    boolean updateCourse(Course updatedCourse);

    boolean deleteCourse(Integer id);

    String getDeleteMessage(Integer id);

    Course getCourseByCode(String code);
}