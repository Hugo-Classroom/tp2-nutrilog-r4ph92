package ca.qc.cegepst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyzerTest {
    @Test
    public void testAnalyze() {
        Analyzer analyzer = new Analyzer();
        TreatmentPlan plan = analyzer.analyze(new Pool(55000, 7.2,3,80,260));
        assertNotNull(plan);
        assertTrue(plan.isEmpty());
    }
}
