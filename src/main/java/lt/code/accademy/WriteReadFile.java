package lt.code.accademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class WriteReadFile {

    ObjectMapper mapper;

    public WriteReadFile(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    void writeToFile (String fileName, Object object){
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
