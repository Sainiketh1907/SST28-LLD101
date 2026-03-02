public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        
        // Create concrete dependencies
        Rubric rubric = new Rubric();
        PlagiarismCheckable plagiarismChecker = new PlagiarismChecker();
        Gradable codeGrader = new CodeGrader();
        ReportWritable reportWriter = new ReportWriter();
        
        // Inject dependencies into the pipeline
        EvaluationPipeline pipeline = new EvaluationPipeline(rubric, plagiarismChecker, codeGrader, reportWriter);
        
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        pipeline.evaluate(sub);
    }
}
