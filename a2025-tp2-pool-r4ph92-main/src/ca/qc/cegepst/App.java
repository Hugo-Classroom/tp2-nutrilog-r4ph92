package ca.qc.cegepst;

import java.util.Scanner;

public class App {
    //TODO: cond to min max ph entry

    public static void main(String[] args) {
        /*
          Il doit y avoir UNE seul instance de Scanner dans le projet pour
          s'assurer que le buffer reste bon avec Classroom.
         */
        Scanner scanner = new Scanner(System.in);
        PoolReader reader = new PoolReader(scanner);
        Tester tester = new Tester(reader.readPool());
        tester.test();
    }
}