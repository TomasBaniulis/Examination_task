package lt.code.accademy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.accademy.data.Exam;
import lt.code.accademy.data.ExamAnswers;
import lt.code.accademy.data.ExamQuestions;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Examination {

    Random random = new Random();

    ExamQuestions questions;
    ExamAnswers answers;

    public String createExamFileName (Exam exam){
        String extension = ".json";
        String fileName = String.format(exam.getExamId()+extension);
        return fileName;
    }

    LocalDate getDate (Scanner scanner){
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

    public void writeToFile (ObjectMapper mapper, String fileName, Object object){
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

    public ExamQuestions createExamQuestions (Faker faker, Scanner scanner, Teacher teacher){
        System.out.println("Generating exam id");
        String examId =faker.idNumber().toString();
        System.out.println("Exam id number:" + examId);
        System.out.println("Enter exam name");
        String examName = scanner.nextLine();
        LocalDate date = getDate(scanner);
        Map<Integer, String> questions = new HashMap<>();
        int counter = 1;
        for (int i = 0; i<10; i++){
            String question = faker.chuckNorris().toString();
            questions.put(counter, question);
            counter++;
        }
        System.out.println("Questions have been generated");
        return new ExamQuestions(teacher.getSubjectName(), teacher.getTeacherId(), teacher.getTeacherName(),
                teacher.getPassword(), examId, examName, date, questions);
    }

    public ExamAnswers createExam (Teacher teacher, Faker faker, Scanner scanner){
        ExamQuestions exam = createExamQuestions(faker, scanner, teacher);
        Map<Integer, Integer> answers = new HashMap<>();
        int counter =1;
        for (int i = 0; i<10; i++){
            int answer = random.nextInt(1,3);
            answers.put(counter, answer);
            counter++;
        }
        return new ExamAnswers(exam.getSubjectName(), exam.getExamId(), exam.getTeacherName(), exam.getPassword(),
                exam.getExamId(), exam.getExamName(), exam.getExamDate(), answers);
    }

    void takeExam (Scanner scanner){
        Map<String, ExamQuestions> exam = new HashMap<>();
        System.out.println( "Enter exam id");
        String examId = scanner.nextLine();
    }

    void runQuestions (ExamQuestions examQuestions, Scanner scanner){
        Map <Integer, String> questions = examQuestions.getQuestions();
        int answer;
        for (Map.Entry<Integer, String> question: questions.entrySet()){
            System.out.printf("Question no. %s%n", question.getKey());
            System.out.println(question.getValue());
            answer = getAnswer(scanner);
        }
    }

    int getAnswer (Scanner scanner){
        int answer;
        while (true) {
            System.out.println(" [1] TRUE     [2] NOT TRUE");
            String input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    answer = 1;
                    return answer;
                }
                case "2" -> {
                    answer = 2;
                    return answer;
                }
            }
        }
    }


}

