import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Command {
    public static void instructions(List<Grammar> grammars){
        Scanner scanner = new Scanner(System.in);
        String commandLine = "";
        String commandName = "";
        System.out.println("Type help to see the available commands:");
        while(!commandName.contains("exit")){
            System.out.println("Enter command:");
            commandLine = scanner.nextLine();
            String[] commandElements = commandLine.split(" ");
            commandName = commandElements[0];
            if (commandName.contains("help")){
                Command.help();
            }
            else if (commandName.contains("list")){
                Grammar.listIds(grammars);
            }
            else if (commandName.contains("printAll")){
                if (grammars.isEmpty()){
                    System.out.println("There aren't any grammars entered");
                }
                else{
                    for (int i = 0; i < grammars.size(); i++) {
                        grammars.get(i).printGrammar();
                    }
                }
            }
            else if (commandName.contains("addGrammar")){
                Grammar.addGrammar(grammars);
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
            else if (commandName.contains("removeRule")){
                //tba
            }
            else if (commandName.contains("exit")){
                System.out.println("Exiting the program...");
            }
            else{
                System.out.println("Enter a valid command. Type help to see the full list!");
            }
        }
    }
    public static void help(){
        System.out.println("List of available commands:");
        System.out.printf("\tlist - prints the ids for all the entered grammars\n");
        System.out.printf("\tprint <id> - prints info about the given grammar matched to the id\n");
        System.out.printf("\tprintAll - prints info about every entered grammar\n");
        System.out.printf("\taddGrammar - adds a new grammar with unique id\n");
        System.out.printf("\taddRule <id> <rule> - adds a new rule to the given grammar\n");
        System.out.printf("\tremoveRule <id> <n> - removes a rule to the given grammar\n");
        System.out.printf("\thelp - lists all available commands\n");
        System.out.printf("\texit - exits the program\n");
    }
}
