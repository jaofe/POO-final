package biblioteca.biblio;

import java.time.*;
import java.util.ArrayList;

public class Livro{

    public String titulo;
    public String autor;
    public String editora;
    public String ano;
    public boolean disponibilidade;
    public boolean reservado;
    public LocalDate dataDevolucao;
    public ArrayList <Review> reviews = new ArrayList<>();

    public Livro (String titulo, String autor, String editora, String ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
        
        disponibilidade = true;
        dataDevolucao = null;
        reservado = true;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    String getTitulo() {
        return titulo;
    }

    void setAutor(String autor) {
        this.autor = autor;
    } 

    String pegarAutor () {
        return autor;
    }

    void setEditora(String editora) {
        this.editora = editora;
    }

    String pegarEditora () {
        return editora;
    }

    void setAno(String ano) {
        this.ano = ano;
    }

    String pegarAno () {
        return ano;
    }

    void mudarDisponibilidade() {
        this.disponibilidade = !this.disponibilidade;
    }

    boolean isDisponivel() {
        return disponibilidade;
    }

    void mudarReserva() {
        this.reservado = !this.reservado;
    }

    boolean isReservado() {
        return reservado;
    }

    public void printLivro() {
        System.out.print("Titulo: " + titulo);
        System.out.print(", Autor: " + autor);
        System.out.print(", Editora: " + editora);
        System.out.print(", Ano de lançamento: " + ano);
    
        if (disponibilidade && reservado) {
            System.out.println(", Livro disponivel");
        }
        else if (!disponibilidade && reservado){
            System.out.print(", Livro indisponivel com possibilidade de reserva");
            System.out.print(", Devoluçao esperada no dia: " + dataDevolucao);

            if (isAtrasado()) {
                System.out.println(", Livro Atrasado.");
            } else System.out.println(" ");

        }
        else if (!disponibilidade)
        {
            System.out.print(", Livro indisponivel sem possibilidade de reserva");
            System.out.print(", Devoluçao esperada no dia: " + dataDevolucao);   
            
            if (isAtrasado()) {
                System.out.println(", Livro Atrasado.");
            } else System.out.println(" ");
        }

    }

    boolean isAtrasado() {
        LocalDate hoje = LocalDate.now();
        
        return (dataDevolucao.isBefore(hoje));
    }
}
