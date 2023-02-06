package lt.code.academy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.academy.data.Exam;
import lt.code.academy.data.Teacher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ExaminationTest {
    @Mock
    Teacher teacher;
    @Mock
    Exam exam;

    ObjectMapper mapper = new ObjectMapper();
    @Mock
    Scanner scanner;
    @Mock
    Faker faker;
    @Mock
    private WriteFileService writeReadFile;
   // @InjectMocks
    //Examination examination;

    @Test
    void testCheckForSecondAttemptWhenListOfFilesDoesNotExist() {
        Examination examination = new Examination(scanner, faker, mapper, writeReadFile);
        String textOne = "test1.json";
        String textTwo = "test2.json";

        Boolean result = examination.checkForSecondAttempt(textOne, textTwo);

        assertTrue(result);
    }
    @Test
    void testCheckForSecondAttemptWhenListOfFilesExistAndAnswerExists () {
        Examination examination = new Examination(scanner, faker, mapper, writeReadFile);
        String textOne = "test1.json";
        String textTwo = "testFileNames.json";
        Boolean result = examination.checkForSecondAttempt(textOne, textTwo);

        assertFalse(result);
    }

    @Test
    void testCheckForSecondAttemptWhenListOfFilesExistAndAnswerDoesNotExist () {
        Examination examination = new Examination(scanner, faker, mapper, writeReadFile);
        String textOne = "test3.json";
        String textTwo = "testFileNames.json";
        Boolean result = examination.checkForSecondAttempt(textOne, textTwo);

        assertTrue(result);
    }

    @BeforeAll
    static void createAnswerListFile() {
        ObjectMapper ob = new ObjectMapper();
        List<String> fileNames = new ArrayList<>();
        fileNames.add("test1.json");
        fileNames.add("test2.json");
        try {
        File file = new File("testFileNames.json");
        if(!file.exists()){
            file.createNewFile();
        }
        ob.writeValue(file, fileNames);
    } catch (IOException e){
            System.out.println("cant create file:" + e.getMessage());
        }
    }
    @AfterAll
    static void deleteFile (){
        File file = new File("testFileNames.json");
        if (file.exists()){
            file.delete();
        }
    }

}