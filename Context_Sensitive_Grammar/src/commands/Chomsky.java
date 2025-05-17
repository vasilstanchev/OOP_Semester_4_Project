package commands;

import grammar.ContextSensitiveGrammar;
import grammar.Rules;

import java.util.List;

public class Chomsky extends Command{
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 1) {
            throw new CustomException("There's no id entered");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            ContextSensitiveGrammar grammar = ContextSensitiveGrammar.returnGrammarById(id, grammars);
            if (Rules.isInChomskyNormalForm(grammar.getRules())){
                System.out.println("The given grammar is in Chomsky normal form.");
            }
            else{
                System.out.println("The given grammar isn't in Chomsky normal form.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
