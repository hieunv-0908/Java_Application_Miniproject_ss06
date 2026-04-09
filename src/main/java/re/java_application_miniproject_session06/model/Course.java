package re.java_application_miniproject_session06.model;

import java.time.LocalDate;

public class Course {

    private String code;          // IELTS-6.5
    private String name;          // Tên khóa
    private String level;         // Beginner, Intermediate, Advanced
    private double price;         // Học phí
    private String description;   // Lộ trình học
    private String instructor;    // Giảng viên
    private int duration;         // Thời lượng (giờ hoặc buổi)
    private boolean isFull;       // Đã hết chỗ chưa
    private int studentCount;     // Số học viên
    private LocalDate startDate;  // Ngày khai giảng

    // Constructor
    public Course() {}

    public Course(String code, String name, String level, double price,
                  String description, String instructor, int duration,
                  boolean isFull, int studentCount, LocalDate startDate) {
        this.code = code;
        this.name = name;
        this.level = level;
        this.price = price;
        this.description = description;
        this.instructor = instructor;
        this.duration = duration;
        this.isFull = isFull;
        this.studentCount = studentCount;
        this.startDate = startDate;
    }

    public Course(String s, String businessEnglish, String advanced, double v, String s1, String s2, boolean b, int i) {
    }

    // Getter & Setter
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public boolean isFull() { return isFull; }
    public void setFull(boolean full) { isFull = full; }

    public int getStudentCount() { return studentCount; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}