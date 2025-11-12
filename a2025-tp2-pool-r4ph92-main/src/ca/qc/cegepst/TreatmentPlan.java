package ca.qc.cegepst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TreatmentPlan {

    private final List<TreatmentStep> steps = new ArrayList<>();

    public void addSteps(List<TreatmentStep> steps) {
        this.steps.addAll(steps);
    }

    public boolean isEmpty() {
        return steps.isEmpty();
    }

    public List<TreatmentStep> getSteps() {
        return Collections.unmodifiableList(steps);
    }
}
