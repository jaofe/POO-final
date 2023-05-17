package biblioteca.biblio.command;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Review;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class FazerRevCommand implements Command<String> {

    private int id;
    Review review;

    public FazerRevCommand(int ID, Review rev)
    {
        this.id = ID;
        this.review = rev;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(review.getReviewerUsername());

        for (Livro l : usuario.pegarLivrosDevolvidos()) {
            if (l.getId() == id) {
                Livro livro = biblioteca.buscarLivroId(id);
                livro.reviews.add(review);
                return "ok";
            }
        }

        return "Não foi possível concluir o review!";
    }
    
}
