package ca.qc.cegepst.checker;

import ca.qc.cegepst.Pool;
import ca.qc.cegepst.TreatmentStep;

import java.util.List;

public interface Checker {
    List<TreatmentStep> check(Pool pool);
}
