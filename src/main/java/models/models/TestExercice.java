package models.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TestExercice extends Test {
    private ArrayList<Exercice> exercices = new ArrayList<Exercice>();
    private HashMap<Integer, Integer> repetitions = new HashMap<>(); // <indexExercice, nbRepetition>

    public TestExercice(String nom, int capacite, ArrayList<Exercice> exercices, HashMap<Integer, Integer> repetitions) {
        super(nom, capacite);
        this.exercices = exercices;
        this.repetitions = repetitions;
    }


public void add_exo(Holder_Exo holder_Exo, int i) {
    Exercice exo = holder_Exo.QstAtindex(i);
    if (!exercices.contains(exo)) {
        exercices.add(exo);
        repetitions.put(exercices.size()-1, 1);
    } else {
        int k = repetitions.get(exercices.indexOf(exo));
        repetitions.put(exercices.indexOf(exo), k++);
    }
}
public void rem_exo(int i) {
    if (i >= 0 && i < exercices.size()) {
        if (repetitions.get(i) == 1) {
            exercices.remove(i);
            repetitions.remove(i);

        } else {
            int k = repetitions.get(i);
            repetitions.put(i, k--);

        }

    }

}
public TestExercice(Holder_Exo holderofexo){
    ArrayList<Integer> numbers = new ArrayList<>();

    System.out.println("here are the exos that you have create");

    holderofexo.afficher_exos();

    Scanner scanner = new Scanner(System.in);
    System.out.println("give the number of the exo you want to add in your Test");

    for (int i = 0; i < holderofexo.taille(); i++) {  //reads the index of the questions the doctor wants to add in the anamnése


        int number = scanner.nextInt();


        numbers.add(number);

        if(i+1 < holderofexo.taille()) {

            System.out.print("Do you want to add an other exo? (yes/no): ");
            String choice = scanner.next();

            // Check the user's choice
            if (choice.equalsIgnoreCase("no")) {
                break;
            }
        }
    }

    this.exercices=new ArrayList<Exercice>();

    for(int number : numbers) {


        try {               //adds the questions into the hashset


            this.exercices.add(holderofexo.QstAtindex(number));


        }
        catch(IndexOutOfBoundsException e){  //in case of an incorrect index value we do nothing (we ignore)


        }


    }

}
    public float calcScore(ArrayList<Integer> scores) {
        float score = 0;
        for (int i = 0; i <= scores.toArray().length; i++) {
            score += (float) scores.get(i) / repetitions.get(i);
        }
        return score;
    }

}




