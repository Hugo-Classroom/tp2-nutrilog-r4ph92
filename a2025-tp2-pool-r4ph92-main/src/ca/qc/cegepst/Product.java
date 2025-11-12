package ca.qc.cegepst;

public enum Product {

    SODIUM_BISULFATE("bisulfate de sodium"),
    SODIUM_CARBONATE("carbonate de sodium"),
    CHLORINE_GRANULES("granulés de chlore"),
    CALCIUM_CHLORIDE("chlorure de calcium"),
    BICARBONATE_SODIUM("bicarbonate de sodium");

    private final String label;

    Product(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
