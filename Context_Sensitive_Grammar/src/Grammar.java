import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Grammar {
    private int id;
    private List<Character> terminals;
    private List<Character> nonTerminals;
    private List<Rules> rules;
    private List<String> language;
    Grammar(List<Character> terminals){
        //id = generateId();
        setTerminals(terminals);
        nonTerminals = new ArrayList<>();
        rules = new ArrayList<>();
        language = new ArrayList<>();
    }
    /*public int generateId(){
        ??
    }*/
    public int getId() {
        return id;
    }
    public List<Character> getTerminals() {
        return Collections.unmodifiableList(terminals);
    }
    public List<Character> getNonTerminals() {
        return Collections.unmodifiableList(nonTerminals);
    }
    public List<Rules> getRules() {
        return Collections.unmodifiableList(rules);
    }
    public List<String> getLanguage() {
        return Collections.unmodifiableList(language);
    }
    public void setTerminals(List<Character> terminals) {
        this.terminals = terminals;
    }
    public void setNonTerminals(List<Character> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }
    public void setRules(List<Rules> rules) {
        this.rules = rules;
    }
    public void setLanguage(ArrayList<String> language) {
        this.language = language;
    }

    public static void addGrammar(List<Grammar> grammars){
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
        List<Character> terminals = this.getTerminals();
        List<Character> nonTerminals = this.getNonTerminals();

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
        Scanner scanner = new Scanner(System.in);
        if (this.getRules().isEmpty()){
            Rules rule = new Rules(1,'S', describingPart);
            this.getRules().add(rule);
        }
        else {
            int number = getRules().size() + 1;
            System.out.println("Choose a non-terminals to begin the rule:");
            System.out.printf("{ ");
            for (int i = 0; i < getNonTerminals().size(); i++) {
                System.out.printf("%c ",getNonTerminals().get(i));
            }
            System.out.printf("}");
            System.out.println();

            char nonTerminal = scanner.next().charAt(0);
            Rules rule = new Rules(number, nonTerminal, describingPart);
            this.rules.add(rule);
        }
    }
    public void listId(){
        System.out.printf("Grammar id: %d\n", this.id);
    }
    public static void listIds(List<Grammar> grammars){
        for (int i = 0; i < grammars.size(); i++) {
            grammars.get(i).listId();
        }
    }
    //removeGrammar()
    //editGrammar() - without terminals
    //modifyRule()
}
