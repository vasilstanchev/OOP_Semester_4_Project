import java.util.*;

public class Grammar {
    private int id;
    private List<Character> terminals;
    private List<Character> nonTerminals;
    private List<Rules> rules;
    private List<String> language;
    Grammar(List<Character> terminals){
        id = generateId();
        setTerminals(terminals);
        nonTerminals = new ArrayList<>();
        rules = new ArrayList<>();
        language = new ArrayList<>();
    }
    public int generateId(){
        Random random = new Random();
        int minNum = 1;
        int maxNum = 100;
        return random.nextInt(maxNum - minNum + 1) + minNum;
    }
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
        System.out.println("Enter the number of terminals you want to add:");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++)
        {
            System.out.println("Enter a terminal to add:");
            char terminal = scanner.next().charAt(0);
            terminals.add(terminal);
        }
        Grammar grammar = new Grammar(terminals);
        grammars.add(grammar);
    }
    private static Boolean[] validateGrammar(List<Character> terminals, List<Character> nonTerminals, List<Rules> rules, List<String> language){
        Boolean[] nullValidator = new Boolean[4];
        nullValidator[0] = false;
        nullValidator[1] = false;
        nullValidator[2] = false;
        nullValidator[3] = false;
        if (terminals.isEmpty()){
            System.out.println("There aren't any terminals added");
            nullValidator[0] = true;
            //tba
        }
        if (nonTerminals.isEmpty()){
            System.out.println("There aren't any non terminals added");
            nullValidator[1] = true;
            //tba
        }
        if (rules.isEmpty()){
            System.out.println("There aren't any rules added");
            nullValidator[2] = true;
            //tba
        }
        if (language.isEmpty()){
            System.out.println("There aren't any words added");
            nullValidator[3] = true;
            //tba
        }
        return nullValidator;
    }
    public void printGrammar(){
        System.out.printf("Id: %d\n", id);
        Boolean[] nullValidator = validateGrammar(terminals, nonTerminals, rules, language);

        if (nullValidator[0] == false){
            System.out.println("Terminals:");
            System.out.printf("{ ");
            for (int i = 0; i < terminals.size(); i++) {
                System.out.printf("%c ",terminals.get(i));
            }
            System.out.printf("}");
            System.out.println();
        }
        if (nullValidator[1] == false) {
            System.out.println("Non-Terminals:");
            System.out.printf("{ ");
            for (int i = 0; i < nonTerminals.size(); i++) {
                System.out.printf("%c ", nonTerminals.get(i));
            }
            System.out.printf("}");
            System.out.println();
        }
        if (nullValidator[2] == false) {
            System.out.println("Rules:");
            for (int i = 0; i < rules.size(); i++) {
                rules.get(i).printRuleInfo();
            }
        }
        if (nullValidator[3] == false) {
            System.out.println("Words inside the language:");
            System.out.printf("{ ");
            for (int i = 0; i < language.size(); i++) {
                System.out.printf("%s ", language.get(i));
            }
            System.out.printf("}");
            System.out.println();
        }
    }
    public void addRule(String describingPart){
        Scanner scanner = new Scanner(System.in);
        if (this.getRules().isEmpty()){
            Rules rule = new Rules(1,'S', describingPart);
            rules.add(rule);
            nonTerminals.add('S');
        }
        else {
            int number = rules.size() + 1;
            System.out.println("Choose a non-terminals to begin the rule:");
            System.out.printf("{ ");
            for (int i = 0; i < nonTerminals.size(); i++) {
                System.out.printf("%c ",nonTerminals.get(i));
            }
            System.out.printf("}");
            System.out.println();

            char nonTerminal = scanner.next().charAt(0);
            if (nonTerminals.equals(nonTerminal)){
                Rules rule = new Rules(number, nonTerminal, describingPart);
                this.rules.add(rule);
            }
            else {
                System.out.println("The non terminal is not present. Rule not added.");
            }
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
