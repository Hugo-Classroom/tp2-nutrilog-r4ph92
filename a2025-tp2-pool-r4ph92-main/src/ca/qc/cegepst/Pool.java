package ca.qc.cegepst;

public record Pool(
        int liters,
        double ph,
        double freeChlorine,
        double alkalinity,
        double hardness
) {
    public Pool {
        if (liters <= 0) {
            throw new IllegalArgumentException("La capacité de la piscine doit être positive.");
        }
    }
}


