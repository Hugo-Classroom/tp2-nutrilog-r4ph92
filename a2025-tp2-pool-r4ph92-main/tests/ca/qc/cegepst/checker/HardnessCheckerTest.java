package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HardnessCheckerTest {
    private final Checker hardnessChecker = new HardnessChecker();
    @Test
    void testWhenHardnessIsBelowMinimum() {
        Pool pool = new Pool(55000, 7.2, 3, 90, 200);
        List<TreatmentStep> steps =  hardnessChecker.check(pool);
        assertNotNull(steps);
        assertEquals(1, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.HARDNESS, step.parameter());
        assertEquals(50, step.adjustment());
        assertEquals(Product.CALCIUM_CHLORIDE, step.product());
        assertEquals(4125.0, step.quantityInGrams());

    }
}
