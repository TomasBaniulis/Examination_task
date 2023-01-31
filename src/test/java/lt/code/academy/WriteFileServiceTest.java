package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WriteFileServiceTest {
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private WriteFileService service;

    File createFile () throws IOException {
    File file = new File("testFile.json");
    if(!file.exists()) {
        file.createNewFile();
    }
    return file;
    }

    @Test
    void testCreateFileWhenFileDoNotExist() throws IOException {


    }

    @Test
    void testWriteFileWhenCanNotWriteToFile() throws IOException {

        String fileName = "test.json";
        List<Integer>numbers = List.of(1,2,3,4);
        service.writeToFile("test.json",numbers);
        File file = new File("testasA.json");
        if (!file.exists()) {
            file.createNewFile();
        }

        service.writeToFile(fileName, numbers);

        verify(mapper, times(1)).writeValue(file, eq(numbers));
    }

}