import java.util.*;

public class OnboardingService {
    private final Parser parser;
    private final StudentValidator validator;
    private final StudentRepo repo;
    private final StudentPrinter printer;

    public OnboardingService(Parser parser ,  StudentValidator validator, StudentRepo repo, StudentPrinter printer) { 
        this.parser = parser;
        this.validator = validator;
        this.repo = repo;
        this.printer = printer;
     }
    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name","");
        String email = kv.getOrDefault("email","");
        String phone = kv.getOrDefault("phone","");
        String program = kv.getOrDefault("program","");

        List<String> errors = validator.validate(name , email , phone , program);
        if (errors.isEmpty()) {
            String id = IdUtil.nextStudentId(repo.count());
            StudentRecord record = new StudentRecord(id, name, email, phone, program);
            repo.save(record);
            printer.printSuccess(record, repo.count());
        }
        else{
            printer.printErrors(errors);
        }
    }
}
