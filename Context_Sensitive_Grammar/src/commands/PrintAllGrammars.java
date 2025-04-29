package commands;

import grammar.ContextSensitiveGrammar;

import java.util.List;

public class PrintAllGrammars extends Command {
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (grammars.isEmpty()) {
            throw new CustomException("There are no grammars entered");
        }

        for (int i = 0; i < grammars.size(); i++) {
            System.out.println("Grammar " + grammars.get(i).getId() + ": " + grammars.get(i).toString());
        }

    }
}
