package commands;
import grammar.ContextSensitiveGrammar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandParameters {
    private List<ContextSensitiveGrammar> grammars;
    private List<String> args;
    private String command;
    private File file;

    public CommandParameters(){
        command="";
    }
    public CommandParameters(List<ContextSensitiveGrammar> grammars, String commandLine)throws CustomException {
        this.grammars = grammars;
        this.args=new ArrayList<>();
        if (commandLine == null || commandLine.isEmpty()){
            throw new CustomException("Command line is missing");
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

    public File getFile() {
        return file;
    }

    public void setFile(File fileName) {
        this.file = file;
    }
}
