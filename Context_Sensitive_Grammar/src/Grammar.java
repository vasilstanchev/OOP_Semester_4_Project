import java.util.ArrayList;
import java.util.Scanner;

public class Grammar {
    private int id;
    private ArrayList<Character> terminals;
    private ArrayList<Character> nonTerminals;
    private ArrayList<Rules> rules;
    private ArrayList<String> language;
    Grammar(ArrayList<Character> terminals){
        //id = generateId();
        setTerminals(terminals);
        nonTerminals = new ArrayList<>();
        rules = new ArrayList();
        language = new ArrayList<>();
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

        for (int i = 0; i < count1; i++)
        {
            System.out.println("Enter a terminal to add:");
            char terminal = scanner.next().charAt(0);
            terminals.add(terminal);
        }
        Grammar grammar = new Grammar(terminals);
        grammars.add(grammar);
    }
    public void printGrammar(){
        //int id = this.getId();
        ArrayList<Character> terminals = this.getTerminals();
        ArrayList<Character> nonTerminals = this.getNonTerminals();

        // System.out.println("Id: %d", id);

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

        System.out.println("Rules:");

        System.out.println("Words inside the language:");
        System.out.printf("{ ");
        for (int i = 0; i < language.size(); i++) {
            System.out.printf("%s ",language.get(i));
        }
        System.out.printf("}");
        System.out.println();
    }
    public void addRule(String describingPart){
        if (rules.isEmpty()){
            Rules rule = new Rules(1,'S', describingPart);
            rules.add(rule);
        }
        else {
            int number = rules.size() + 1;
            //tba
        }
    }
    //listId() - show all the ids to the corresponding grammars
    //removeGrammar()
    //editGrammar() - without terminals
    //modifyRule()
}
