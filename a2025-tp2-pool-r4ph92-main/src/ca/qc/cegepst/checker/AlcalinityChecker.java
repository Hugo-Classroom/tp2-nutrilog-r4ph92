package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;

import java.util.ArrayList;
import java.util.List;

import static ca.qc.cegepst.Analyzer.EPS;

public class AlcalinityChecker implements Checker {

    private static final double ALK_MIN = 80.0;  // ppm
    private static final double ALK_MAX = 120.0;
    private static final double ALK_STEP = 10.0;
    private static final double ALK_UP_GR_PER_10M3 = 200.0;
    private static final double ALK_DOWN_GR_PER_10M3 = 20.0;

    @Override
    public List<TreatmentStep> check(Pool pool) {
        List<TreatmentStep> treatmentSteps = new ArrayList<>();
        double liters = pool.liters();
        double alk = pool.alkalinity();

        if (alk < ALK_MIN - EPS) {
            double delta = ALK_MIN - alk;
            int steps = (int) Math.ceil(delta / ALK_STEP);

            double grams = steps * ALK_UP_GR_PER_10M3 * (liters / 10_000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.ALKALINITY,
                    steps * ALK_STEP,
                    Product.BICARBONATE_SODIUM,
                    grams
            ));

        } else if (alk > ALK_MAX + EPS) {
            double delta = alk - ALK_MAX;
            int steps = (int) Math.ceil(delta / ALK_STEP);

            double grams = steps * ALK_DOWN_GR_PER_10M3 * (liters / 10_000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.ALKALINITY,
                    -steps * ALK_STEP,
                    Product.SODIUM_BISULFATE,
                    grams
            ));
        }
        return treatmentSteps;
    }
}
