import java.util.ArrayList;

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
}
