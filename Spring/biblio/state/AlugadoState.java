package biblioteca.biblio.state;

import java.time.LocalDate;

import biblioteca.biblio.custonExceptions.InvalidStateException;

public class AlugadoState implements LivroState {

    private String usernameA;
    private LocalDate dataDevolução;

    public AlugadoState(String usernameA, LocalDate dataDevolucao) {
        this.usernameA = usernameA;
        this.dataDevolução = dataDevolucao;
    }

    @Override
    public String getUsernameA() throws InvalidStateException {
        return this.usernameA;
    }

    @Override
    public String getUsernameR() throws InvalidStateException {
        throw new InvalidStateException("O livro não está reservado");
    }

    @Override
    public boolean isDisponivel() {
        return false;
    }

    @Override
    public boolean isReservavel() {
        return true;
    }

    @Override
    public LocalDate getDataDevolucao() throws InvalidStateException {
        return this.dataDevolução;
    }

    @Override
    public LivroState alugar(String username, LocalDate dataDevolucao) throws InvalidStateException {
        throw new InvalidStateException("Este livro já está alugado");
    }

    @Override
    public LivroState devolver() throws InvalidStateException {
        return new DisponivelState();
    }

    @Override
    public LivroState reservar(String usernameR) throws InvalidStateException {
        return new ReservadoState(usernameA, usernameR, dataDevolução);
    }
}
