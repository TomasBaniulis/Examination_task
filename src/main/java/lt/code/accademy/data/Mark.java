package lt.code.accademy.data;

import java.time.LocalDate;

public class Mark extends Exam{

    int mark;

    public Mark() {
    }

    public Mark(String subjectName, String teacherId, String teacherName, String password, String examId, String examName, LocalDate examDate, int mark) {
        super(subjectName, teacherId, teacherName, password, examId, examName, examDate);
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                ", examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", examDate=" + examDate +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
