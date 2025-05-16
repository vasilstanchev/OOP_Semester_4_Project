package grammar;

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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Rule №%d: ", this.getNumber()));
        stringBuilder.append(String.format("%c -> %s", this.getNonTerminal(), this.getDescribingPart()));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    void printRuleInfo(){
        System.out.println(this.toString());
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
    public static boolean isInChomskyNormalForm(List<Rules> rules) {
        for (Rules rule : rules) {
            String describingPart = rule.getDescribingPart();
            if (describingPart.length() == 1 && Character.isLowerCase(describingPart.charAt(0))) {
                continue;
            }
            if (describingPart.length() == 2 && Character.isUpperCase(describingPart.charAt(0)) && Character.isUpperCase(describingPart.charAt(1))) {
                continue;
            }
            if (describingPart.equals("final")) {
                continue;
            }
            return false;
        }

        return true;
    }

}
