package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class Empty extends Command{
    /**
     * Пренаписан метод, който извиква метода за проверка за празен език в дадена граматика, като предава параметрите въведени от потребителя(уникалния идентификатор на граматика)
     * @param parameters
     */
    @Override
    public void execute(CommandParameters parameters)throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.isEmpty()) {
            throw new CustomException("No Id parameter was provided.");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            ContextSensitiveGrammar.isLanguageEmpty(grammars, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
