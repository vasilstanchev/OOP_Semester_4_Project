package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class AddRule extends Command{
    /**
     * Пренаписан метод, който извиква метода за създаване на ново правило към дадена граматика, като предава параметрите въведени от потребителя(правилото и уникалния идентификатор на граматика)
     * @param parameters
     * @throws CustomException
     */
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
            grammar.addRule(args.get(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
