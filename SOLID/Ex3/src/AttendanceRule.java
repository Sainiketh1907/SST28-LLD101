public class AttendanceRule implements EligibilityRule {
    private final int minAttendancePct;

    public AttendanceRule(int minAttendancePct) {
        this.minAttendancePct = minAttendancePct;
    }

    @Override
    public boolean isSatisfied(StudentProfile student) {
        return student.attendancePct >= minAttendancePct;
    }

    @Override
    public String failureReason() {
        return "attendance below " + minAttendancePct;
    }
}
