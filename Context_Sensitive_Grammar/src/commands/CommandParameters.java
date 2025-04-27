package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class CommandParameters {
    private List<ContextSensitiveGrammar> grammars;
    private List<String> args;
    private String command;

    public CommandParameters(){
        command="";
    }
    public CommandParameters(List<ContextSensitiveGrammar> grammars, String commandLine) {
        this.grammars = grammars;
        if (commandLine == null || commandLine.isEmpty()){
            throw new IllegalArgumentException("Command line is missing");
        }
        String[] commandElements = commandLine.split(" ");
        command = commandElements[0];
        for (int i = 1; i < commandElements.length; i++) {
            this.args.add(commandElements[i]);
        }
    }

    public List<ContextSensitiveGrammar> getGrammars() {
        return grammars;
    }

    public List<String> getArgs() {
        return args;
    }

    public String getCommand() {
        return command;
    }
}
