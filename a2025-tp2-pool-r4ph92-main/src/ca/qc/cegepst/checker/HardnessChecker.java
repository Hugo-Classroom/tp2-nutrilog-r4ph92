package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;

import java.util.ArrayList;
import java.util.List;

import static ca.qc.cegepst.Analyzer.EPS;

public class HardnessChecker implements Checker {

    private static final double HARD_MIN = 250.0; // ppm
    private static final double HARD_STEP = 10.0;
    private static final double HARD_UP_GR_PER_1000L = 15.0;

    @Override
    public List<TreatmentStep> check(Pool pool) {
        List<TreatmentStep> treatmentSteps = new ArrayList<>();

        double liters = pool.liters();
        double hard = pool.hardness();

        if (hard < HARD_MIN - EPS) {
            double delta = HARD_MIN - hard;
            int steps = (int) Math.ceil(delta / HARD_STEP);

            // +10 ppm ↔ 15 g / 1000 L (chlorure de calcium)
            double grams = steps * HARD_UP_GR_PER_1000L * (liters / 1000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.HARDNESS,
                    steps * HARD_STEP,
                    Product.CALCIUM_CHLORIDE,
                    grams
            ));
        }
        return treatmentSteps;
    }
}
