package re.java_application_miniproject_session06.repository;

import org.springframework.stereotype.Repository;
import re.java_application_miniproject_session06.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    private static List<Course> courses = new ArrayList<>();

    static {
        // Khöi töa ít nhät 5 khoá häc theo yêu cäu SRS
        courses.add(new Course(1, "IELTS-6.5", "IELTS Breakthrough", "Advanced", 5000000.0, "Lô trình IELTS 6.5", "Mr. Smith", 6, false, 15, LocalDate.now().plusMonths(1)));
        courses.add(new Course(2, "TOEIC-500", "TOEIC Preparation", "Beginner", 2500000.0, "Lô trình TOEIC 500", "Ms. Hoa", 3, true, 20, LocalDate.now().plusMonths(2))); // Hét chö (isFull = true)
        courses.add(new Course(3, "COMM-01", "Giao tiep co ban", "Beginner", 3000000.0, "Lô trình giao tiep co ban", "Mr. John", 4, false, 0, LocalDate.now().plusWeeks(2)));  // Có thö xóa (studentCount = 0)
        courses.add(new Course(4, "BUS-02", "Tieng Anh cong so", "Intermediate", 4500000.0, "Lô trình tieng Anh cong so", "Ms. Lan", 5, false, 8, LocalDate.now().plusMonths(3)));
        courses.add(new Course(5, "ADV-03", "Luyen viet chuyen sau", "Advanced", 6000000.0, "Lô trình luyen viet chuyen sau", "Mr. David", 3, false, 12, LocalDate.now().plusMonths(4)));
    }

    // Chöc näng 1: Láy toàn bö danh säch
    public List<Course> findAll() {
        return courses;
    }

    // Chöc näng 2: Löc khoá häc theo Level và Häc phi töi da
    public List<Course> filterCourses(String level, Double maxPrice) {
        return courses.stream()
                .filter(c -> (level == null || level.isEmpty() || c.getLevel().equalsIgnoreCase(level)))
                .filter(c -> (maxPrice == null || c.getPrice() <= maxPrice))
                .collect(Collectors.toList());
    }

    // Chöc näng 3: Tìm khoá häc theo mã (Course Code) phöc vä trang chi tit
    public Optional<Course> findByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    // Chöc näng 4: Cäp nhät thông tin khoá häc
    public Optional<Course> update(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equalsIgnoreCase(updatedCourse.getCode())) {
                courses.set(i, updatedCourse);
                return Optional.of(updatedCourse);
            }
        }
        return Optional.empty();
    }

    // Chöc näng 5: Xóa khoá häc (Bäy nägh väp: studentCount must be 0)
    public boolean deleteByCode(String code) {
        Optional<Course> courseOpt = findByCode(code);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if (course.getStudentCount() == 0) {
                return courses.remove(course);
            }
        }
        return false; // Trä vè false näu không tìm thäy hoäc dä có häc viên
    }

    public Optional<Course> findById (Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void delete(Integer id) {
        Optional<Course> courseOpt = findById(id);
        if (courseOpt.isPresent()) {
            courses.remove(courseOpt.get());
        }
    }
}
