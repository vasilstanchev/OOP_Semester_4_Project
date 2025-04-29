package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddGrammar extends Command{
    @Override
    public void execute(CommandParameters parameters)throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.isEmpty()){
            throw new CustomException("No terminals were provided");
        }
        ContextSensitiveGrammar.addGrammar(grammars,args);
    }
}
