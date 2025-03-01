import java.util.ArrayList;
import java.util.Scanner;

public class Grammar {
    private int id;
    private ArrayList<Character> literals;
    private ArrayList<Character> nonLiterals;
    //private ArrayList<Rule> rules;
    Grammar(ArrayList<Character> literals, ArrayList<Character> nonLiterals){
        //id = generateId();
        this.literals = literals;
        this.nonLiterals = nonLiterals;
    }
    /*public int generateId(){
        ??
    }*/
    public ArrayList<Character> getLiterals() {
        return literals;
    }
    public ArrayList<Character> getNonLiterals() {
        return nonLiterals;
    }
    public int getId() {
        return id;
    }
    public void setLiterals(ArrayList<Character> literals) {
        this.literals = literals;
    }
    public void setNonLiterals(ArrayList<Character> nonLiterals) {
        this.nonLiterals = nonLiterals;
    }

    public static void addGrammar(ArrayList<Grammar> grammars){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> literals = new ArrayList<>();
        ArrayList<Character> nonLiterals = new ArrayList<>();
        System.out.println("Enter the number of literals you want to add:");
        int count1 = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of non literals you want to add:");
        int count2 = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count1; i++)
        {
            System.out.println("Enter a literal to add:");
            char literal = scanner.next().charAt(0);
            literals.add(literal);
        }
        for (int j = 0; j < count2; j++)
        {
            System.out.println("Enter a non literal to add:");
            char nonLiteral = scanner.next().charAt(0);
            nonLiterals.add(nonLiteral);
        }
        Grammar grammar = new Grammar(literals,nonLiterals);
        grammars.add(grammar);
    }
    //printGrammar
    //removeGrammar()
    //editGrammar()??
    //addRule()
    //modifyRule()
}
