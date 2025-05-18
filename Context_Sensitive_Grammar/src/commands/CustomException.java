package commands;

public class CustomException extends Exception{
    /**
     * Конструктор за грешка дефинира със специализирано съобщение от програмиста
     * @param message
     */
    public CustomException(String message){
        super(message);
    }
}
