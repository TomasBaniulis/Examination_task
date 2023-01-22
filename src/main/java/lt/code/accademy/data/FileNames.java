package lt.code.accademy.data;

public enum FileNames {
    STUDENTS_FILE ("students.json"),
    TEACHERS_FILE ("teachers.json"),
    JSON_EXTENSION (".json"),
    ANSWERS_FILES_LIST_FILE_EXTENSION ("answerList.json"),
    GRADES_FILE_EXTENSION ("grades.json");

    private final String string;

    FileNames(String string) {
        this.string = string;
    }
}
