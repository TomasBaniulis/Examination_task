package lt.code.academy.data;

import java.util.Map;

public class ExamGrades extends Exam {

    Map<Student, Integer> marks;

    public ExamGrades() {
    }

    public ExamGrades(String subjectName, String teacherName, String examId, String examName, String examDate, Map<Student, Integer> marks) {
        super(subjectName, teacherName, examId, examName, examDate);
        this.marks = marks;
    }

    public Map<Student, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<Student, Integer> marks) {
        this.marks = marks;
    }
}
