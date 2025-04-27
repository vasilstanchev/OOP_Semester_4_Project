package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddRule extends Command{
    @Override
    public void execute(CommandParameters parameters) {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 2) {
            throw new IllegalArgumentException("Not enough parameters were provided.");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            grammars.get(id).addRule(args.get(1));
        } catch (Exception e) {
            System.out.println("You've entered a wrong Id.");
        }
    }
}
