package lt.code.accademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import lt.code.accademy.data.Exam;
import lt.code.accademy.data.Student;
import lt.code.accademy.data.StudentAnswers;
import lt.code.accademy.data.Teacher;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Examination {
    Scanner scanner = new Scanner(System.in);
    Faker faker = new Faker();
    ObjectMapper mapper = new ObjectMapper();

    Random random = new Random();

    public String createFileName (String name){
        String fileName = name+".json";
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
    public void writeToFile (String fileName, Object object){
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
    void createExam (Teacher teacher){
        System.out.println("Generating exam id");
        String examId =faker.code().ean8();
        System.out.println("Exam id number:" + examId);
        String examName = faker.commerce().material();
        System.out.println(examName);
        LocalDate date = getDate();
        String dateString = date.toString();
        Map<Integer, String> questions = new HashMap<>();
        Map<Integer, Integer> answers = new HashMap<>();
        int counter = 1;
        for (int i = 0; i<10; i++){
            String question = faker.chuckNorris().fact();
            questions.put(counter, question);
            int answer = random.nextInt(1,3);
            answers.put(counter,answer);
            counter++;
        }
        System.out.println("Exam has been generated");
        Exam exam = new Exam(teacher.getSubjectName(), teacher.getTeacherName(),
                examId, examName, dateString, questions, answers);
        String fileName = exam.getExamId()+".json";
        writeToFile(fileName, exam);
    }

    void takeExam (Student student){
        System.out.println("Enter exam id");
        String id = scanner.nextLine();
        String examFileName = id+".json";
        File examFile = new File(examFileName);
        if (!examFile.exists()){
            System.out.println("No such exam id!");
            return;
        }
        try {
            Exam exam = mapper.readValue(examFile, new TypeReference<>() {});
            LocalDate dateNow = LocalDate.now();
            if (!dateNow.equals(LocalDate.parse(exam.getExamDate()))){
                System.out.printf("You can't take exam! Exam date is/was: %s%n", exam.getExamDate() );
                return;
            }
            Map <Integer, Integer> studentAnswers = runQuestions(exam);
            StudentAnswers answers = new StudentAnswers(student.getId(), student.getName(), id, studentAnswers);
            String fileName = id + student.getId() + ".json";
            writeToFile(fileName, answers);
            createListOfStudentAnswerFiles(exam, fileName);
        }catch (IOException e){
            System.out.println("Can't read exam file:" + e.getMessage());
        }
    }
    Map<Integer, Integer> runQuestions (Exam exam){
        Map <Integer, String> questions = exam.getQuestions();
        Map<Integer, Integer> answers = new HashMap<>();
        int answer;
        for (Map.Entry<Integer, String> question: questions.entrySet()){
            System.out.printf("Question no. %s%n", question.getKey());
            System.out.println(question.getValue());
            answer = random.nextInt(1,3);
            System.out.println("Answer:" + answer);
            answers.put(question.getKey(), answer);
        }
        System.out.println("You have finished your exam!");
        return answers;
    }

    void createListOfStudentAnswerFiles (Exam exam, String name) {
        List<String > fileNames = new ArrayList<>();
        String fileName = exam.getExamId() + "answerList.json";
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
                fileNames.add(name);
                mapper.writeValue(file, fileNames);
                return;
            }
            fileNames = mapper.readValue(file, new TypeReference<List<String>>() {});
            fileNames.add(name);

            mapper.writeValue(file, fileNames);

        }catch (IOException e){
            System.out.println("Cant create file:" + e.getMessage());
        }
    }
}

