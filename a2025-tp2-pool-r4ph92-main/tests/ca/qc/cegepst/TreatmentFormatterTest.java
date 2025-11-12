package ca.qc.cegepst;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TreatmentFormatterTest {
    @Test
    void testFormatterWithEmpty() {
        TreatmentPlan plan = new TreatmentPlan();
        TreatmentFormatter formatter = new TreatmentFormatter();
        formatter.formatPlan(plan);
    }

    @Test
    void testFormatter() {
        TreatmentPlan plan = new TreatmentPlan();
        plan.addSteps(List.of(new TreatmentStep(Parameter.PH, 12.0, Product.SODIUM_BISULFATE, 111.4)
        , new TreatmentStep(Parameter.ALKALINITY, -1.8, Product.SODIUM_BISULFATE, 1111.4)));
        TreatmentFormatter formatter = new TreatmentFormatter();
        formatter.formatPlan(plan);
    }
}
