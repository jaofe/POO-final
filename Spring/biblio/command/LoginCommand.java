package biblioteca.biblio.command;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class LoginCommand implements Command {
    private String username;
    private String senha;

    public LoginCommand(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.login(this.username, this.senha);

        if (usuario == null) {
            throw new InvalidCommandException("error");
        }

        if (usuario.isCliente()) {
            return "user";
        } else {
            usuario.baterPontoEntrada();
            return "admin";
        }

    }
}
