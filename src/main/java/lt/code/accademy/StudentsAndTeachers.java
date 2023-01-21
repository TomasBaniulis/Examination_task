package lt.code.accademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.accademy.data.Student;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentsAndTeachers {


    String studentFile = "students.json";
    String teachersFile = "teachers.json";





    void generateStudents (ObjectMapper mapper, Faker faker){
        Map<String, Student> students = new HashMap<>();
        int counter = 1;
        for (int i=0; i<10; i++){
            String id = String.valueOf(counter);
            String name = faker.name().fullName();
            String password = faker.company().buzzword();
            students.put(id, new Student(id, name, password));
        }
        try{
            File file = new File(studentFile);
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, students);

        }catch (IOException e){
            System.out.printf("Can't create % file: %s %n", studentFile, e.getMessage());
        }
    }

    void generateTeachers (ObjectMapper mapper, Faker faker){
        Map<String, Teacher> teachers = new HashMap<>();
        int counter = 1;
        for (int i=0; i<10; i++){
            String subject = "Chuck Norris History";
            String name = faker.superhero().name();
            String password = faker.company().profession();
            String id = String.valueOf(counter);
            teachers.put(id, new Teacher(subject, id, name, password) );
        }
        try{
            File file = new File(teachersFile);
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, teachers);

        }catch (IOException e){
            System.out.printf("Can't create % file: %s %n", teachersFile, e.getMessage());
        }



    }



}
