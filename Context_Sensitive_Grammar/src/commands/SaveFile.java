package commands;

import grammar.ContextSensitiveGrammar;

import java.io.*;
import java.util.List;

public class SaveFile extends Command{
    /**
     * Метод за записване на списък от граматики във файл
     * @param grammars
     * @param file
     */
    public static void saveList(List<ContextSensitiveGrammar> grammars, File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(grammars);
            System.out.println("The grammars were save successfully");
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    /**
     * Метод за записване на граматика във файл
     * @param grammar
     * @param file
     */
    public void saveGrammar(ContextSensitiveGrammar grammar, File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(grammar);
            System.out.println("The grammar was save successfully");
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    /**
     * Пренаписан метод, който извиква метода за записване във файл, като предава името на файла, въведен от потребителя
     * @param parameters
     * @throws CustomException
     */
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.isEmpty()){
            SaveFile.saveList(grammars, parameters.getFile());
        }
        else if (args.size() != 2){
            throw new CustomException("Not enough parameters were provided.");
        }
        else {
            try {
                int id = Integer.parseInt(args.get(0));
                File newFile = OpenFile.resolveFilePath(args.get(1));
                ContextSensitiveGrammar grammar = ContextSensitiveGrammar.returnGrammarById(id, grammars);
                this.saveGrammar(grammar, newFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
