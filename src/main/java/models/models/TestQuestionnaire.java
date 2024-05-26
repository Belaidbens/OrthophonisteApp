package models.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class TestQuestionnaire extends Test {
    private HashSet<Question> questions = new HashSet<>();

    public TestQuestionnaire(String nom, int capacite, HashSet<Question> questions) {
        super(nom, capacite);
        this.questions = questions;
    }
    public TestQuestionnaire(Holder_Qst holderofqst){
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("here are the questions that you have create");

        holderofqst.afficher_qsts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("give the number of the question you want to add in your Test");

        for (int i = 0; i < holderofqst.taille(); i++) {  //reads the index of the questions the doctor wants to add in the anamnése


            int number = scanner.nextInt();


            numbers.add(number);

            if(i+1 < holderofqst.taille()) {

                System.out.print("Do you want to add an other question? (yes/no): ");
                String choice = scanner.next();

                // Check the user's choice
                if (choice.equalsIgnoreCase("no")) {
                    break;
                }
            }
        }
        this.questions=new HashSet<Question>();
        for(int number : numbers) {
            try {               //adds the questions into the hashset
                this.questions.add(holderofqst.QstAtindex(number));
            }
            catch(IndexOutOfBoundsException e){  //in case of an incorrect index value we do nothing (we ignore)

            }
        }
    }


    public float calcScore(ArrayList<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum;
    }
    }



