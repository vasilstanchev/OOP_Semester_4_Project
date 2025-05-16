package grammar;

import commands.CustomException;

import java.util.*;

public class ContextSensitiveGrammar extends Grammar {
    public ContextSensitiveGrammar(List<Character> terminals){
        super(terminals);
    }
    public ContextSensitiveGrammar(List<Character> terminals, List<Character> nonTerminals, List<Rules> rules, List<String> language){
        super(terminals,nonTerminals,rules,language);
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

    private static char findNextAvailableNonTerminal(List<Character> nonTerminals)throws CustomException {
        char nextNonTerminal = 'A';
        while (nonTerminals.contains(nextNonTerminal)) {
            nextNonTerminal++;
            if (nextNonTerminal > 'Z') {
                throw new CustomException("All non-terminals are already used");
            }
        }
        return nextNonTerminal;
    }

    public static ContextSensitiveGrammar convertToChomskyNormalForm(ContextSensitiveGrammar grammar)throws CustomException {
        if (Rules.isInChomskyNormalForm(grammar.getRules())) {
            throw new CustomException("Grammar is already in chomsky normal form.");
        }

        List<Rules> newRules = new ArrayList<>(grammar.getRules());
        List<Character> newNonTerminals = new ArrayList<>(grammar.getNonTerminals());
        char nextNonTerminal = findNextAvailableNonTerminal(newNonTerminals);
        Map<Character, Character> terminalToNonTerminal = new HashMap<>();

        boolean changed = true;
        while (changed) {
            changed = false;
            List<Rules> toAdd = new ArrayList<>();
            List<Rules> toRemove = new ArrayList<>();
            for (Rules rule : newRules) {
                if (rule.getDescribingPart().length() == 1 && Character.isUpperCase(rule.getDescribingPart().charAt(0))) {
                    char nonTerminalToReplace = rule.getDescribingPart().charAt(0);
                    char currentNonTerminal = rule.getNonTerminal();
                    toRemove.add(rule);
                    changed = true;
                    for (Rules r : newRules) {
                        if (r.getNonTerminal() == nonTerminalToReplace) {
                            Rules newRule = new Rules(newRules.size() + toAdd.size() + 1, currentNonTerminal, r.getDescribingPart());
                            toAdd.add(newRule);
                        }
                    }
                }
            }
            newRules.removeAll(toRemove);
            newRules.addAll(toAdd);
        }

        List<Rules> finalRules = new ArrayList<>();
        for (Rules rule : newRules) {
            String describingPart = rule.getDescribingPart();
            char nonTerminal = rule.getNonTerminal();

            if (describingPart.length() == 1 && Character.isLowerCase(describingPart.charAt(0))) {
                finalRules.add(rule);
            } else if (describingPart.length() >= 2) {
                StringBuilder newDescribingPart = new StringBuilder();
                for (char symbol : describingPart.toCharArray()) {
                    if (Character.isLowerCase(symbol)) {
                        if (!terminalToNonTerminal.containsKey(symbol)) {
                            terminalToNonTerminal.put(symbol, nextNonTerminal++);
                            newNonTerminals.add(terminalToNonTerminal.get(symbol));
                            Rules newRule = new Rules(finalRules.size() + 1, terminalToNonTerminal.get(symbol), String.valueOf(symbol));
                            finalRules.add(newRule);
                        }
                        newDescribingPart.append(terminalToNonTerminal.get(symbol));
                    } else {
                        newDescribingPart.append(symbol);
                    }
                }

                String finalDescribingPart = newDescribingPart.toString();
                if (finalDescribingPart.length() <= 2) {
                    Rules newRule = new Rules(finalRules.size() + 1, nonTerminal, finalDescribingPart);
                    finalRules.add(newRule);
                } else {
                    char lastNonTerminal = nonTerminal;
                    for (int i = 0; i < finalDescribingPart.length() - 2; i++) {
                        char newNt = nextNonTerminal++;
                        newNonTerminals.add(newNt);
                        Rules newRule = new Rules(finalRules.size() + 1, lastNonTerminal, String.valueOf(finalDescribingPart.charAt(i)) + newNt);
                        finalRules.add(newRule);
                        lastNonTerminal = newNt;
                    }
                    Rules newRule = new Rules(finalRules.size() + 1, lastNonTerminal, finalDescribingPart.substring(finalDescribingPart.length() - 2));
                    finalRules.add(newRule);
                }
            } else if (describingPart.equals("final")) {
                finalRules.add(rule);
            }
        }

        ContextSensitiveGrammar newGrammar = new ContextSensitiveGrammar(grammar.getTerminals(), newNonTerminals, finalRules, grammar.getLanguage());
        return newGrammar;
    }
}
