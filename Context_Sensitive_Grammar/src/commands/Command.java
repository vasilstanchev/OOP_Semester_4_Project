package commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import grammar.ContextSensitiveGrammar;

public class Command {
    private static final Map<String, Supplier<? extends Command>> commands = new HashMap<>();
    static {
        commands.put("list", ListId::new);
        commands.put("print", PrintGrammar::new);
        commands.put("printAll", PrintGrammar::new);
        commands.put("help", Help::new);
    }
    public void execute(){};
    public void execute(List<ContextSensitiveGrammar> grammars){};
    public static void instructions(List<ContextSensitiveGrammar> grammars){
        Scanner scanner = new Scanner(System.in);
        String commandLine = "";
        String commandName = "";
        System.out.println("Type help to see the available commands:");
        while(!commandName.contains("exit")){ // to be optimized neither switch or if-else
            System.out.println("Enter command:");
            commandLine = scanner.nextLine();
            String[] commandElements = commandLine.split(" ");
            commandName = commandElements[0];
            if ("help".equals(commandName) && commands.containsKey("help")){
                commands.get(commandName).get().execute();
            }
            else if ("list".equals(commandName) && commands.containsKey("list")){
                commands.get(commandName).get().execute(grammars);
            }
            else if (commandName.contains("print")){
                if (grammars.isEmpty()){
                    System.out.println("There aren't any grammars entered");
                }
                else{
                    int id = Integer.parseInt(commandElements[1]);
                    boolean isTrue = false;
                    for (int i = 0; i < grammars.size(); i++) {
                        if (grammars.get(i).getId() == id){
                            PrintGrammar.execute(grammars.get(i));
                            isTrue = true;
                        }
                    }
                    if (!isTrue){
                        System.out.println("The entered id doesn't match any grammar");
                    }
                }
            }
            else if (commandName.contains("printAll")){
                if (grammars.isEmpty()){
                    System.out.println("There aren't any grammars entered");
                }
                else{
                    for (int i = 0; i < grammars.size(); i++) {
                        PrintGrammar.execute(grammars.get(i));
                    }
                }
            }
            else if (commandName.contains("addGrammar")){
                ContextSensitiveGrammar.addGrammar(grammars);
            }
            else if (commandName.contains("addRule")){
                int id = Integer.parseInt(commandElements[1]);
                String describingPart = commandElements[2];
                for (int i = 0; i < grammars.size(); i++) {
                    if(grammars.get(i).getId() == id){
                        grammars.get(i).addRule(describingPart);
                    }
                }
            }
            else if (commandName.contains("addWord")){
                int id = Integer.parseInt(commandElements[1]);
                for (int i = 0; i < grammars.size(); i++) {
                    if(grammars.get(i).getId() == id){
                        grammars.get(i).addWord();
                    }
                }
            }
            else if (commandName.contains("removeRule")){
                //tba
            }
            else if (commandName.contains("empty")){
                int id = Integer.parseInt(commandElements[1]);
                ContextSensitiveGrammar.isLanguageEmpty(grammars, id);
            }
            else if (commandName.contains("exit")){
                System.out.println("Exiting the program...");
            }
            else{
                System.out.println("Enter a valid command. Type help to see the full list!");
            }
        }
    }
}
