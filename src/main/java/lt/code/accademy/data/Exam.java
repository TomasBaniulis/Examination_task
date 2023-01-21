package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class Exam extends Teacher {

    String examId;
    String examName;
    LocalDate examDate;

    public Exam() {
    }

    public Exam(String subjectName, String teacherId, String teacherName, String password, String examId, String examName, LocalDate examDate) {
        super( subjectName, teacherId, teacherName, password);
        this.examId = examId;
        this.examName = examName;
        this.examDate = examDate;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
