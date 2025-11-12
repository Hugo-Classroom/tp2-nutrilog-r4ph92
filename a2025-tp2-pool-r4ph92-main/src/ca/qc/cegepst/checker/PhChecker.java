package ca.qc.cegepst.checker;

import ca.qc.cegepst.Parameter;
import ca.qc.cegepst.Pool;
import ca.qc.cegepst.Product;
import ca.qc.cegepst.TreatmentStep;

import java.util.ArrayList;
import java.util.List;

import static ca.qc.cegepst.Analyzer.EPS;
import static ca.qc.cegepst.Analyzer.PH_GR_PER_10M3;
import static ca.qc.cegepst.Analyzer.PH_STEP;

public class PhChecker implements Checker {

    private static final double PH_MIN = 7.2;
    private static final double PH_MAX = 7.6;

    @Override
    public List<TreatmentStep> check(Pool pool) {
        List<TreatmentStep> treatmentSteps = new ArrayList<>();
        double liters = pool.liters();
        double ph = pool.ph();

        if (ph < PH_MIN - EPS) {
            double delta = PH_MIN - ph;
            int steps = (int) Math.ceil(delta / PH_STEP);

            double grams = steps * PH_GR_PER_10M3 * (liters / 10_000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.PH,
                    steps * PH_STEP,
                    Product.SODIUM_CARBONATE,
                    grams
            ));

        } else if (ph > PH_MAX + EPS) {
            double delta = ph - PH_MAX;
            int steps = (int) Math.ceil(delta / PH_STEP);

            double grams = steps * PH_GR_PER_10M3 * (liters / 10_000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.PH,
                    -steps * PH_STEP,
                    Product.SODIUM_BISULFATE,
                    grams
            ));
        }
        return treatmentSteps;
    }
}
