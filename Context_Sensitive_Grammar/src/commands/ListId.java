package commands;

import grammar.ContextSensitiveGrammar;

import java.util.List;

public class ListId extends Command{
    public void execute(List<ContextSensitiveGrammar> grammars){
        for (int i = 0; i < grammars.size(); i++) {
            grammars.get(i).listId();
        }
    }
}
