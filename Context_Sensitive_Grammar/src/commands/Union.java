package commands;

import grammar.ContextSensitiveGrammar;
import grammar.Grammar;

import java.util.List;

public class Union extends Command{
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
            ContextSensitiveGrammar newGrammar = ContextSensitiveGrammar.unionOfTwoGrammars(g1,g2);
            grammars.add(newGrammar);
            newGrammar.listId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
