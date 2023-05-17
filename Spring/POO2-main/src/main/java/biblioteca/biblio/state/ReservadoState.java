package biblioteca.biblio.state;

import java.time.LocalDate;

import biblioteca.biblio.custonExceptions.InvalidStateException;

public class ReservadoState implements LivroState {

    private String usernameA;
    private String usernameR;
    private LocalDate dataDevolucao;

    public ReservadoState (String usernameA, String usernameR, LocalDate dataDevolucao) {
        this.usernameA = usernameA;
        this.usernameR = usernameR;
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String getUsernameA() throws InvalidStateException {
        return usernameA;
    }
    @Override
    public String getUsernameR() throws InvalidStateException {
        return usernameR;
    }
    @Override
    public LocalDate getDataDevolucao() throws InvalidStateException {
        return dataDevolucao;
    }

    @Override
    public boolean isDisponivel() {
        return false;
    }

    @Override
    public boolean isReservavel() {
        return false;
    }

    @Override
    public LivroState alugar(String username, LocalDate dataDevolucao) throws InvalidStateException {
        throw new InvalidStateException("Este livro já está alugado");
    }

    @Override
    public LivroState devolver() throws InvalidStateException {
        return new AlugadoState(usernameR, LocalDate.now().plusDays(14));
    }

    @Override
    public LivroState reservar(String usernameR) throws InvalidStateException {
        throw new InvalidStateException("O livro já foi reservado");
    }
}
