package commands;

import grammar.ContextSensitiveGrammar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenFile extends Command{
    public static File resolveFilePath(String pathOrFileName) {
        File f = new File(pathOrFileName);
        return f.isAbsolute() ? f : new File(System.getProperty("user.dir"), pathOrFileName);
    }
    private List<ContextSensitiveGrammar> loadList(File file) throws CustomException{
        if (!file.exists()) {
           throw new CustomException("File doesn't exist");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                System.out.println("The grammar was loaded");
                return (List<ContextSensitiveGrammar>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occured while reading the file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        List<String> args = parameters.getArgs();
        List<ContextSensitiveGrammar> grammars = parameters.getGrammars();
        if (args.size() != 1) {
            throw new CustomException("Not enough parameters were provided.");
        }
        try {
            String fileNameOrPath = args.get(0);
            parameters.setFile(OpenFile.resolveFilePath(fileNameOrPath));
            grammars = this.loadList(parameters.getFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
