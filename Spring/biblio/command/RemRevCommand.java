package biblioteca.biblio.command;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Review;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class RemRevCommand implements Command<String>{

    private int id;
    Usuario user;

    public RemRevCommand(int ID, Usuario u)
    {
        this.id = ID;
        this.user = u;
    }

    @Override
    public String execute() throws InvalidCommandException {
       Livro livro = biblioteca.buscarLivroId(id);
    
        int index = -1;
        for (Review r : livro.reviews) {
            if (r.getReviewerUsername().equals(user.getUsername())) {
                index = livro.reviews.indexOf(r);
            }
        }
        if (index == -1) {
            return "Review não encontrada.";
        }

            livro.reviews.remove(index);
            System.out.println("Review removida com sucesso.");
            return "Review removida com sucesso.";
        
    }
    
}
