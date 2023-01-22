package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class StudentAnswers extends Exam{

    String studentId;

    Map <Integer, Integer> studentAnswers;

    public StudentAnswers() {
    }

    public StudentAnswers(String subjectName, String teacherId, String teacherName, String password, String examId,
                          String examName, LocalDate examDate, String studentId, Map<Integer, Integer> studentAnswers) {
        super(subjectName, teacherId, teacherName, password, examId, examName, examDate);
        this.studentId = studentId;
        this.studentAnswers = studentAnswers;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Map<Integer, Integer> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Map<Integer, Integer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }


}
