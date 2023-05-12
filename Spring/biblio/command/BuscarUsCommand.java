package biblioteca.biblio.command;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class BuscarUsCommand implements Command<Usuario>{

    private String id;

    public BuscarUsCommand(String ID)
    {
        this.id = ID;
    }

    @Override
    public Usuario execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(id);
        if (usuario == null) {
            throw new InvalidCommandException("Usuário não encontrado.");
        }
        return usuario;
    }
    
}
