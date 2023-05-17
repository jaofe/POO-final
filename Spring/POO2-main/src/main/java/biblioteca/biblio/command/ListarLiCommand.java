package biblioteca.biblio.command;

import java.util.ArrayList;

import biblioteca.biblio.Livro;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class ListarLiCommand implements Command<ArrayList<Livro>>{
    @Override
    public ArrayList<Livro> execute() throws InvalidCommandException {
        return biblioteca.livros;
    }
}
