package commands;

public class CloseFile extends Command{
    /**
     * Пренаписан метод, който извиква метода за затваряне на файла
     * @param parameters
     * @throws CustomException
     */
    @Override
    public void execute(CommandParameters parameters) throws CustomException {
        if (parameters.getFile() == null){
            throw new CustomException("No file is open to be closed");
        }
        parameters.setFile(null);
        System.out.println("File closed");
    }
}
