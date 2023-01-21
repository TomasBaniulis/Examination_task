package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class ExamQuestions extends Exam {
    Map<Integer, String> questions;

    public ExamQuestions() {
    }

    public ExamQuestions(String teacherId, String teacherName, String teacherSurname, String password, String examId, String examName, LocalDate examDate, Map<Integer, String> questions) {
        super(teacherId, teacherName, teacherSurname, password, examId, examName, examDate);
        this.questions = questions;
    }

    public Map<Integer, String> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, String> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ExamQuestions{" +
                "questions=" + questions +
                ", examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                ", teacherName='" + teacherName + '\'' +
                ", teacherSurname='" + teacherSurname + '\'' +
                '}';
    }
}
