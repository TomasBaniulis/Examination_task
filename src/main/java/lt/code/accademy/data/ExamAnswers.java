package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class ExamAnswers extends Exam{

    Map<Integer, Integer> answers;

    public ExamAnswers() {
    }

    public ExamAnswers(String teacherId, String teacherName, String teacherSurname, String password, String examId, String examName, LocalDate examDate, Map<Integer, Integer> answers) {
        super(teacherId, teacherName, teacherSurname, password, examId, examName, examDate);
        this.answers = answers;
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, Integer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "ExamAnswers{" +
                "answers=" + answers +
                ", examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherSurname='" + teacherSurname + '\'' +
                '}';
    }
}