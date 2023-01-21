package lt.code.accademy.data;

import java.time.LocalDate;
import java.util.Map;

public class ExamResults extends Exam{

    Map<String, Integer> results;

    public ExamResults() {
    }

    public ExamResults(String teacherId, String teacherName, String teacherSurname, String password, String examId, String examName, LocalDate examDate, Map<String, Integer> results) {
        super(teacherId, teacherName, teacherSurname, password, examId, examName, examDate);
        this.results = results;
    }
}
