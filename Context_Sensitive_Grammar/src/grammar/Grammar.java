package grammar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Grammar implements Serializable {
    protected int id;
    protected List<Character> terminals;
    protected List<Character> nonTerminals;
    protected List<Rules> rules;
    protected List<String> language;

    /**
     * Конструктор, който създава нова граматика с даден списък с терминали
     * @param terminals
     */
    Grammar(List<Character> terminals){
        id = generateId();
        setTerminals(terminals);
        nonTerminals = new ArrayList<>();
        rules = new ArrayList<>();
        language = new ArrayList<>();
    }
    /**
     * Конструктор, който създава нова граматика с всичките атрибути
     * @param terminals
     */
   Grammar(List<Character> terminals, List<Character> nonTerminals, List<Rules> rules, List<String> language){
        id = generateId();
        setTerminals(terminals);
        setNonTerminals(nonTerminals);
        setRules(rules);
        setLanguage(language);
   }

    /**
     * Метод, който създава уникален идентификатор в граница(1-1000)
     * @return
     */
    public int generateId(){
        Random random = new Random();
        int minNum = 1;
        int maxNum = 1000;
        return random.nextInt(maxNum - minNum + 1) + minNum;
    }

    /**
     * Метод, който връща уникалния идентификатор на дадената граматика
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, който връща копие на списъка от терминали на дадената граматика
     * @return
     */
    public List<Character> getTerminals() {
        return Collections.unmodifiableList(terminals);
    }
    /**
     * Метод, който връща копие на списъка от нетерминали на дадената граматика
     * @return
     */
    public List<Character> getNonTerminals() {
        return Collections.unmodifiableList(nonTerminals);
    }
    /**
     * Метод, който връща копие на списъка от правила на дадената граматика
     * @return
     */
    public List<Rules> getRules() {
        return Collections.unmodifiableList(rules);
    }
    /**
     * Метод, който връща копие на списъка от терминали на дадената граматика
     * @return
     */
    public List<String> getLanguage() {
        return Collections.unmodifiableList(language);
    }

    /**
     * Метод, който обновява списъка с терминалит
     * @param terminals
     */
    public void setTerminals(List<Character> terminals) {
        this.terminals = terminals != null ? new ArrayList<>(terminals) : new ArrayList<>();
    }

    /**
     * Метод, който обновява списъка с нетерминалит
     * @param nonTerminals
     */
    public void setNonTerminals(List<Character> nonTerminals) {
        this.nonTerminals = nonTerminals != null ? new ArrayList<>(nonTerminals) : new ArrayList<>();
    }

    /**
     * Метод, който обновява списъка с правила
     * @param rules
     */
    public void setRules(List<Rules> rules) {
        this.rules = rules != null ? new ArrayList<>(rules) : new ArrayList<>();
    }

    /**
     * Метод, който обновява езика
     * @param language
     */
    public void setLanguage(List<String> language) {
        this.language = language != null ? new ArrayList<>(language) : new ArrayList<>();
    }
}
