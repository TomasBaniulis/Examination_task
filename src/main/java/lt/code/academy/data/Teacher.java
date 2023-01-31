package lt.code.academy.data;

public class Teacher extends Subject {
    String teacherId;
    String teacherName;
    String password;

    public Teacher() {
    }

    public Teacher(String subjectName, String teacherName) {
        super(subjectName);
        this.teacherName = teacherName;
    }
    public Teacher(String subjectName, String teacherId, String teacherName, String password) {
        super(subjectName);
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.password = password;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
