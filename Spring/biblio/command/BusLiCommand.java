package biblioteca.biblio.command;

import biblioteca.biblio.Livro;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class BusLiCommand implements Command<Livro> {

    private String id;

    public BusLiCommand(String ID)
    {
        this.id = ID;
    }

    @Override
    public Livro execute() throws InvalidCommandException {
        int idint = 1;
        
        idint = Integer.parseInt(id);
        Livro livro = biblioteca.buscarLivroId(idint);
        if (livro == null) {
            throw new InvalidCommandException("error");
        }
        else
        {
            return livro;
        }
    }
}
