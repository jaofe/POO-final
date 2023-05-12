package biblioteca.biblio.command;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class LogoutCommand implements Command<String>{
    
    Usuario user;

    public LogoutCommand(Usuario u)
    {
        this.user = u;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());

        if (usuario != null && !usuario.isCliente()) {

            usuario.baterPontoSaida();

            return "Bater ponto feito com sucesso.";
        }
        
        return "error ao computar horas";
    }
    
}
