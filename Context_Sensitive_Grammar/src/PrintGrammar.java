import java.util.List;

public class PrintGrammar {
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

    private static String toString(ContextSensitiveGrammar grammar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Id: %d\n", grammar.getId()));
        Boolean[] nullValidator = validateGrammar(grammar.getTerminals(), grammar.getNonTerminals(), grammar.getRules(), grammar.getLanguage());
        List<Character> terminals = grammar.getTerminals();
        List<Character> nonTerminals = grammar.getNonTerminals();
        List<Rules> rules = grammar.getRules();
        List<String> language = grammar.getLanguage();

        if (nullValidator[0] == false){
            stringBuilder.append("Terminals:\n");
            stringBuilder.append("{ ");
            for (int i = 0; i < terminals.size(); i++) {
                stringBuilder.append(String.format("%c ",terminals.get(i)));
            }
            stringBuilder.append("}");
            stringBuilder.append("\n");
        }
        if (nullValidator[1] == false) {
            stringBuilder.append("Non-Terminals:\n");
            stringBuilder.append("{ ");
            for (int i = 0; i < nonTerminals.size(); i++) {
                stringBuilder.append(String.format("%c ", nonTerminals.get(i)));
            }
            stringBuilder.append("}");
            stringBuilder.append("\n");
        }
        if (nullValidator[2] == false) {
            stringBuilder.append("Rules:\n");
            for (int i = 0; i < rules.size(); i++) {
                stringBuilder.append(rules.get(i).toString());
            }
        }
        if (nullValidator[3] == false) {
            stringBuilder.append("Words inside the language:\n");
            stringBuilder.append("{ ");
            for (int i = 0; i < language.size(); i++) {
                stringBuilder.append(String.format("%s ", language.get(i)));
            }
            stringBuilder.append("}");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void execute(ContextSensitiveGrammar grammar){
        System.out.println(PrintGrammar.toString(grammar));
    }
}
