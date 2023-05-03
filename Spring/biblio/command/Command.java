package biblioteca.biblio.command;
import biblioteca.biblio.Biblioteca;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public interface Command {
    Biblioteca biblioteca = new Biblioteca();

    public String execute() throws InvalidCommandException;
}
