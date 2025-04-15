import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ContextSensitiveGrammar> grammars = new ArrayList<>();
        Command.instructions(grammars);
    }
}