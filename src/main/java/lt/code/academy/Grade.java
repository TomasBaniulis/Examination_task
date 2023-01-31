package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.code.academy.data.FileNames;
import lt.code.academy.data.Student;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Grade {

    private final ObjectMapper mapper;
    private final Scanner scanner;

    public Grade(ObjectMapper mapper, Scanner scanner) {
        this.mapper = mapper;
        this.scanner = scanner;
    }

    void showGradesForTeacher (){
        Map<Student, Integer> grades = getGrades();
        if (grades == null){
            System.out.println("There is no exam evaluation yet!");
            return;
        }
        for (Map.Entry<Student, Integer> grade: grades.entrySet()){
            System.out.printf("%s grade: %s%n", grade.getKey().getName(), grade.getValue());
        }
    }

    void showExamGradeForStudent (Student student){
        Map<Student, Integer> grades = getGrades();
        if (grades == null){
            System.out.println("There is no exam evaluation yet!");
            return;
        }
            for (Map.Entry<Student, Integer> grade: grades.entrySet()) {
                if (grade.getKey().getId().equals(student.getId())) {
                    System.out.printf("Your grade: %s%n", grade.getValue());
                    return;
                }
            }
            System.out.println("You didn't take this exam");
    }

    private Map <Student, Integer> getGrades () {
        Map<Student, Integer> grades;
        System.out.println("Enter exam id");
        String examId = scanner.nextLine();
        String examGradesFile = examId + FileNames.GRADES_FILE_EXTENSION;
        File file = new File(examGradesFile);
        if (!file.exists()) {
            return null;
        }
        try {
            grades = mapper.readValue(file, new TypeReference<>() {
            });
            return grades;
        } catch (IOException e) {
            System.out.println("Can't read grades file:" + e);
        }
        return null;
    }

}
