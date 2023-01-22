package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class Exam extends Teacher {

    String examId;
    String examName;
    LocalDate examDate;

    Map <Integer, String> questions;
    Map <Integer, Integer> rightAnswers;


    public Exam(String subjectName, String teacherId, String teacherName, String password, String examId, String examName, LocalDate examDate, Map<Integer, String> questions, Map<Integer, Integer> rightAnswers) {
        super(subjectName, teacherId, teacherName, password);
        this.examId = examId;
        this.examName = examName;
        this.examDate = examDate;
        this.questions = questions;
        this.rightAnswers = rightAnswers;
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

    public Map<Integer, String> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, String> questions) {
        this.questions = questions;
    }

    public Map<Integer, Integer> getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Map<Integer, Integer> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }
}
