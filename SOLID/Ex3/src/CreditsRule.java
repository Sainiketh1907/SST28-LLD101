public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public boolean isSatisfied(StudentProfile student) {
        return student.earnedCredits >= minCredits;
    }

    @Override
    public String failureReason() {
        return "credits below " + minCredits;
    }
}
