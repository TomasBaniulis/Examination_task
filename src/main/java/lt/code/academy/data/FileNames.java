package lt.code.academy.data;

public enum FileNames {
    STUDENTS_FILE ("students.json"),
    TEACHERS_FILE ("HD.users.tomasbaniulis.Desktop.JAVA. Java files.Codeacademy.exam_app.teachers.json"),
    JSON_EXTENSION (".json"),
    ANSWERS_FILES_LIST_FILE_EXTENSION ("answerList.json"),
    GRADES_FILE_EXTENSION ("grades.json");

    private final String string;

    FileNames(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
