package commands;

import grammar.ContextSensitiveGrammar;

import java.io.File;
import java.util.List;

public class SaveAsFile extends Command{
    /**
     * Пренаписан метод, който извиква метода за записване във файл, като предава пътеката на файла, въведена от потребителя
     * @param parameters
     * @throws CustomException
     */
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        String filePath;
        if (args.isEmpty()){
            throw new CustomException("The filepath is missing");
        }
        if (args.size()>1){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(args.get(0));
            stringBuilder.append(" ");
            stringBuilder.append(args.get(1));
            filePath= stringBuilder.toString();
        }
        else {
            filePath = args.get(0);
        }
        try {
            System.out.println(filePath);
            File newFile = OpenFile.resolveFilePath(filePath);
            SaveFile.saveList(grammars, newFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
