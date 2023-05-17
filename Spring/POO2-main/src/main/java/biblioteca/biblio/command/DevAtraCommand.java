package biblioteca.biblio.command;

import java.util.ArrayList;
import java.util.List;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class DevAtraCommand implements Command<String> {
    
    Usuario user;

    public DevAtraCommand(Usuario u)
    {
        this.user = u;
    }
    
    @Override
    public String execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        
        List<Livro> livrosAlugados = new ArrayList<>(usuario.pegarLivrosAlugados());
        for (Livro livro : livrosAlugados) {
            if (livro.isAtrasado()) {
                if(livro.devolver()) {
                    usuario.devolverLivro(livro);
                    Usuario usuario2 = biblioteca.buscarUsuario(livro.getUsername());
                    if (usuario2 != null) {
                        usuario2.alugarLivro(livro);
                    }
                }
            }
        }

        return "ok";
    }
}