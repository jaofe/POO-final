package biblioteca.biblio.command;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class MudarContCommand implements Command<String>{
    
    private String novoContato;
    Usuario user;
    
    public MudarContCommand(String contato, Usuario u)
    {
        this.novoContato = contato;
        this.user = u;
    }

    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.login(user.getUsername(), user.getSenha());

        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        usuario.alterarContato(user.getSenha(), novoContato);

        return "Contato alterado com sucesso.";
    }
}
