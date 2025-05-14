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
        stringBuilder.append("\tremoveRule <id> <n> - removes a rule to the given grammar by the given number of the rule\n");
        stringBuilder.append("\tunion <id1> <id2> - finds the union of two grammars and creates a new one from it. then it displays the new id\n");
        stringBuilder.append("\tconcat <id1> <id2> - finds the concat of two grammars and creates a new one from it. then it displays the new id\n");
        stringBuilder.append("\tchomsky <id> - checks if a given grammar is in Chomsky normal form\n");
        stringBuilder.append("\tcyk <id> - checks if a given word is in the language of a given grammar using the CYK algorithm\n");
        stringBuilder.append("\titer <id> - Finds the result of the \"iteration\" (Kleene star) operation over a grammar and creates a new grammar. then it displays the new id\n");
        stringBuilder.append("\tchomskify <id> - converts a grammar to Chomsky normal form. then it displays the new id\n");
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
