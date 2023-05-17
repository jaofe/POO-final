package biblioteca.biblio.command;

import biblioteca.biblio.Livro;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class CadLiCommand implements Command<String> {
    
    Livro livro;

    public CadLiCommand(Livro liv)
    {
        this.livro = liv;
    }

    @Override
    public String execute() throws InvalidCommandException {
        biblioteca.cadastrarLivro(livro);
        return "ok";
    }
}
