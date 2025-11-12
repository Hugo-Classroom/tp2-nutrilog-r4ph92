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

public class ChlorineChecker implements Checker {
    private static final double CHLORINE_MIN = 2.0; // ppm
    private static final double CHLORINE_MAX = 4.0; // ppm

    private static final double CHLORINE_STEP = 0.2;
    private static final double CHLORINE_GR_PER_1000L = 0.24;
    private static final double PH_DROP_PER_CHLORINE_STEP = 0.1;

    @Override
    public List<TreatmentStep> check(Pool pool) {
        List<TreatmentStep> treatmentSteps = new ArrayList<>();
        double liters = pool.liters();
        double chlorine = pool.freeChlorine();

        if (chlorine > CHLORINE_MAX + EPS) {

            double delta = chlorine - CHLORINE_MAX;
            int steps = (int) Math.ceil(delta / CHLORINE_STEP);

            double phDecreaseTotal = steps * PH_DROP_PER_CHLORINE_STEP;

            double gramsPer10m3 = (phDecreaseTotal / PH_STEP) * PH_GR_PER_10M3;
            double grams = gramsPer10m3 * (liters / 10_000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.PH,
                    phDecreaseTotal,
                    Product.SODIUM_BISULFATE,
                    grams
            ));

            double gramsRaisePer10m3 = (phDecreaseTotal / PH_STEP) * PH_GR_PER_10M3;
            double gramsRaise = gramsRaisePer10m3 * (liters / 10_000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.PH,
                    +phDecreaseTotal,
                    Product.SODIUM_CARBONATE,
                    gramsRaise
            ));
        } else if (chlorine < CHLORINE_MIN - EPS) {
            // Besoin d'augmenter le chlore : par pas de +0.2 ppm
            double delta = CHLORINE_MIN - chlorine;
            int steps = (int) Math.ceil(delta / CHLORINE_STEP);

            // Quantité: 0.24 g / 1000 L par +0.2 ppm
            double grams = steps * CHLORINE_GR_PER_1000L * (liters / 1000.0);

            treatmentSteps.add(new TreatmentStep(
                    Parameter.FREE_CHLORINE,
                    steps * CHLORINE_STEP,
                    Product.CHLORINE_GRANULES,
                    grams
            ));
        }
        return treatmentSteps;
    }
}
