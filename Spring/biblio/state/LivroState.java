package biblioteca.biblio.state;

import java.time.LocalDate;

import biblioteca.biblio.custonExceptions.InvalidStateException;

public interface LivroState {
    public String getUsernameA() throws InvalidStateException;
    public String getUsernameR() throws InvalidStateException;
    public boolean isDisponivel();
    public boolean isReservavel();
    public LocalDate getDataDevolucao() throws InvalidStateException;
    public LivroState alugar(String username, LocalDate dataDevolucao) throws InvalidStateException;
    public LivroState devolver() throws InvalidStateException;
    public LivroState reservar(String usernameR) throws InvalidStateException;
}