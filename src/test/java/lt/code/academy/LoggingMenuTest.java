package lt.code.academy;

import lt.code.academy.data.Student;
import lt.code.academy.data.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoggingMenuTest {
    @Mock
    Scanner scanner;
    @Mock
    Examination examination;
    @Mock
    Evaluation evaluation;
    @Mock
    Grade grade;
    @InjectMocks
    LoggingMenu loggingMenu;

    @Test
    void testStudentMenuActionOne(){
        Student student = mock(Student.class);
        String action = "1";
        loggingMenu.studentMenuAction(action, student);
        verify(examination, times(1)).takeExam(student);
    }
    @Test
    void testStudentMenuActionTwo() {
        Student student = mock(Student.class);
        String action = "2";
        loggingMenu.studentMenuAction(action, student);
        verify(grade, times(1)).showExamGradeForStudent(student);
    }
    @Test
    void testTeacherMenuActionOne() {
        Teacher teacher = mock(Teacher.class);
        String action = "1";
        loggingMenu.teacherMenuAction(action, teacher);
        verify(examination, times(1)).createExam(teacher);
    }
    @Test
    void testTeacherMenuActionTwo() {
        Teacher teacher = mock(Teacher.class);
        String action = "2";
        loggingMenu.teacherMenuAction(action, teacher);
        verify(evaluation, times(1)).evaluationMain(scanner);
    }
    @Test
    void testTeacherMenuActionThree() {
        Teacher teacher = mock(Teacher.class);
        String action = "3";
        loggingMenu.teacherMenuAction(action, teacher);
        verify(grade, times(1)).showGradesForTeacher();
    }


}