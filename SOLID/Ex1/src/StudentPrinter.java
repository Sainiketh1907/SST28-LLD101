import java.util.List;

public class StudentPrinter {
    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printSuccess(StudentRecord student, int total) {
        System.out.println("OK: created student " + student.id);
        System.out.println("Saved. Total students: " + total);
        System.out.println("CONFIRMATION:");
        System.out.println(student);
    }

    public void printErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }

}
