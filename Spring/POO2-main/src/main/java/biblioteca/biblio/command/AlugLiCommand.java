package biblioteca.biblio.command;

import java.time.LocalDate;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class AlugLiCommand implements Command<String> {
    
    private int id;
    private int tempo;
    Usuario user; 
  
    public AlugLiCommand(int ID, int Tempo, Usuario User)
    {
        this.id = ID;
        this.tempo = Tempo;
        user = User;
    }
    
    @Override
    public String execute() throws InvalidCommandException {

        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        Livro livro = biblioteca.buscarLivroId(id);
        
        if (!usuario.isCliente()) {
            throw new InvalidCommandException("error");
        }
        if (usuario.temLivroAtrasado()) {
            return "Devido a existencia de livros atrasados, esta função está indisponivel!";
        }
        if (livro.alugar(user.getUsername(), LocalDate.now().plusDays(tempo))) {
            usuario.alugarLivro(livro);
            return "ok";
        }
        return "Livro indisponivel!";

        
    }
}
