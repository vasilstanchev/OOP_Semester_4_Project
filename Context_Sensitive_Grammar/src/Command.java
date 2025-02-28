import java.util.Scanner;

public class Command {
    public static void instructions(){
        Scanner scanner = new Scanner(System.in);
        String commandName = "";
        System.out.println("Type help to see the available commands:");
        while(!commandName.contains("exit")){
            System.out.println("Enter command:");
            commandName = scanner.nextLine();
            if (commandName.contains("help")){
                Command.help();
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
        System.out.printf("\thelp - lists all available commands\n");
        System.out.printf("\texit - exits the program\n");
    }
}
