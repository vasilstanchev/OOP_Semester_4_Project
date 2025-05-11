package grammar;

import commands.CustomException;

import java.util.*;

public class ContextSensitiveGrammar extends Grammar {
    ContextSensitiveGrammar(List<Character> terminals){
        super(terminals);
    }

    public static ContextSensitiveGrammar returnGrammarById(int id, List<ContextSensitiveGrammar> grammars) throws CustomException{
        for (int i = 0; i < grammars.size(); i++) {
            if (grammars.get(i).getId()==id){
                return grammars.get(i);
            }
        }
        throw new CustomException("The id doesn't exist");
    }
    public static void addGrammar(List<ContextSensitiveGrammar> grammars, List<String> args)throws CustomException {
        if (args.isEmpty()) {
            throw new CustomException("There's no id entered");
        }
        List<Character> terminals = new ArrayList<>();
        int count = args.size();
        for (int i = 0; i < count; i++)
        {
            char terminal = args.get(i).charAt(0);
            terminals.add(terminal);
        }
        ContextSensitiveGrammar grammar = new ContextSensitiveGrammar(terminals);
        grammars.add(grammar);
    }

    public static void printRules(List<Rules> rules){
        for (int i = 0; i < rules.size(); i++) {
            rules.get(i).printRuleInfo();
        }
    }
    private void extractNonTerminal(String describingPart){
        for (char letter : describingPart.toCharArray()) {
            if (Character.isUpperCase(letter)){
                if (!nonTerminals.contains(letter)) {
                    nonTerminals.add(letter);
                }
            }
        }
    }
    public void addRule(String describingPart) throws CustomException{
        Scanner scanner = new Scanner(System.in);
        if (rules.isEmpty()){
            Rules rule = new Rules(1,'S', describingPart);
            rules.add(rule);
            nonTerminals.add('S');
            this.extractNonTerminal(describingPart);
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
            boolean isTrue = false;
            for (int i = 0; i < nonTerminals.size(); i++){
                if (nonTerminals.get(i).equals(nonTerminal)){
                    Rules rule = new Rules(number, nonTerminal, describingPart);
                    rules.add(rule);
                    this.extractNonTerminal(describingPart);
                    isTrue = true;
                }
            }
            if (!isTrue) {
                throw new CustomException("The non-terminal is not inside the list of non-terminals, the rule was not added");
            }
        }
    }

    public void removeRule(int ruleNumber) throws CustomException {
        Rules ruleToRemove = Rules.getRuleByNumber(this.rules, ruleNumber);
        if (ruleToRemove == null) {
            throw new CustomException("No rule found with number: " + ruleNumber);
        }

        this.rules.remove(ruleToRemove);
        System.out.println("Rule №" + ruleNumber + " was removed");
    }

    public void listId(){
        System.out.printf("Grammar id: %d\n", this.id);
    }
    public static void isLanguageEmpty(List<ContextSensitiveGrammar> grammars, int id){
        for (int i = 0; i < grammars.size(); i++) {
            if (grammars.get(i).getId() == id){
                if (grammars.get(i).getLanguage().isEmpty()){
                    System.out.println("The language in this grammar is empty");
                }
            }
        }
    }
    private int showRulesWithS(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        System.out.println("Choose between these rules to begin the word:");
        for (int i = 0; i < rules.size(); i++) {
            if(rules.get(i).getNonTerminal() == 'S'){
                rules.get(i).printRuleInfo();
            }
        }

        System.out.println("Number: ");
        number = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < rules.size(); i++) {
            if(rules.get(i).getNonTerminal() == 'S' && rules.get(i).getNumber() == number){
                return rules.get(i).getNumber();
            }
        }
        return 0;
    }

    public void addWord() throws CustomException{
        Scanner scanner = new Scanner(System.in);
        boolean isPossible = Rules.checkForFinal(rules);
        if (isPossible) {
            int number = showRulesWithS();
            Rules rule = Rules.getRuleByNumber(rules, number);
            String word = rule.getDescribingPart();
            do {
                System.out.printf("Current word: %s\n", word);
                ContextSensitiveGrammar.printRules(rules);
                System.out.println("Enter the number of the rule you want to use:");
                number = Integer.parseInt(scanner.nextLine());
                rule = Rules.getRuleByNumber(rules, number);
                if (rule == null){
                    throw new CustomException("Rule doesn't exist");
                }
                else{
                    word = Rules.connectTerminals(rule,word);
                }
                if (rule.getDescribingPart().equals("final")){
                    word=word.replace("final","");
                }
            } while (!rule.getDescribingPart().equals("final"));
            language.add(word);
        }
        else{
            throw new CustomException("There isn't any end rule");
        }
    }
}
