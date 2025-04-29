package commands;

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
        commands.put("addWord", AddWord::new);
        commands.put("empty", Empty::new);
    }
    public void execute()throws CustomException {
        execute(new CommandParameters(null, null));
    }
    public abstract void execute(CommandParameters parameters) throws CustomException;
    public static void instructions(List<ContextSensitiveGrammar> grammars) throws CustomException{
        Scanner scanner = new Scanner(System.in);
        String commandLine = "";
        String commandName = "";
        List<String> args = new ArrayList<>();
        CommandParameters context=new CommandParameters();
        System.out.println("Type help to see the available commands:");
        while(!context.getCommand().contains("exit")){
            System.out.println("Enter command:");
            commandLine = scanner.nextLine();

            Supplier<? extends Command> commandSupplier = commands.get(commandName);
            context = new CommandParameters(grammars, commandLine);
            commandName=context.getCommand();
            if (commandSupplier != null) {
                Command command = commandSupplier.get();
                command.execute(context);
            }
            else{
                System.out.println("Enter a valid command. Type help to see the full list!");
            }
        }
    }
}
