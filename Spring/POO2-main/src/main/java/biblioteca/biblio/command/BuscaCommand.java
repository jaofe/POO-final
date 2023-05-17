package biblioteca.biblio.command;

import java.util.ArrayList;

import biblioteca.biblio.Livro;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class BuscaCommand implements Command<ArrayList<Livro>>{
    
    private String search;

    public BuscaCommand(String sear)
    {
        this.search = sear;
    }

    @Override
    public ArrayList<Livro> execute() throws InvalidCommandException {
        ArrayList<Livro> livros = new ArrayList<>();
        for (Livro livro : biblioteca.livros) {
            if (livro.getTitulo().contains(search)) {
                livros.add(livro);
            }
        }
        return livros;
    }
}
