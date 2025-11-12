package ca.qc.cegepst;

import java.text.DecimalFormat;

public class TreatmentFormatter {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.0");

    public String formatPlan(TreatmentPlan plan) {
        if (plan.isEmpty()) {
            return "Aucun traitement nécessaire.";
        }

        StringBuilder builder = new StringBuilder();
        int stepNumber = 1;

        for (TreatmentStep step : plan.getSteps()) {
            builder.append(stepNumber++)
                    .append(". ")
                    .append(formatStep(step))
                    .append("\n");
        }

        return builder.toString();
    }


    private String formatStep(TreatmentStep step) {
        String action = step.adjustment() > 0 ? "Augmenter" : "Diminuer";
        String value = DECIMAL_FORMAT.format(Math.abs(step.adjustment()));
        String parameter = step.parameter().getLabel();
        String quantity = formatQuantity(step.quantityInGrams());
        String product = step.product().getLabel();

        return action + " le " + parameter + " de " + value + " avec " + quantity + " de " + product;
    }

    private String formatQuantity(double grams) {
        if (grams >= 1000) {
            return DECIMAL_FORMAT.format(grams / 1000) + "kg";
        }
        return DECIMAL_FORMAT.format(grams) + "g";
    }
}
