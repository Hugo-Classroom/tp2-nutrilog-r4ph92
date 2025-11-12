package ca.qc.cegepst;

public enum Parameter {
    PH("pH"),
    FREE_CHLORINE("Chlore libre"),
    ALKALINITY("Alcalinité totale"),
    HARDNESS("Dureté calcique");

    private final String label;

    Parameter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
