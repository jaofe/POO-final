package biblioteca.biblio.command;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;
import biblioteca.biblio.custonExceptions.InvalidStateException;

public class FazerResCommand implements Command<String>{
    
    private int id;
    Usuario user;

    public FazerResCommand(int ID, Usuario u)
    {
        this.id = ID;
        this.user = u;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());

        if (usuario.temLivroAtrasado()) {
            return "Devido a existencia de livros atrasados, esta função está indisponivel!";
        }
        Livro livro = biblioteca.buscarLivroId(id);
        try {
            if (livro.reservar(usuario.getUsername())) {
                usuario.reservarLivro(livro);
                return "ok";
            }
        } catch (InvalidStateException e) {
            return "Reserva não conseguiu ser efetuada!";
        }
        return "Error ao reservar livro";

    }
}