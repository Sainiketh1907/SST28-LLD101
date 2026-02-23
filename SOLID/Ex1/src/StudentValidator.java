import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentValidator {
    private final Set<String> validPrograms;
    StudentValidator(Set<String> validPrograms){
        this.validPrograms = validPrograms;
    }
    public List<String> validate(String name , String email , String phone , String program){
        List<String> errors = new ArrayList<>();
        if (name.isBlank()) errors.add("name is required");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!validPrograms.contains(program)) errors.add("program is invalid");
        return errors;
    }
}
