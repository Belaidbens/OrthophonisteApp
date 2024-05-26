package models.models;

import java.util.*;
import java.time.*;
import java.io.Serializable;
public class Orthophoniste implements Serializable {
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String telephone;
    private String motdepasse;
    List<Patient> patiens;
    List<Patient> patiens2;
    List<Anamnese> anamneses;
    List<Test> tests;
    public Holder_anam holderofanams;
    private Holder_Qst holderofQst;
    private Holder_Exo holderofexo;

    private Rendezvous rdv;
    private Jour jr;
    private Agenda agenda;

    public Orthophoniste(String nom, String prenom, String adresse, String mail, String telephone, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.telephone = telephone;
        this.motdepasse = motdepasse;
        this.agenda = new Agenda();
        this.patiens =new ArrayList<>() ;
        this.tests= new ArrayList<>();
        this.anamneses= new ArrayList<>();
        this.holderofanams=new Holder_anam();
        this.holderofQst=new Holder_Qst();
        this.holderofexo = new Holder_Exo();
    }

    public Orthophoniste creercompte(String nom, String prenom, String adresse, String mail, String telephone, String motdepasse) {
        Orthophoniste ortho;
        return ortho = new Orthophoniste(nom, prenom, adresse, mail, telephone, motdepasse);
    }

    public List<Patient> getPatiens() {
        return patiens;
    }

    public List<Patient> getPatiens2() {
        return patiens2;
    }
    public String toString(){
        return nom +" "+ prenom;
    }
    public boolean seConnecter(String email, String motDePasse) {
        return this.mail.equals(email) && this.motdepasse.equals(motDePasse);
    }

    public void afficherorthophoniste() {
        System.out.println("nom : " + nom);

    }
    public String getMail(){
        return mail;
    }
    public String getMotdepasse(){
        return motdepasse;
    }


    public Anamnese create_Amn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give the type of the anamnese you want to create : ");
        System.out.println("Type1 :anamnese of Adulte");
        System.out.println("Type2 :anamnese of Child");
        int num = scanner.nextInt();

        Type type = Type.values()[num - 1];
        ;

