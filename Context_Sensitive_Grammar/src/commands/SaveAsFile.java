package commands;

import grammar.ContextSensitiveGrammar;

import java.io.File;
import java.util.List;

public class SaveAsFile extends Command{
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 1){
            throw new CustomException("The filepath is missing");
        }
        else {
            try {
                File newFile = OpenFile.resolveFilePath(args.get(0));
                SaveFile.saveList(grammars, newFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
