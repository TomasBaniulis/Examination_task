package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class StudentAnswers extends Student{
    String examId;
    Map <Integer, Integer> answers;

    public StudentAnswers() {
    }

    public StudentAnswers( String id, String name, String examId, Map<Integer, Integer> answers) {
        super(id,name);
        this.examId = examId;
        this.answers = answers;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, Integer> answers) {
        this.answers = answers;
    }
}
