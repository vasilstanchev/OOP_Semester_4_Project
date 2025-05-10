import commands.Command;
import commands.CustomException;
import grammar.ContextSensitiveGrammar;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CustomException {
        List<ContextSensitiveGrammar> grammars = new ArrayList<>();
        Command.instructions(grammars);
    }
}