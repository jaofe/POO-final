package biblioteca.biblio;

import java.time.*;
import java.util.ArrayList;

import biblioteca.biblio.custonExceptions.InvalidStateException;
import biblioteca.biblio.state.DisponivelState;
import biblioteca.biblio.state.LivroState;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private String ano;
    private String capaUrl;
    private String sinopse;
    private LivroState state;

    public ArrayList<Review> reviews = new ArrayList<>();

    private static int idcount = 1;

    public Livro(String titulo, String autor, String capaUrl, String editora, String ano, String sinopse) {
        this.id = idcount++;
        this.titulo = titulo;
        this.autor = autor;
        this.capaUrl = capaUrl;
        this.editora = editora;
        this.ano = ano;
        this.sinopse = sinopse;
        this.state = new DisponivelState();
    }

    public int getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setCapa(String capaUrl) {
        this.capaUrl = capaUrl;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEditora() {
        return editora;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAno() {
        return ano;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getSinopse() {
        return sinopse;
    }

    public boolean getDisponibilidade() {
        return this.state.isDisponivel();
    }

    public boolean getReservabilidade(){
        return this.state.isReservavel();
    }

    public LocalDate getDataDevolucao() {
        try {
            return this.state.getDataDevolucao();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsername() {
        try {
            return state.getUsernameA();
        } catch (Exception e) {
            return null;
        }
    }

    public String designado() throws InvalidStateException{
        try {
            return state.getUsernameR();
        } catch (InvalidStateException e) {
            throw e;
        }
    }

    public boolean isAtrasado() {
        try {
            LocalDate dataDevolucao = this.state.getDataDevolucao();
            return (dataDevolucao.isBefore(LocalDate.now()));
        } catch (Exception e) {
            return false;
        }
    }

    public String getCapaUrl() {
        return capaUrl;
    }

    public Boolean alugar(String username, LocalDate dataDevolucao) {
        try {
            this.state = this.state.alugar(username, dataDevolucao);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }


    public boolean devolver() {
        try {
            this.state = this.state.devolver();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public Boolean reservar(String username) throws InvalidStateException {
        try {
            this.state = this.state.reservar(username);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }
}
