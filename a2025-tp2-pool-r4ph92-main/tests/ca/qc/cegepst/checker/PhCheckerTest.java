package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PhCheckerTest {
    private final Checker phChecker = new PhChecker();
    @Test
    void testWhenPhIsBelowMinimum() {
        Pool pool = new Pool(55000, 6, 3, 90, 200);
        List<TreatmentStep> steps =  phChecker.check(pool);
        assertNotNull(steps);
        assertEquals(1, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.PH, step.parameter());
        assertEquals(1.4000000000000001, step.adjustment());
        assertEquals(Product.SODIUM_CARBONATE, step.product());
        assertEquals(3619.0, step.quantityInGrams());
    }

    @Test
    void testWhenPhIsAboveMaximum() {
        Pool pool = new Pool(55000, 9, 3, 90, 200);
        List<TreatmentStep> steps =  phChecker.check(pool);
        assertNotNull(steps);
        assertEquals(1, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.PH, step.parameter());
        assertEquals(-1.6, step.adjustment());
        assertEquals(Product.SODIUM_BISULFATE, step.product());
        assertEquals(4136.0, step.quantityInGrams());


    }
}
