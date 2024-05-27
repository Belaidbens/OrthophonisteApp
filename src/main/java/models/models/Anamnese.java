package models.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Anamnese {

    private HashSet<Qst_anam> qsts;
    private Type type;

    Anamnese(ArrayList<Qst_anam> qsts, Type type) {
        ArrayList<Integer> numbers = new ArrayList<>();

       /* holderofanams.afficher_qsts(type);
        Scanner scanner = new Scanner(System.in);
        System.out.println("give the number of the question you want to add in your anamnese");
        for (int i = 0; i < holderofanams.taille(type); i++) {  //reads the index of the questions the doctor wants to add in the anamnése
            int number = scanner.nextInt();
            numbers.add(number);
            if (i + 1 < holderofanams.taille(type)) {

                System.out.print("Do you want to add an other question? (yes/no): ");
                String choice = scanner.next();

                // Check the user's choice
                if (choice.equalsIgnoreCase("no")) {
                    break;
                }
            }
        }
        this.qsts = new HashSet<Qst_anam>();
        for (int number : numbers) {
            try {               //adds the questions into the hashset
                qsts.add(holderofanams.QstAtindex(number, type));
            } catch (IndexOutOfBoundsException e) {  //in case of an incorrect index value we do nothing (we ignore)
            }
        }*/
        this.qsts=new HashSet<>(qsts);
        this.type=type;
    }
}
