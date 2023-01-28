package lt.code.accademy.data;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class Student  {

    String id;
    String name;
    String password;

    public Student() {
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student (String string){
        String [] values = string.split(" ");
        this.id = values[1].trim();
        this.name = values[3].trim() + " " + values[4].trim();
        this.password = values[values.length-1].trim();
    }

    public Student(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonValue
    public String toString() {
        return "Student{" +
                "id: " + id +
                " name: " + name +
                " password: " + password + " " +
                '}';
    }

}
