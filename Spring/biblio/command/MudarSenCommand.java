package biblioteca.biblio.command;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class MudarSenCommand implements Command<String>{
    
    private String novaSenha;
    Usuario user;

    public MudarSenCommand(String sen, Usuario u)
    {
        this.novaSenha = sen;
        this.user = u;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.login(user.getUsername(), user.getSenha());

        if (usuario == null) {
            return "Usuário não encontrado;";
        }

        usuario.alterarSenha(usuario.getSenha(), novaSenha);

        return "Senha alterada com sucesso.";
    }
}
