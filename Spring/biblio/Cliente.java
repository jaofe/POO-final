package biblioteca.biblio;

import java.util.ArrayList;

public class Cliente extends Usuario {

    public ArrayList<Livro> livrosAlugados = new ArrayList<>();
    public ArrayList<Livro> livrosReservados = new ArrayList<>();
    public ArrayList<Livro> livrosDevolvidos = new ArrayList<>();

    public Cliente(String username, String senha, String contato) {
        super(username, senha, contato);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Cliente other = (Cliente) obj;
        return this.getUsername().equals(other.getUsername());
    }

    @Override
    public void alugarLivro(Livro livro) {
        this.livrosAlugados.add(livro);
    }

    @Override
    public void devolverLivro(Livro livro) {
        System.out.println("1");
        if (this.livrosAlugados.contains(livro)) {
            System.out.println("2");
            this.livrosAlugados.remove(livro);
        }
        if(!this.livrosDevolvidos.contains(livro)){
            this.livrosDevolvidos.add(livro);
        }
        return;
    }

    public String listarLivrosAlugados() {
        StringBuilder buffer = new StringBuilder(" ");

        if (livrosAlugados.isEmpty()) {
            buffer.append("Nenhum livro alugado");
        } else {
            buffer.append("livros alugados:");
            for (Livro livro : livrosAlugados) {
                buffer.append("Titulo: ").append(livro.getTitulo());
                buffer.append(", ");
                buffer.append(livro.isAtrasado() ? "Livro Atrasado" : "Sem Atraso");
                buffer.append(". | ");
            }
        }

        buffer.append(" (CLIENTE)");

        return buffer.toString();
    }

    @Override
    public void reservarLivro(Livro livro) {
        livrosReservados.add(livro);
        
    }

    @Override
    public void confirmarReserva(Livro livro) {
        if (livrosReservados.contains(livro)) {
            livrosReservados.remove(livro);
            livrosAlugados.add(livro);
        }
    }

    @Override
    public String printUsuario() {
        return ("Usuario: " + getUsername() +"Contato: " + getContato() + listarLivrosAlugados());
    }

    public boolean estaComLivroAlugado(Livro livro) {
        return livrosAlugados.contains(livro);
    }

    @Override
    public boolean isCliente() {
        return true;
    }

    @Override
    public void ListarLivros(Usuario usuarios, int[] count) {

        if (!livrosAlugados.isEmpty()) {
            System.out.println(listarLivrosAlugados());
            count[0]++;
        }

    }

    @Override
    public void listarLivrosAtrasados(Usuario usuario, Livro livro) {
        if (estaComLivroAlugado(livro)) {
            System.out.print("O livro está com o seguinte usuario: ");
            System.out.println(this.getUsername() + " Forma de contato: " + this.getContato());
        }
    }

    @Override
    public boolean temLivroAtrasado() {
        for (Livro l : livrosAlugados) {
            if (l.isAtrasado()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Livro> pegarLivrosAlugados() {
        return livrosAlugados;
    }

    @Override
    public ArrayList<Livro> pegarLivrosDevolvidos() {
        return livrosDevolvidos;
    }
}
