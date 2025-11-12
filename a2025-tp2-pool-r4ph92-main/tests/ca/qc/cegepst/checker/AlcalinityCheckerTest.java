package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlcalinityCheckerTest {
    @ParameterizedTest
    @CsvSource({
            "6, 1, 80, BICARBONATE_SODIUM, 1600",
            "130, 1, -10, SODIUM_BISULFATE, 20",
            "90, 0, 0, , 0"
    })
    void testChecker(double alcalinity, int expectedSteps, double expectedAdjustment,
                     Product expectedProduct, double expectedGrams) {
        Checker alcalinityChecker = new AlcalinityChecker();
        Pool pool = new Pool(10000, 7, 2, alcalinity, 200);
        List<TreatmentStep> steps =  alcalinityChecker.check(pool);
        assertNotNull(steps);
        assertEquals(expectedSteps, steps.size());
        if (expectedSteps > 0) {
            TreatmentStep step = steps.getFirst();
            assertNotNull(step);
            assertEquals(Parameter.ALKALINITY, step.parameter());
            assertEquals(expectedAdjustment, step.adjustment());
            assertEquals(expectedProduct, step.product());
            assertEquals(expectedGrams, step.quantityInGrams());
        }
    }

    /*
    @Test
    void testCheckWhenAlcalinityLessThanMin() {
        Pool pool = new Pool(10000, 7, 2, 6, 200);
        List<TreatmentStep> steps =  alcalinityChecker.check(pool);
        assertNotNull(steps);
        assertEquals(1, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.ALKALINITY, step.parameter());
        assertEquals(80, step.adjustment());
        assertEquals(Product.BICARBONATE_SODIUM, step.product());
        assertEquals(1600, step.quantityInGrams());
    }

    @Test
    void testCheckWhenAlcalinityGreaterThanMin() {
        Pool pool = new Pool(10000, 7, 2, 130, 200);
        List<TreatmentStep> steps =  alcalinityChecker.check(pool);
        assertNotNull(steps);
        assertEquals(1, steps.size());
        TreatmentStep step = steps.getFirst();
        assertNotNull(step);
        assertEquals(Parameter.ALKALINITY, step.parameter());
        assertEquals(-10, step.adjustment());
        assertEquals(Product.SODIUM_BISULFATE, step.product());
        assertEquals(20, step.quantityInGrams());
    }

    @Test
    void testCheckWhenAlcalinityTarget() {
        Pool pool = new Pool(10000, 7, 2, 90, 200);
        List<TreatmentStep> steps =  alcalinityChecker.check(pool);
        assertNotNull(steps);
        assertEquals(0, steps.size());
    }
     */
}
