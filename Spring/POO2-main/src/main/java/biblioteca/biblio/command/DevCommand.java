package biblioteca.biblio.command;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class DevCommand implements Command<String> {

    private int id;
    Usuario user;

    public DevCommand(Usuario u, int ID)
    {
        this.user = u;
        this.id = ID;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        Livro livro = biblioteca.buscarLivroId(id);
        if (!usuario.pegarLivrosAlugados().contains(livro)){
            return "o usuario não esta com este livro";
        }

        
        if (livro.isAtrasado()) {
            return "Livro atrasado, por favor vá para a pagina de multas.";
        }

        if(livro.devolver()) {
            usuario.devolverLivro(livro);
            Usuario usuario2 = biblioteca.buscarUsuario(livro.getUsername());
            if (usuario2 != null) {
                usuario2.alugarLivro(livro);
            }
        }

        return "ok";
    }
    
}
