package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddWord extends Command{
    @Override
    public void execute(CommandParameters parameters) {
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
    }
}
