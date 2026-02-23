import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        Parser parser = new Parser();
        FakeDb db = new FakeDb();
        StudentRepo repo = db;
        StudentValidator validator = new StudentValidator(Set.of("CSE", "AI", "SWE"));
        StudentPrinter printer = new StudentPrinter();
        OnboardingService svc = new OnboardingService(parser, validator, repo , printer);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
