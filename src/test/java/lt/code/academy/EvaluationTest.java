package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.academy.data.Exam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EvaluationTest {
    @Mock
    private ObjectMapper mapper;
    @Mock
    Scanner scanner;
    @Mock
    Faker faker;
    @Mock
    private WriteFileService writeReadFile;
    @Mock
    Examination examination;
    @InjectMocks
    private Evaluation evaluation;

    @Test
    void testWhenComparingAnswersMatch() {
        Map<Integer, Integer> studentAnswers = new HashMap<>();
        studentAnswers.put(1,2);
        Map<Integer, Integer> rightAnswers = new HashMap<>();
       rightAnswers.put(1,2);

        int counter = evaluation.compareAnswers(studentAnswers,rightAnswers);

        assertEquals(1, counter);
    }

    @Test
    void testWhenComparingAnswersDoNotMatch() {
        Map<Integer, Integer> studentAnswers = new HashMap<>();
        studentAnswers.put(1,21);
        Map<Integer, Integer> rightAnswers = new HashMap<>();
        rightAnswers.put(1,2);

        int counter = evaluation.compareAnswers(studentAnswers,rightAnswers);

        assertEquals(0, counter);
    }

    @Test
    void testIfMethodCallsScanner () throws IOException {
        File file = new File("20829582answerList.json");
        evaluation.evaluationMain(scanner);
        verify(scanner, times(1)).nextLine();
    }


}