package commands;

import grammar.ContextSensitiveGrammar;
import grammar.Grammar;
import grammar.Rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Union extends Command{
    /**
     * Метод, който обединява езиците на двете дадени граматики и връща нова
     * @param g1
     * @param g2
     * @return
     */
    private ContextSensitiveGrammar unionOfTwoGrammars(ContextSensitiveGrammar g1, ContextSensitiveGrammar g2){
        List<Character> terminals;
        List<Character> nonTerminals;
        List<Rules> rules = new ArrayList<>(g1.getRules());
        List<String> language;

        Set<Character> setTerminals = new HashSet<>(g1.getTerminals());
        setTerminals.addAll(g2.getTerminals());
        terminals = new ArrayList<>(setTerminals);

        Set<Character> setNonTerminals = new HashSet<>(g1.getNonTerminals());
        setNonTerminals.addAll(g2.getNonTerminals());
        nonTerminals = new ArrayList<>(setNonTerminals);

        Set<String> setLanguage = new HashSet<>(g1.getLanguage());
        setLanguage.addAll(g2.getLanguage());
        language = new ArrayList<>(setLanguage);

        for (Rules g2Rule : g2.getRules()) {
            if (!rules.contains(g2Rule)) {
                rules.add(g2Rule);
            }
        }

        ContextSensitiveGrammar newGrammar = new ContextSensitiveGrammar(terminals, nonTerminals, rules, language);
        return newGrammar;
    }

    /**
     * Пренаписан метод, който извиква метода за обединение на две граматики, като предава параметрите въведени от потребителя(уникалните идентификатори на граматиките)
     * @param parameters
     * @throws CustomException
     */
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 2) {
            throw new CustomException("Not enough parameters were provided.");
        }
        try {
            int id1 = Integer.parseInt(args.get(0));
            int id2 = Integer.parseInt(args.get(1));
            ContextSensitiveGrammar g1 = ContextSensitiveGrammar.returnGrammarById(id1, grammars);
            ContextSensitiveGrammar g2 = ContextSensitiveGrammar.returnGrammarById(id2, grammars);
            ContextSensitiveGrammar newGrammar = this.unionOfTwoGrammars(g1,g2);
            grammars.add(newGrammar);
            newGrammar.listId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
