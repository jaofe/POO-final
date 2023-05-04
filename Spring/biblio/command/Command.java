package biblioteca.biblio.command;
import biblioteca.biblio.Biblioteca;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public interface Command<T> {
    Biblioteca biblioteca = new Biblioteca();

    public T execute() throws InvalidCommandException;
}
