package ca.qc.cegepst;
import java.util.Scanner;

public class PoolReader {

    private final Scanner scanner;

    public PoolReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Pool readPool() {
        int liters = lireCapacite();
        double ph = lirePh();
        double chlore = lireChlore();
        double alcalinite = lireAlcalinite();
        double durete = lireDurete();
        return new Pool(liters, ph, chlore, alcalinite, durete);
    }

    private int lireCapacite() {
        System.out.print("Capacité de la piscine (en litres) : ");
        while (!scanner.hasNextInt()) {
            System.out.println("Erreur : veuillez entrer un nombre entier valide.");
            scanner.next(); // <- on consomme l'entrée invalide
            System.out.print("Capacité de la piscine (en litres) : ");
        }
        return scanner.nextInt();
    }

    private double lirePh() {
        System.out.print("Niveau de pH [7.2 - 7.6] : ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Erreur : veuillez entrer une valeur numérique valide.");
            scanner.next();
            System.out.print("Niveau de pH [7.2 - 7.6] : ");
        }
        return scanner.nextDouble();
    }

    private double lireChlore() {
        System.out.print("Chlore libre [2 - 4 ppm] : ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Erreur : veuillez entrer une valeur numérique valide.");
            scanner.next();
            System.out.print("Chlore libre [2 - 4 ppm] : ");
        }
        return scanner.nextDouble();
    }

    private double lireAlcalinite() {
        System.out.print("Alcalinité totale [80 - 120 ppm] : ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Erreur : veuillez entrer une valeur numérique valide.");
            scanner.next();
            System.out.print("Alcalinité totale [80 - 120 ppm] : ");
        }
        return scanner.nextDouble();
    }

    private double lireDurete() {
        System.out.print("Dureté calcique [250 - 360 ppm] : ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Erreur : veuillez entrer une valeur numérique valide.");
            scanner.next();
            System.out.print("Dureté calcique [250 - 360 ppm] : ");
        }
        return scanner.nextDouble();
    }
}
