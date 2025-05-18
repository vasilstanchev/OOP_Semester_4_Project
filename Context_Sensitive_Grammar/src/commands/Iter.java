package commands;

import grammar.ContextSensitiveGrammar;

import java.util.List;

public class Iter extends Command{
    /**
     * Пренаписан метод, който извиква метода за прилагане действието "Звезда на Клини" на дадена граматика, като предава параметрите въведени от потребителя(уникалния идентификатор на граматика)
     * @param parameters
     * @throws CustomException
     */
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 1) {
            throw new CustomException("There's no id entered");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            ContextSensitiveGrammar grammar = ContextSensitiveGrammar.returnGrammarById(id, grammars);
            ContextSensitiveGrammar newGrammar = ContextSensitiveGrammar.kleeneStar(grammar);
            grammars.add(newGrammar);
            newGrammar.listId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
