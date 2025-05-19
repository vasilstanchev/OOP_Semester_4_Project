package grammar;

import commands.CustomException;

import java.io.Serializable;
import java.util.*;

public class ContextSensitiveGrammar extends Grammar implements Serializable {
    /**
     * Конструктор, който създава нова КС граматика с даден списък с терминали
     * @param terminals
     */
    public ContextSensitiveGrammar(List<Character> terminals){
        super(terminals);
    }
    /**
     * Конструктор, който създава нова КС граматика с всичките атрибути
     * @param terminals
     */
    public ContextSensitiveGrammar(List<Character> terminals, List<Character> nonTerminals, List<Rules> rules, List<String> language){
        super(terminals,nonTerminals,rules,language);
    }

    /**
     * Метод, който връща дадена граматика по даден уникален идентификатор
     * @param id
     * @param grammars
     * @return
     * @throws CustomException
     */
    public static ContextSensitiveGrammar returnGrammarById(int id, List<ContextSensitiveGrammar> grammars) throws CustomException{
        for (int i = 0; i < grammars.size(); i++) {
            if (grammars.get(i).getId()==id){
                return grammars.get(i);
            }
        }
        throw new CustomException("The id doesn't exist");
    }

    /**
     * Метод, който добавя нова граматика в списъка с граматики
     * @param grammars
     * @param args
     * @throws CustomException
     */
    public static void addGrammar(List<ContextSensitiveGrammar> grammars, List<String> args)throws CustomException {
        if (args.isEmpty()) {
            throw new CustomException("There weren't any terminals provided");
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

    /**
     * Метод, който отпечатва всичките правила за дадена граматика
     * @param rules
     */
    public static void printRules(List<Rules> rules){
        for (int i = 0; i < rules.size(); i++) {
            rules.get(i).printRuleInfo();
        }
    }

    /**
     * Метод, който взима нетерминалната част от дадена описателна част на правило
     * @param describingPart
     */
    private void extractNonTerminal(String describingPart){
        for (char letter : describingPart.toCharArray()) {
            if (Character.isUpperCase(letter)){
                if (!nonTerminals.contains(letter)) {
                    nonTerminals.add(letter);
                }
            }
        }
    }

    /**
     * Метод, който проверява дали терминалите в дадената описателна част са част от азбуката на граматиката
     * @param describingPart
     * @return
     * @throws CustomException
     */
    private boolean checkDescribingPart(String describingPart)throws CustomException{
        if(describingPart.equals("final")){
            return true;
        }
        for (char character: describingPart.toCharArray()) {
            if (Character.isLowerCase(character)) {
                if (!this.terminals.contains(character)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод, който добавя ново правило
     * @param describingPart
     * @throws CustomException
     */
    public void addRule(String describingPart) throws CustomException{
        if (!this.checkDescribingPart(describingPart)){
            throw new CustomException("The terminals inside the describing part of the rule don't match with the entered alphabet!");
        }
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

    /**
     * Метод, който премахва дадено правило по дадено поредно число
     * @param ruleNumber
     * @throws CustomException
     */
    public void removeRule(int ruleNumber) throws CustomException {
        Rules ruleToRemove = Rules.getRuleByNumber(this.rules, ruleNumber);
        if (ruleToRemove == null) {
            throw new CustomException("No rule found with number: " + ruleNumber);
        }

        this.rules.remove(ruleToRemove);
        System.out.println("Rule №" + ruleNumber + " was removed");
    }

    /**
     * Метод, който отпечатва уникалния идентификатор на граматиката
     */
    public void listId(){
        System.out.printf("Grammar id: %d\n", this.id);
    }

    /**
     * Метод, който проверява дали езикът в дадена граматика е празен
     * @param grammars
     * @param id
     */
    public static void isLanguageEmpty(List<ContextSensitiveGrammar> grammars, int id){
        for (int i = 0; i < grammars.size(); i++) {
            if (grammars.get(i).getId() == id){
                if (grammars.get(i).getLanguage().isEmpty()){
                    System.out.println("The language in this grammar is empty");
                }
            }
        }
    }

    /**
     * Метод, който отпечатва всичките начални правила, като дава право на потребителя да направи избор за начално правило
     * @return
     */
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

    /**
     * Метод, който добавя нова дума в езикът на дадена граматика
     * @throws CustomException
     */
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

    /**
     * Метод, който превръща дадена граматика да е в нормална форма на Чомски
     * @param grammar
     * @return
     * @throws CustomException
     */
    public static ContextSensitiveGrammar convertToChomskyNormalForm(ContextSensitiveGrammar grammar) throws CustomException {
        if (Rules.isInChomskyNormalForm(grammar.getRules())) {
            throw new CustomException("Grammar is already in Chomsky normal form.");
        }

        List<Rules> newRules = new ArrayList<>();
        List<Character> newNonTerminals = new ArrayList<>(grammar.getNonTerminals());
        List<Character> terminals = grammar.getTerminals();
        Map<Character, Character> terminalMap = new HashMap<>();
        int ruleCounter = 1;
        char newNonTerminalChar = grammar.getNonTerminals().get(grammar.getNonTerminals().size() - 1);

        for (Rules rule : grammar.getRules()) {
            String rhs = rule.getDescribingPart();

            if (rhs.equals("final")) {
                newRules.add(new Rules(ruleCounter++, rule.getNonTerminal(), rhs));
                continue;
            }

            if (rhs.length() > 1) {
                StringBuilder newRhs = new StringBuilder();
                for (char symbol : rhs.toCharArray()) {
                    if (terminals.contains(symbol)) {
                        if (!terminalMap.containsKey(symbol)) {
                            while (newNonTerminals.contains(newNonTerminalChar)) {
                                newNonTerminalChar++;
                            }
                            terminalMap.put(symbol, newNonTerminalChar);
                            newNonTerminals.add(newNonTerminalChar);
                            newRules.add(new Rules(ruleCounter++, newNonTerminalChar, String.valueOf(symbol)));
                            newNonTerminalChar++;
                        }
                        newRhs.append(terminalMap.get(symbol));
                    } else {
                        newRhs.append(symbol);
                    }
                }
                newRules.add(new Rules(ruleCounter++, rule.getNonTerminal(), newRhs.toString()));
            } else {
                newRules.add(new Rules(ruleCounter++, rule.getNonTerminal(), rhs));
            }
        }

        List<Rules> finalRules = new ArrayList<>();
        for (Rules rule : newRules) {
            String rhs = rule.getDescribingPart();

            if (rhs.equals("final")) {
                finalRules.add(rule);
                continue;
            }

            if (rhs.length() <= 2) {
                finalRules.add(rule);
            } else {
                char currentLhs = rule.getNonTerminal();
                String remainingRhs = rhs;
                while (remainingRhs.length() > 2) {
                    char first = remainingRhs.charAt(0);

                    while (newNonTerminals.contains(newNonTerminalChar)) {
                        newNonTerminalChar++;
                    }
                    char newNonTerminal = newNonTerminalChar;
                    newNonTerminals.add(newNonTerminal);
                    finalRules.add(new Rules(ruleCounter++, currentLhs, "" + first + newNonTerminal));
                    currentLhs = newNonTerminal;
                    remainingRhs = remainingRhs.substring(1);
                    newNonTerminalChar++;
                }
                finalRules.add(new Rules(ruleCounter++, currentLhs, remainingRhs));
            }
        }

        return new ContextSensitiveGrammar(terminals, newNonTerminals, finalRules, grammar.getLanguage());
    }

    /**
     * Метод, който проверява дали дадена дума може да бъде генерирана в граматика, която е в нормална форма на Чомски(Използвайки CYK алгоритъм)
     * @param grammar
     * @param word
     * @return
     * @throws CustomException
     */
    public static boolean isWordInLanguage(ContextSensitiveGrammar grammar, String word) throws CustomException{
        if (!Rules.isInChomskyNormalForm(grammar.getRules())) {
            throw new CustomException("Grammar isn't in chomsky normal form.");
        }

        int n = word.length();
        List<Rules> rules = grammar.getRules();
        Set<Character> nonTerminals = new HashSet<>(grammar.getNonTerminals());
        char startSymbol = 'S';

        Set<Character>[][] P = new HashSet[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                P[i][j] = new HashSet<>();
            }
        }

        for (int i = 0; i < n; i++) {
            char terminal = word.charAt(i);
            for (Rules rule : rules) {
                if (rule.getDescribingPart().length() == 1 && rule.getDescribingPart().charAt(0) == terminal) {
                    P[i][i].add(rule.getNonTerminal());
                }
            }
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    for (Rules rule : rules) {
                        String describingPart = rule.getDescribingPart();
                        if (describingPart.length() == 2) {
                            char A = describingPart.charAt(0);
                            char B = describingPart.charAt(1);
                            if (P[i][k].contains(A) && P[k + 1][j].contains(B)) {
                                P[i][j].add(rule.getNonTerminal());
                            }
                        }
                    }
                }
            }
        }

        return P[0][n - 1].contains(startSymbol);
    }

    /**
     * Метод, който прави действието "Звезда на Клини" върху езика на дадена граматика
     * @param grammar
     * @return
     */
    public static ContextSensitiveGrammar kleeneStar(ContextSensitiveGrammar grammar) {
        int maxLength = 3;
        Set<String> result = new HashSet<>();
        result.add("");

        Set<String> current = new HashSet<>();
        current.add("");

        for (int i = 1; i <= maxLength; i++) {
            Set<String> next = new HashSet<>();
            for (String prefix : current) {
                for (char ch : grammar.getTerminals()) {
                    next.add(prefix + ch);
                }
            }
            result.addAll(next);
            current = next;
        }
        List<String> resultList = new ArrayList<>(result);
        List<String> language = new ArrayList<>(grammar.getLanguage());
        language.addAll(resultList);

        return new ContextSensitiveGrammar(grammar.getTerminals(), grammar.getNonTerminals(), grammar.getRules(),language);
    }
}
