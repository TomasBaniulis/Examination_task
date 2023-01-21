package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class ExamResults extends Exam{

    Map<String, Integer> results;

    public ExamResults() {
    }

    public ExamResults(String subjectName, String teacherId, String teacherName, String password, String examId, String examName, LocalDate examDate, Map<String, Integer> results) {
        super(subjectName, teacherId, teacherName, password, examId, examName, examDate);
        this.results = results;
    }
}
