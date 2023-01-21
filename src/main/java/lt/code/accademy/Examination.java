package lt.code.accademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lt.code.accademy.data.Exam;
import lt.code.accademy.data.ExamQuestions;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Examination {

    private final ObjectMapper mapper;
    private final Scanner scanner;

    private final Faker faker;

    public Examination() {
        mapper = new ObjectMapper();
        scanner = new Scanner(System.in);
        faker = new Faker();
    }

    public String createExamFileName (Exam exam){
        String extension = ".json";
        String fileName = String.format(exam.getExamId()+extension);
        return fileName;
    }

    LocalDate getDate (){
        while (true){
            try{
                System.out.println("Enter exam date yyyy.MM.dd :");
                String date = scanner.nextLine();
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                return localDate;

            }catch (DateTimeException e) {
                System.out.println("Wrong date format. Try again!");
            }
        }
    }

    public void writeToFile (Exam exam, Object object){
        File file = new File(createExamFileName(exam));
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file,object);
        }catch (IOException e){
            System.out.println("Can't create file:" + e.getMessage());
        }
    }

    public ExamQuestions createExam (Teacher teacher){
        System.out.println("Generating exam id");
        String examId =faker.idNumber().toString();
        System.out.println("Exam id number:" + examId);
        System.out.println("Enter exam name");
        String examName = scanner.nextLine();
        LocalDate date = getDate();



        ExamQuestions exam = new ExamQuestions();
        return exam;
    }

}

