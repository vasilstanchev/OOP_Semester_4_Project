import java.util.ArrayList;
import java.util.Scanner;

public class Grammar {
    private int id;
    private ArrayList<Character> terminals;
    private ArrayList<Character> nonTerminals;
    //private ArrayList<Rule> rules;
    Grammar(ArrayList<Character> terminals, ArrayList<Character> nonLiterals){
        //id = generateId();
        setTerminals(terminals);
        setNonTerminals(nonTerminals);
        //rules = new ArrayList();
    }
    /*public int generateId(){
        ??
    }*/
    public ArrayList<Character> getTerminals() {
        return terminals;
    }
    public ArrayList<Character> getNonTerminals() {
        return nonTerminals;
    }
    public int getId() {
        return id;
    }
    public void setTerminals(ArrayList<Character> terminals) {
        this.terminals = terminals;
    }
    public void setNonTerminals(ArrayList<Character> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public static void addGrammar(ArrayList<Grammar> grammars){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> terminals = new ArrayList<>();
        ArrayList<Character> nonTerminals = new ArrayList<>();
        System.out.println("Enter the number of terminals you want to add:");
        int count1 = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of non terminals you want to add:");
        int count2 = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count1; i++)
        {
            System.out.println("Enter a terminal to add:");
            char terminal = scanner.next().charAt(0);
            terminals.add(terminal);
        }
        for (int j = 0; j < count2; j++)
        {
            System.out.println("Enter a non literal to add:");
            char nonTerminal = scanner.next().charAt(0);
            nonTerminals.add(nonTerminal);
        }
        Grammar grammar = new Grammar(terminals, nonTerminals);
        grammars.add(grammar);
    }
    public void printGrammar(){
        //int id = this.getId();
        ArrayList<Character> terminals = this.getTerminals();
        ArrayList<Character> nonTerminals = this.getNonTerminals();

        //unique id show

        System.out.println("Terminals:");
        System.out.printf("{ ");
        for (int i = 0; i < terminals.size(); i++) {
            System.out.printf("%c ",terminals.get(i));
        }
        System.out.printf("}");
        System.out.println();

        System.out.println("Non-Terminals:");
        System.out.printf("{ ");
        for (int i = 0; i < nonTerminals.size(); i++) {
            System.out.printf("%c ",nonTerminals.get(i));
        }
        System.out.printf("}");
        System.out.println();

        //rules show
    }
    //listId() - show all the ids to the corresponding grammars
    //removeGrammar()
    //editGrammar() - without terminals
    //addRule()
    //modifyRule()
}
