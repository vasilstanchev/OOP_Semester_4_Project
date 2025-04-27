package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddGrammar extends Command{
    @Override
    public void execute(CommandParameters parameters) {
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        ContextSensitiveGrammar.addGrammar(grammars);
    }
}
