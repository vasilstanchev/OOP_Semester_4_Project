package commands;

import java.util.*;
import java.util.function.Supplier;

import grammar.ContextSensitiveGrammar;

public abstract class Command {
    private static final Map<String, Supplier<? extends Command>> commands = new HashMap<>();
    static {
        commands.put("list", ListId::new);
        commands.put("print", PrintGrammar::new);
        commands.put("printAll", PrintGrammar::new);
        commands.put("help", Help::new);
        //commands.put("addGrammar", Help::new);
        //commands.put("addRule", Help::new);
        //commands.put("addWord", Help::new);
        //commands.put("empty", Help::new);
    }
    public void execute() {
        execute(new CommandParameters(null, new ArrayList<>()));
    }
    public abstract void execute(CommandParameters parameters);
    public static void instructions(List<ContextSensitiveGrammar> grammars){
        Scanner scanner = new Scanner(System.in);
        String commandLine = "";
        String commandName = "";
        List<String> args = new ArrayList<>();
        System.out.println("Type help to see the available commands:");
        while(!commandName.contains("exit")){ // to be optimized neither switch or if-else
            System.out.println("Enter command:");
            commandLine = scanner.nextLine();
            String[] commandElements = commandLine.split(" ");
            commandName = commandElements[0];
            for (int i = 1; i < commandElements.length; i++) {
                args.add(commandElements[i]);
            }

            Supplier<? extends Command> commandSupplier = commands.get(commandName);
            if (commandSupplier != null) {
                Command command = commandSupplier.get();
                CommandParameters context = new CommandParameters(grammars, args);
                command.execute(context);
            }
            else{
                System.out.println("Enter a valid command. Type help to see the full list!");
            }
        }
    }
}
