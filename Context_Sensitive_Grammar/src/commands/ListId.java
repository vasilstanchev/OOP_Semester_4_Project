package commands;

import grammar.ContextSensitiveGrammar;

import java.util.List;

public class ListId extends Command{
    @Override
    public void execute(CommandParameters parameters){
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        for (int i = 0; i < grammars.size(); i++) {
            grammars.get(i).listId();
        }
    }
}
