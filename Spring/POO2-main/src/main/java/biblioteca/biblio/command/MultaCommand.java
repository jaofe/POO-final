package biblioteca.biblio.command;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import biblioteca.biblio.Livro;
import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class MultaCommand implements Command<ArrayList<String>>{
    
    private String username;

    public MultaCommand(String name)
    {
        this.username = name;
    }

    @Override
    public ArrayList<String> execute() throws InvalidCommandException {
        Usuario usuario = biblioteca.buscarUsuario(username);
        ArrayList<String> multas = new ArrayList<>();
        if (usuario == null || !usuario.isCliente()) {
            throw new InvalidCommandException("Usuário não encontrado.");
        }

        ArrayList<Livro> livrosAlugados = usuario.pegarLivrosAlugados();
        for (Livro livro : livrosAlugados) {
            if (livro.isAtrasado()) {
                LocalDate hoje = LocalDate.now();
                long atraso = ChronoUnit.DAYS.between(livro.getDataDevolucao(), hoje);

                multas.add("Livro: " + livro.getTitulo() + ",id: " + livro.getId() + ", data devolução: "
                        + livro.getDataDevolucao() + ", multa de R$: " + (5 + atraso * .75) + " .");
            }
        }
        return multas;
    }
}
