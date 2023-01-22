package lt.code.accademy.data;

import java.util.Map;

public class ExamMarks extends Exam {

    Map<Student, Integer> marks;

    public ExamMarks() {
    }

    public ExamMarks(String subjectName, String teacherName, String examId, String examName, String examDate, Map<Student, Integer> marks) {
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
