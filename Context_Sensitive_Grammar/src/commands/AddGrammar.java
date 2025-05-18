package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddGrammar extends Command{
    /**
     * Пренаписан метод, който извиква метода за създаване на нова граматика, като предава параметрите въведени от потребителя(терминали)
     * @param parameters
     * @throws CustomException
     */
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
