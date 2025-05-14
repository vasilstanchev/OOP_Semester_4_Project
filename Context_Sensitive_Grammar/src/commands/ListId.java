package commands;

import grammar.ContextSensitiveGrammar;

import java.util.List;

public class ListId extends Command{
    @Override
    public void execute(CommandParameters parameters)throws CustomException{
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if(grammars.isEmpty()){
            throw new CustomException("There aren't any available grammars");
        }
        for (int i = 0; i < grammars.size(); i++) {
            grammars.get(i).listId();
        }
    }
}
