package biblioteca.biblio.command;

import java.util.List;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class GetLivDevCommand implements Command<List<Livro>>{

    private String username;

    public GetLivDevCommand(String name)
    {
        this.username = name;
    }

    @Override
    public List<Livro> execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(username);

        if (usuario == null || !usuario.isCliente()) {
            throw new InvalidCommandException("Usuário não encontrado.");
        }

        return usuario.pegarLivrosDevolvidos();
    }
    
}
