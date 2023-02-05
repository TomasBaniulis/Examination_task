package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.academy.data.Exam;
import lt.code.academy.data.Teacher;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ExaminationTest {
    @Mock
    Teacher teacher;
    @Mock
    Exam exam;
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


}