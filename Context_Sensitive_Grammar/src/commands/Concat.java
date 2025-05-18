package commands;

import grammar.ContextSensitiveGrammar;
import grammar.Rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Concat extends Command{
    /**
     * Метод, който конкатенира езиците на двете дадени граматики и връща нова
     * @param g1
     * @param g2
     * @return
     */
    private ContextSensitiveGrammar concatOfTwoGrammars(ContextSensitiveGrammar g1, ContextSensitiveGrammar g2) {
        Set<Character> setTerminals = new HashSet<>(g1.getTerminals());
        setTerminals.addAll(g2.getTerminals());
        List<Character> terminals = new ArrayList<>(setTerminals);

        Set<Character> setNonTerminals = new HashSet<>(g1.getNonTerminals());
        setNonTerminals.addAll(g2.getNonTerminals());
        List<Character> nonTerminals = new ArrayList<>(setNonTerminals);

        List<Rules> rules = new ArrayList<>(g1.getRules());
        rules.addAll(g2.getRules());

        List<String> result = new ArrayList<>();
        for (String word1 : g1.getLanguage()) {
            for (String word2 : g2.getLanguage()) {
                result.add(word1 + word2);
            }
        }

        ContextSensitiveGrammar newGrammar = new ContextSensitiveGrammar(terminals, nonTerminals, rules, result);
        return newGrammar;
    }

    /**
     * Пренаписан метод, който извиква метода за конкатенация на две граматики, като предава параметрите въведени от потребителя(уникалните идентификатори на граматиките)
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
            ContextSensitiveGrammar newGrammar = this.concatOfTwoGrammars(g1,g2);
            grammars.add(newGrammar);
            System.out.println("New grammar is: " + newGrammar);
            System.out.println("Rules size: " + newGrammar.getRules().size());
            System.out.println("Language: " + newGrammar.getLanguage());
            newGrammar.listId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
