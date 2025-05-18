package commands;

import grammar.ContextSensitiveGrammar;

import java.util.List;

public class Cyk extends Command{
    /**
     * Пренаписан метод, който извиква метода за проверка дали дадена дума е в нормализирана граматика на Чомски, като предава параметрите въведени от потребителя(уникалния идентификатор на граматика)
     * @param parameters
     * @throws CustomException
     */
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 2) {
            throw new CustomException("Not enough parameters were provided.");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            String word = args.get(1);
            ContextSensitiveGrammar grammar = ContextSensitiveGrammar.returnGrammarById(id, grammars);
            if (ContextSensitiveGrammar.isWordInLanguage(grammar, word)){
                System.out.println("The word is inside of the language");
            }
            else {
                System.out.println("The word isn't inside of the language");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
