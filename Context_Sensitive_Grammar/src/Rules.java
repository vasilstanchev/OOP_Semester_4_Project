import java.util.ArrayList;
import java.util.List;

public class Rules {
    private int number;
    private char nonTerminal;
    private String describingPart;
    Rules(int number, char nonTerminal, String describingPart)
    {
        setNumber(number);
        setNonTerminal(nonTerminal);
        setDescribingPart(describingPart);
    }
    public int getNumber() {
        return number;
    }
    public char getNonTerminal() {
        return nonTerminal;
    }
    public String getDescribingPart() {
        return describingPart;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setNonTerminal(char nonTerminal) {
        this.nonTerminal = nonTerminal;
    }
    public void setDescribingPart(String describingPart) {
        this.describingPart = describingPart;
    }
    void printRuleInfo(){
        System.out.printf("Rule №%d: ", this.getNumber());
        System.out.printf("%c -> %s", this.getNonTerminal(), this.getDescribingPart());
        System.out.println();
    }
    public static Rules getRuleByNumber(List<Rules> rules, int number){
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).getNumber() == number){
                return rules.get(i);
            }
        }
        return null;
    }
    public static boolean checkForFinal(List<Rules> rules){
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).getDescribingPart().equals("final")){
                return true;
            }
        }
        if (rules.isEmpty()){
            System.out.println("There aren't any rules added");
            return false;
        }
        return false;
    }
    public static String connectTerminals(Rules rule, String word){
        String newWord;
        String character = Character.toString(rule.getNonTerminal());
        if (word.contains(character)){
            newWord = word.replaceAll(character, rule.getDescribingPart());
            return newWord;
        }
        else{
            return word;
        }
    }
}
