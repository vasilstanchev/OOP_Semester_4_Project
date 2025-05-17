package commands;

public class CloseFile extends Command{
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        if (parameters.getFile() == null){
            throw new CustomException("No file is open to be closed");
        }
        parameters.setFile(null);
        System.out.println("File closed");
    }
}
