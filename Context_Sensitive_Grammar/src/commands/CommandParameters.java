package commands;
import grammar.ContextSensitiveGrammar;
import java.util.List;

public class CommandParameters {
        private List<ContextSensitiveGrammar> grammars;
        private List<String> args;
        public CommandParameters(List<ContextSensitiveGrammar> grammars, List<String> args) {
            this.grammars = grammars;
            this.args = args;
        }

        public List<ContextSensitiveGrammar> getGrammars() {
            return grammars;
        }
        public List<String> getArgs() {
            return args;
        }
}
