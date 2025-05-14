package grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Grammar {
    protected int id;
    protected List<Character> terminals;
    protected List<Character> nonTerminals;
    protected List<Rules> rules;
    protected List<String> language;
    Grammar(List<Character> terminals){
        id = generateId();
        setTerminals(terminals);
        nonTerminals = new ArrayList<>();
        rules = new ArrayList<>();
        language = new ArrayList<>();
    }
   Grammar(List<Character> terminals, List<Character> nonTerminals, List<Rules> rules, List<String> language){
        id = generateId();
        setTerminals(terminals);
        setNonTerminals(nonTerminals);
        setRules(rules);
        setLanguage(language);
   }
    public int generateId(){
        Random random = new Random();
        int minNum = 1;
        int maxNum = 1000;
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
    public void setLanguage(List<String> language) {
        this.language = language;
    }
}
