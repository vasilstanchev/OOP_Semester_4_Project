package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class Empty extends Command{
    @Override
    public void execute(CommandParameters parameters) {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.isEmpty()) {
            System.out.println("No Id parameter was provided.");
            return;
        }
        try {
            int id = Integer.parseInt(args.get(0));
            ContextSensitiveGrammar.isLanguageEmpty(grammars, id);
        } catch (Exception e) {
            System.out.println("You've entered a wrong Id.");
        }
    }
}
