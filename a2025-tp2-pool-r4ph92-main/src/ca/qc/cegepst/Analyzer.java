package ca.qc.cegepst;

import ca.qc.cegepst.checker.*;

public class Analyzer {
    public static final double EPS = 1e-9;
    public static final double PH_GR_PER_10M3 = 94.0;
    public static final double PH_STEP = 0.2;

    private final Checker chlorineChecker = new ChlorineChecker();
    private final Checker alcalinityChecker = new AlcalinityChecker();
    private final Checker phChecker = new PhChecker();
    private final Checker hardnessChecker = new HardnessChecker();

    public TreatmentPlan analyze(Pool pool) {
        TreatmentPlan plan = new TreatmentPlan();

        // 1) CHLORE
        plan.addSteps(chlorineChecker.check(pool));

        // 2) ALCALINITÉ
        plan.addSteps(alcalinityChecker.check(pool));

        // 3) pH
        plan.addSteps(phChecker.check(pool));

        // 4) DURETÉ
        plan.addSteps(hardnessChecker.check(pool));

        return plan;
    }
}
