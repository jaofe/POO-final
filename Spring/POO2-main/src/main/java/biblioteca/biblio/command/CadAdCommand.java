package biblioteca.biblio.command;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class CadAdCommand implements Command<String>{
    Usuario usuario;

    public CadAdCommand(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public String execute() throws InvalidCommandException {
        Usuario user = biblioteca.buscarUsuario(usuario.getUsername());
        if (user == null) {
            biblioteca.criarAdmin(usuario.getUsername(), usuario.getSenha(), usuario.getContato());
            return "ok";
        } else {
            return "nome de usuario já está em uso";
        }
    }
    
}
