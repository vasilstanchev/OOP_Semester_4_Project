package commands;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

import grammar.ContextSensitiveGrammar;

public abstract class Command {
    private static final Map<String, Supplier<? extends Command>> commands = new HashMap<>();
    static {
        commands.put("list", ListId::new);
        commands.put("print", PrintGrammar::new);
        commands.put("printAll", PrintAllGrammars::new);
        commands.put("help", Help::new);
        commands.put("addGrammar", AddGrammar::new);
        commands.put("addRule", AddRule::new);
        commands.put("removeRule", RemoveRule::new);
        commands.put("addWord", AddWord::new);
        commands.put("union", Union::new);
        commands.put("concat", Concat::new);
        commands.put("chomsky", Chomsky::new);
        commands.put("cyk", Cyk::new);
        commands.put("iter", Iter::new);
        commands.put("chomskify", Chomskify::new);
        commands.put("empty", Empty::new);
        commands.put("closeFile", CloseFile::new);
        commands.put("openFile", OpenFile::new);
        commands.put("saveAsFile", SaveAsFile::new);
        commands.put("save", SaveFile::new);
    }

    /**
     * Извикване на метода за командите, когато няма подадени параметри. Създава нова инстанция с "null" стойности
     * @throws CustomException
     */
    public void execute()throws CustomException {
        execute(new CommandParameters(null, null,null));
    }
    /**
     * Абстрактен метод за изпълнение на команда с параметри.
     * @param parameters
     * @throws CustomException
     */
    public abstract void execute(CommandParameters parameters) throws CustomException;

    /**
     * Главният метод за цялата програма, който служи като главно пространство за обработка на командите и техните параметри
     * @param grammars
     * @throws CustomException
     */
    public static void instructions(List<ContextSensitiveGrammar> grammars) throws CustomException{
        Scanner scanner = new Scanner(System.in);
        String commandLine = "";
        String commandName = "";
        CommandParameters context = new CommandParameters();
        File currentFile = null;
        System.out.println("Type help to see the available commands:");
        while(!context.getCommand().contains("exit")){
            try {
                System.out.println("Enter command:");
                System.out.printf("> ");
                commandLine = scanner.nextLine();

                context = new CommandParameters(grammars, commandLine,currentFile);
                context.setFile(currentFile);
                commandName = context.getCommand();
                Supplier<? extends Command> commandSupplier = commands.get(commandName);

                if (commandSupplier != null) {
                    Command command = commandSupplier.get();
                    command.execute(context);
                    grammars = context.getGrammars();
                    currentFile = context.getFile();
                } else if (commandName.contains("exit")) {
                    System.out.println("Exiting...");
                } else {
                    System.out.println("Enter a valid command. Type help to see the full list!");
                }
            } catch (Exception e){
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
