package biblioteca.biblio.state;

import java.time.LocalDate;

import biblioteca.biblio.custonExceptions.InvalidStateException;

public class DisponivelState implements LivroState {
    @Override
    public String getUsernameA() throws InvalidStateException {
        throw new InvalidStateException("O livro não está alugado");
    }

    @Override
    public String getUsernameR() throws InvalidStateException{
        throw new InvalidStateException("O livro não está reservado");

    }

    @Override
    public boolean isDisponivel() {
        return true;
    }

    @Override
    public boolean isReservavel() {
        return false;
    }

    @Override
    public LocalDate getDataDevolucao() throws InvalidStateException {
        throw new InvalidStateException("O livro não está alugado");
    }

    @Override
    public LivroState alugar(String username, LocalDate dataDevolucao) throws InvalidStateException {
        return new AlugadoState(username, dataDevolucao);
    }

    @Override
    public LivroState devolver() throws InvalidStateException {
        throw new InvalidStateException("O livro não está alugado");
    }

    @Override
    public LivroState reservar(String usernameR) throws InvalidStateException {
        throw new InvalidStateException("O livro está disponivel");
    }
}
