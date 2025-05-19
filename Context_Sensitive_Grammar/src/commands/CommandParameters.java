package commands;
import grammar.ContextSensitiveGrammar;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandParameters {
    private List<ContextSensitiveGrammar> grammars;
    private List<String> args;
    private String command;
    private File file;

    /**
     * Конструктор без параметри, който инициализира празна команда
     */
    public CommandParameters(){
        command="";
    }

    /**
     * Конструктор за подадените параметри, който зарежда всяка променлива в дадения атрибут
     * @param grammars
     * @param commandLine
     * @throws CustomException
     */
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

    /**
     * Метод, който връща списък от граматики
     * @return
     */
    public List<ContextSensitiveGrammar> getGrammars() {
        return grammars;
    }
    /**
     * Метод, който връща списъка от аргументите към дадена команда
     * @return
     */
    public List<String> getArgs() {
        return args;
    }
    /**
     * Метод, който връща името на дадена команда
     * @return
     */
    public String getCommand() {
        return command;
    }
    /**
     * Метод, който връща обектът към даден файл
     * @return
     */
    public File getFile() {
        return file;
    }
    /**
     * Метод, който обновява обекта към даден файл
     * @param fileName
     */
    public void setFile(File fileName) {
        this.file = file;
    }
}
