package re.java_application_miniproject_session06.repository;

import org.springframework.stereotype.Repository;
import re.java_application_miniproject_session06.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    // Sử dụng List tĩnh để giả lập Database
    private static List<Course> courses = new ArrayList<>();

    static {
        // Khởi tạo ít nhất 5 khóa học theo yêu cầu SRS
        courses.add(new Course("IELTS-6.5", "IELTS Breakthrough", "Advanced", 5000000.0, "Mr. Smith", "6 tháng", false, 15));
        courses.add(new Course("TOEIC-500", "TOEIC Preparation", "Beginner", 2500000.0, "Ms. Hoa", "3 tháng", true, 20)); // Hết chỗ (isFull = true)
        courses.add(new Course("COMM-01", "Giao tiếp cơ bản", "Beginner", 3000000.0, "Mr. John", "4 tháng", false, 0));  // Có thể xóa (studentCount = 0)
        courses.add(new Course("BUS-02", "Tiếng Anh công sở", "Intermediate", 4500000.0, "Ms. Lan", "5 tháng", false, 8));
        courses.add(new Course("ADV-03", "Luyện viết chuyên sâu", "Advanced", 6000000.0, "Mr. David", "3 tháng", false, 12));
    }

    // Chức năng 2: Lấy toàn bộ danh sách
    public List<Course> findAll() {
        return courses;
    }

    // Chức năng 2: Lọc khóa học theo Level và Học phí tối đa
    public List<Course> filterCourses(String level, Double maxPrice) {
        return courses.stream()
                .filter(c -> (level == null || level.isEmpty() || c.getLevel().equalsIgnoreCase(level)))
                .filter(c -> (maxPrice == null || c.getPrice() <= maxPrice))
                .collect(Collectors.toList());
    }

    // Chức năng 3: Tìm khóa học theo mã (Course Code) phục vụ trang chi tiết
    public Optional<Course> findByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    // Chức năng 4: Cập nhật thông tin khóa học
    public void update(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equalsIgnoreCase(updatedCourse.getCode())) {
                courses.set(i, updatedCourse);
                break;
            }
        }
    }

    // Chức năng 5: Xóa khóa học (Bẫy nghiệp vụ: studentCount must be 0)
    public boolean deleteByCode(String code) {
        Optional<Course> courseOpt = findByCode(code);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if (course.getStudentCount() == 0) {
                return courses.remove(course);
            }
        }
        return false; // Trả về false nếu không tìm thấy hoặc đã có học viên
    }
}