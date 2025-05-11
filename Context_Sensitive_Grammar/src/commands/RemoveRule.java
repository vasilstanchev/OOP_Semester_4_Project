package commands;

import grammar.ContextSensitiveGrammar;
import java.util.List;

public class RemoveRule extends Command{
    @Override
    public void execute(CommandParameters parameters)throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 2) {
            throw new CustomException("Not enough parameters were provided.");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            ContextSensitiveGrammar grammar = ContextSensitiveGrammar.returnGrammarById(id, grammars);
            grammar.removeRule(Integer.parseInt(args.get(1)));
        } catch (Exception e) {
            System.out.println("You've entered a wrong Id.");
        }
    }
}
