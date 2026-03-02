public class EvaluationPipeline {
    // DIP: high-level module depends on abstractions, not concretes
    private final Rubric rubric;
    private final PlagiarismCheckable plagiarismChecker;
    private final Gradable codeGrader;
    private final ReportWritable reportWriter;

    public EvaluationPipeline(Rubric rubric, PlagiarismCheckable plagiarismChecker, 
                              Gradable codeGrader, ReportWritable reportWriter) {
        this.rubric = rubric;
        this.plagiarismChecker = plagiarismChecker;
        this.codeGrader = codeGrader;
        this.reportWriter = reportWriter;
    }

    public void evaluate(Submission sub) {
        int plag = plagiarismChecker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = codeGrader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportWriter.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
