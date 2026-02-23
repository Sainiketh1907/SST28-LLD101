public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public boolean isSatisfied(StudentProfile student) {
        return student.disciplinaryFlag == LegacyFlags.NONE;
    }

    @Override
    public String failureReason() {
        return "disciplinary flag present";
    }
}
