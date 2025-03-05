import java.util.ArrayList;
import java.util.Scanner;

public class Command {
    public static void instructions(ArrayList<Grammar> grammars){
        Scanner scanner = new Scanner(System.in);
        String commandName = "";
        System.out.println("Type help to see the available commands:");
        while(!commandName.contains("exit")){
            System.out.println("Enter command:");
            commandName = scanner.nextLine();
            if (commandName.contains("help")){
                Command.help();
            }
            else if (commandName.contains("print")){
                //tba
            }
            else if (commandName.contains("addGrammar")){
                Grammar.addGrammar(grammars);
            }
            else if (commandName.contains("addRule")){
                //tba
            }
            else if (commandName.contains("removeRule")){
                //tba
            }
            else if (commandName.contains("exit")){
                System.out.println("Goodbye!!");
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
        System.out.printf("\taddGrammar - adds a new grammar with unique id\n");
        System.out.printf("\taddRule <id> <rule> - adds a new rule to the given grammar\n");
        System.out.printf("\tremoveRule <id> <n> - removes a rule to the given grammar\n");
        System.out.printf("\thelp - lists all available commands\n");
        System.out.printf("\texit - exits the program\n");
    }
}
