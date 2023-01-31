package lt.code.academy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class WriteFileService {

    ObjectMapper mapper;

    public WriteFileService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    void writeToFile (String fileName, Object object){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(fileName);
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file,object);
        }catch (IOException e){
            System.out.printf("Can't create file %s: %s%n:", fileName, e.getMessage());
        }
    }
    <T> T readFile (String fileName, Function<File, T> function){
         try{
            File file = new File(fileName);
                T t = function.apply(file);
                return t;
            } catch(Exception e) {
                System.out.printf("Can't read %s file: %s%n ",fileName, e.getMessage());
         }
         return null;
     }
}