        if (holderofanams.taille(type) != 0) {
            Anamnese amn = new Anamnese(holderofanams, type);
            anamneses.add(amn);
            return amn;
        } else {
            System.out.println("there is no question created , you should creat questions before creating an anamnése");

            return null;

        }
    }
    public void prendre_charge(int i) {
        patiens.add(patiens2.get(i));
        patiens2.get(i).getDoc().setNumero();
        patiens2.remove(i);
    }
    public void ne_prendre_charge(int i) {
        patiens2.remove(i);
    }
    public Rendezvous creer_RDV(Patient pat){
        Rendezvous ren;
        LocalDate date=null;
        LocalTime heuredebut= null;// a changer
        LocalTime heurefin=null;
        if(pat == null){
            Type type = null;
            String nom = null;
            String prenom = null;
            String adr = null;
            LocalDate Date = null;
            String placeN =  null;
            String mail =null;
            switch (type) {
                case ADULTE:
                    String tel = null;
                    String pro = null;
                    String Dip = null;
                    pat = new Adulte(nom, prenom, Date, adr,tel,mail,placeN , Dip,pro);
                    break;
                    default:
                    String Telm = null;
                    String Telp = null;
                    String Clasd = null;
                    pat = new Enfant(nom,prenom, Date, adr,Telp,mail,placeN, Clasd, Telm );
                    break;
            }
            ren = new Consultation(heuredebut,heurefin,pat.getNom(),pat.getPrenom(),pat.getAge(),create_Amn()); //a changer
            Dossier doc = new Dossier(ren );

            //ajout ll agenda (   )
           if(programmerRendezvous(date, ren)){
               patiens2.add(pat);
           }else{
               ren=null;
           }


            pat.SetDoc(doc);
            patiens2.add(pat);
        }else{
            try {
                TYPERDV derou = null;
                int i = pat.getDoc().getNumero();
                ren = new Suivi(heuredebut,heurefin,derou);//a changer
                if(programmerRendezvous(date,ren)) {
                    patiens.get(i).getDoc().addRendezVous(ren);
                    BilanOrthophonique bo = this.createBo();
                    pat.getDoc().addBilan(bo);
                }else {
                    ren=null;;
                }

            }
            catch(ZeroTests e) {
                //changed here
                System.out.println("the RDV creation failed, there is no test to create a new BO ,try to create tests before organasing a new RDV");
                ren = null;
            }
        }
        return ren;
    }


    private BilanOrthophonique createBo() throws ZeroTests {
        if (tests.size() == 0) {
            throw new ZeroTests();
        }
        int choix = 1;
        ArrayList<Epreuve> erps = new ArrayList<>();
        while (choix != 0) {
            HashSet<Integer> inputSet = new HashSet<>();
            Scanner scanner = new Scanner(System.in);
            // Read inputs from the user
            int input;
            System.out.println("here are the tests :");
            for (Test test : tests) {
                System.out.println(test);
            }
            System.out.println("choose the tests you want to put into the Bo, to finish enter -1");
            while (true) {
                System.out.print("Enter an integer: ");
                input = scanner.nextInt();
                // Check for the termination condition
                if (input == -1) {
                    break;
                }
                // Add the input to the HashSet
                inputSet.add(input);
            }
            // Close the scanner
            ArrayList<Test> tab = new ArrayList<Test>();
            for (int i : inputSet) {
                if (i >= 0 && i < tests.size()) {
                    tab.add(tests.get(i));
                }
            }

            erps.add(new Epreuve(tab,null));

            choix = scanner.nextInt();
        }
        return new BilanOrthophonique(erps,new Diagnostic(null),null);
    }

    public Rendezvous creer_ATl(ArrayList<Patient> pas) throws PatientNotFoundExcetption{
        LocalDate date = null;// change thaaaat
        LocalTime heuredebut = null;// change thaaaat
        LocalTime heurefin = null;
        for (Patient patient : pas) {
            if(patient.getDoc() ==  null){
                throw new PatientNotFoundExcetption();
            }
        }
        String theme = null;// change thaaaat
        Atelier atl = new Atelier(heuredebut,heurefin,pas,theme);
        if(programmerRendezvous(date,atl)){
            return atl;
        }else{
            return null;
        }
    }
    private Qst_anam Create_Qst_Anam(String enoncé, Type type) {
        Scanner scanner = new Scanner(System.in);

        switch (type) {
            case ADULTE:
                System.out.println("what is the category of the question ?");

                for (CategAdulte cat : CategAdulte.values()) { //printing the values of the enum
                    System.out.println(cat);
                }

                int i = scanner.nextInt();

                Qst_adt Qst1 = new Qst_adt(enoncé, CategAdulte.values()[i]);
                return Qst1;
            case ENFANT:

                System.out.println("what is the category of the question ?");


                for (CategEnfant cat : CategEnfant.values()) { //printing the values of the enum
                    System.out.println(cat);
                }


                int j = scanner.nextInt();

                Qst_adt Qst2 = new Qst_adt(enoncé, CategAdulte.values()[j]);


                return Qst2;

            default:

                return null;
        }

    }


    public void AddQstToHoldAnam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give the type of the question you want to add : ");
        System.out.println("Type1 : Adulte");
        System.out.println("Type2 : Enfant");
        Type type;
        int num = scanner.nextInt();
        type = Type.values()[num-1]; ;
        System.out.println("give the question you want to add : ");
        String input = scanner.nextLine();
        Qst_anam qst= Create_Qst_Anam(input,type);
        this.holderofanams.add_qst(qst, type);
    }



    public Test  create_test() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give the type of the test you want to create : ");
        System.out.println("Type1 :test of exos");
        System.out.println("Type2 :test of qsts");


        int num = scanner.nextInt();

        TypeTest type = TypeTest.values()[num-1];
        switch ( type) {
            case EXOS:
                if (holderofexo.taille()!=0) {
                    TestExercice se = new TestExercice(holderofexo);
                    tests.add(se);
                    return se;
                }
                else {
                    System.out.println("there is no exos created , you should creat exos before creating an test");

                    return null;
                }
                case QSTS:
                if (holderofQst.taille()!=0) {
                    TestQuestionnaire qstionnaire = new TestQuestionnaire(holderofQst);
                    tests.add(qstionnaire);
                    return qstionnaire;}
                else {
                    System.out.println("there is no questions created , you should creat questions before creating an test");
                    return null;
                }
        }
        return null;
    }

    public void AddQstToHoldQstNorm() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("give the type of the  question you want to add : ");

        for (Type_Qst cat : Type_Qst.values()) { //printing the values of the enum
            System.out.println(cat);
        }
        int i=scanner.nextInt();
        Type_Qst type= Type_Qst.values()[i-1];
        System.out.println("give the  question you want to add : ");
        String input = scanner.nextLine();
        //QstNrml qst = new QstNrml(input);
        this.holderofQst.add_qst(Create_Qst_Norml(input, type));
    }

    private Question Create_Qst_Norml(String enoncé, Type_Qst type) {

        switch (type) {
            case QCU:
                return new QCU(enoncé);
            case QCM:
                // Code for Qcm case
                return new QCM(enoncé);
            case QL:
                // Code for Ql case
                return new Question(enoncé);
            default:
                // Default case (optional)
                // Code to handle other cases if necessary
                break;
        }
        return null;

    }
    public void AddQstToHoldExo() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("give the consigne of the  question you want to add : ");

        String cons =scanner.nextLine();

        System.out.println("give the materiel name : ");

        String nommat =scanner.nextLine();

        //QstNrml qst = new QstNrml(input);
        this.holderofexo.add_exo(Create_exo(cons, nommat));

    }

    private Exercice Create_exo(String cons, String nomMat) {
        return new Exercice(cons,nomMat);

    }
    public FicheSuivi creerFichierSuivis(Patient Pas) {

        Scanner scanner =new Scanner(System.in);
        int userInput=-1;
        FicheSuivi fichier = null;
        TreeSet<Objective> objs=new TreeSet<Objective>();
        while (userInput != 0) {
            System.out.println("Enter the name of the objective : ");
            String name = scanner.nextLine();
            System.out.println("Enter the terme of the objective : ");
            for(EnumTerm term : EnumTerm.values()) {
                System.out.println(term);
            }
            int i=scanner.nextInt();
            EnumTerm term = EnumTerm.values()[i-1];
            objs.add(new Objective(name,term));
            System.out.print("Enter a number (0 to quit): ");
            userInput = scanner.nextInt();
        }
        fichier.setObjs(objs);
        Pas.getDoc().addFicheSuivi(fichier);
        return fichier;

    }

    public boolean programmerRendezvous(LocalDate date, Rendezvous nouveauRdv) {
        if (agenda.estDisponible(date, nouveauRdv)) {
            agenda.ajouterRendezvous(date, nouveauRdv);
            return true;
        } else {
            System.out.println("Rendez-vous non disponible pour cette date et heure.");
            return false;
        }
    }
    public void afficherAgenda() {
        agenda.afficherAgenda();
    }
}
