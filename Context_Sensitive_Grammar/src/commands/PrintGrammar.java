package commands;

import java.util.List;
import grammar.ContextSensitiveGrammar;
import grammar.Rules;

public class PrintGrammar extends Command{
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
            stringBuilder.append("grammar.Rules:\n");
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

    @Override
    public void execute(CommandParameters parameters) throws CustomException{
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.isEmpty()) {
            throw new CustomException("There's no id entered");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            System.out.println("Grammar " + id + ": " + grammars.get(id).toString());
        } catch (Exception e) {
            System.out.println("You've entered a wrong Id.");
        }
    }
}
