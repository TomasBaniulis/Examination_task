package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Scanner;

class ExaminationTest {
    @Mock
    private ObjectMapper mapper;
    @Mock
    Scanner scanner;
    @Mock
    Faker faker;
    @Mock
    private WriteFileService writeReadFile;
    @InjectMocks
    Examination examination;

    @Test
    void createExam() {
    }

    @Test
    void takeExam() {
    }

    @Test
    void runQuestions() {
    }

    @Test
    void createListOfStudentAnswerFiles() {
    }

    @Test
    void TestCheckForSecondAttempt() {

    }

    @Test
    void testGetDate() {


    }
}