package ca.qc.cegepst;

public class Tester {
    private final Pool pool;
    public Tester(Pool pool) {
        this.pool = pool;
    }

    public void test() {
        // Analyser les données de la piscine
        Analyzer analyzer = new Analyzer();
        TreatmentPlan plan = analyzer.analyze(pool);

        // Afficher le plan de traitement
        TreatmentFormatter formatter = new TreatmentFormatter();
        System.out.println("----------------------------------");
        System.out.print(formatter.formatPlan(plan));
    }
}
