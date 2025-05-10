package commands;

public class Help extends Command{
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("List of available commands:\n");
        stringBuilder.append("\tlist - prints the ids for all the entered grammars\n");
        stringBuilder.append("\tprint <id> - prints info about the given grammar matched to the id\n");
        stringBuilder.append("\tprintAll - prints info about every entered grammar\n");
        stringBuilder.append("\taddGrammar <terminals> - adds a new grammar with unique id. terminals should be separated by a space\n");
        stringBuilder.append("\taddRule <id> <rule> - adds a new rule to the given grammar\n");
        stringBuilder.append("\taddWord <id> - adds a new word to the given grammar\n");
        //System.out.printf("\tremoveRule <id> <n> - removes a rule to the given grammar\n");
        stringBuilder.append("\tempty <id> - check whether the language in a given grammar is empty\n");
        stringBuilder.append("\thelp - lists all available commands\n");
        stringBuilder.append("\texit - exits the program\n");
        return stringBuilder.toString();
    }
    @Override
    public void execute(CommandParameters parameters) {
        System.out.printf(this.toString());
    }
}
