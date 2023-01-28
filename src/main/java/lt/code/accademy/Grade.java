package lt.code.accademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lt.code.accademy.data.FileNames;
import lt.code.accademy.data.Student;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Grade {

    private final ObjectMapper mapper;
    private final Scanner scanner;

    public Grade(ObjectMapper mapper, Scanner scanner) {
        this.mapper = mapper;
        this.scanner = scanner;
    }

    void showGradesForTeacher (){
        Map<Student, Integer> grades;
        System.out.println("Enter exam id to generate grades list");
        String examId = scanner.nextLine();
        String examGradesFile = examId + FileNames.GRADES_FILE_EXTENSION;
        File file = new File(examGradesFile);
        if (!file.exists()){
            System.out.printf("Exam id: %s, grades file doesn't exist! please evaluate exam first! ");
            return;
        }
        try{
            grades = mapper.readValue(file, new TypeReference<>() {});
            for (Map.Entry<Student, Integer> grade: grades.entrySet()){
                System.out.printf("%s grade: %s%n", grade.getKey().getName(), grade.getValue());
            }
        }catch (IOException e){
            System.out.println("can't read grades file:" + e);
        }
    }

    void showExamGradeForStudent (Student student){
        System.out.println("Enter exam id to see your grade");
        String examId = scanner.nextLine();
        Map<Student, Integer> grades = new HashMap<>();
        String examGradesFile = examId + FileNames.GRADES_FILE_EXTENSION;
        File file = new File(examGradesFile);
        if (!file.exists()) {
            System.out.printf("Exam id: %s, hasn't been evaluated yet!%n", examId);
            return;
        }
        try{
            grades = mapper.readValue(file, new TypeReference<>() {});
            for (Map.Entry<Student, Integer> grade: grades.entrySet()) {
                if (grade.getKey().getId().equals(student.getId())) {
                    System.out.printf("Your grade: %s%n", grade.getValue());
                    return;
                }
            }
            System.out.println("You didn't take this exam");
        }catch (IOException e){
            System.out.println("Can't read grades file:" + e);
        }
    }

}
