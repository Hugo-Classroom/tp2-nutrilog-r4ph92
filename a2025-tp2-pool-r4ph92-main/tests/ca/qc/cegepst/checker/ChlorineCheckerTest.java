package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChlorineCheckerTest {
    private final Checker checker = new ChlorineChecker();
    @Test
    void testCheckWhenChlorineAboveMaximum() {
        Pool pool = new Pool(50000, 7.2, 5,90,230);
        List<TreatmentStep> steps = checker.check(pool);
        assertNotNull(steps);
        assertEquals(2, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.PH, step.parameter());
        assertEquals(0.5, step.adjustment());
        assertEquals(Product.SODIUM_BISULFATE, step.product());
        assertEquals(1175.0, step.quantityInGrams());
        step = steps.get(1);
        assertNotNull(step);
        assertEquals(Parameter.PH, step.parameter());
        assertEquals(0.5, step.adjustment());
        assertEquals(Product.SODIUM_CARBONATE, step.product());
        assertEquals(1175.0, step.quantityInGrams());
    }

    @Test
    void testCheckWhenChlorineBelowMinimum() {
        Pool pool = new Pool(50000, 7.2, 1,90,230);
        List<TreatmentStep> steps = checker.check(pool);
        assertNotNull(steps);
        assertEquals(1, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.FREE_CHLORINE, step.parameter());
        assertEquals(1.0, step.adjustment());
        assertEquals(Product.CHLORINE_GRANULES, step.product());
        assertEquals(60.0, step.quantityInGrams());
    }


}