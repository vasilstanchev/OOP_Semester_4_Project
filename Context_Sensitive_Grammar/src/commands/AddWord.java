package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddWord extends Command{
    @Override
    public void execute(CommandParameters parameters)throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 1) {
            throw new CustomException("Not enough parameters were provided.");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            ContextSensitiveGrammar grammar = ContextSensitiveGrammar.returnGrammarById(id, grammars);
            grammar.addWord();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
