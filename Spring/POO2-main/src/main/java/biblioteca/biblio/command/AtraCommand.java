package biblioteca.biblio.command;

import java.util.ArrayList;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class AtraCommand implements Command<ArrayList<String>> {

    @Override
    public ArrayList<String> execute() throws InvalidCommandException {

        ArrayList<String> livrosAtrasados = new ArrayList<>();
        for (Livro livro : biblioteca.livros) {
            if (livro.getUsername() != null && livro.isAtrasado()) {
                Usuario usuario = biblioteca.buscarUsuario(livro.getUsername());
                livrosAtrasados.add("Livro: " + livro.getTitulo() + ",id: " + livro.getId() + ", data devolução: "
                        + livro.getDataDevolucao() + ", usuario: " + usuario.getUsername() + ", forma de contato: "
                        + usuario.getContato() + ".");
            }
        }
        return livrosAtrasados;
    }
}
