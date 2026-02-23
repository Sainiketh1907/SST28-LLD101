public class CgrRule implements EligibilityRule {
    private final double minCgr;
    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }
    @Override
    public boolean isSatisfied(StudentProfile student) {
        return student.cgr >= minCgr;
    }

    @Override
    public String failureReason() {
        return "CGR below " + minCgr;
    }
}
