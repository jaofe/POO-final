package biblioteca.biblio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class Main {
    static Biblioteca biblioteca = new Biblioteca();;
    static Scanner input;
    static Cliente cliente;
    static Admin admin;

    @PostConstruct
    public void initBiblioteca() {
        biblioteca.admins.add(new Admin("admin", "admin", "admin@admin.com"));
        biblioteca.clientes.add(new Cliente("user", "user", "user@user.com"));
    }

    /*
     * private static void alterarSenha(Usuario usuario) {
     * System.out.print("Digite sua senha: ");
     * 
     * String senhaAtual = input.nextLine();
     * 
     * System.out.print("Digite a nova senha: ");
     * String senhaNova = input.nextLine();
     * 
     * 
     * boolean conseguiuAlterar = usuario.alterarSenha(senhaAtual, senhaNova);
     * 
     * if (!conseguiuAlterar) {
     * System.out.println("Senha incorreta!");
     * }
     * }
     * 
     * private static void alterarContato(Usuario usuario) {
     * System.out.print("Digite sua senha: ");
     * 
     * String senha = input.nextLine();
     * 
     * System.out.print("Digite o seu novo contato: ");
     * String contato = input.nextLine();
     * 
     * 
     * boolean conseguiuAlterar = usuario.alterarContato(senha, contato);
     * 
     * if (!conseguiuAlterar) {
     * System.out.println("Senha incorreta!");
     * }
     * }
     * 
     * private static void cadastrarAdmin() {
     * System.out.print("Digite o Username: ");
     * String username = input.nextLine();
     * 
     * if (biblioteca.buscarUsuario(username) != null) {
     * System.out.println("Usuario já existente!");
     * return;
     * }
     * System.out.print("Digite forma de contato(email/telefone): ");
     * String contato = input.nextLine();
     * 
     * System.out.print("Digite sua senha: ");
     * String senha = input.nextLine();
     * 
     * biblioteca.criarAdmin(username, senha, contato);
     * }
     */

    @PostMapping("api/endpoint")
    public ResponseEntity<?> handlePostRequest(@RequestBody String data) {
        System.out.println("dados recebidos:" + data);
        return ResponseEntity.ok("Request received successfully");
    }

    @PostMapping("api/login")
    private static ResponseEntity<?> tentarLogar(@RequestBody LoginBody loginData) {
        admin = biblioteca.buscarAdmin(loginData.getUsername());

        if (admin != null && admin.isSenhaCorreta(loginData.getSenha())) {
            cliente = null;
            System.out.println("admin");
            return ResponseEntity.ok("admin");
        }

        cliente = biblioteca.buscarCliente(loginData.getUsername());

        if (cliente != null && cliente.isSenhaCorreta(loginData.getSenha())) {
            admin = null;
            System.out.println("user");
            return ResponseEntity.ok("user");
        }

        System.out.println("Usuario ou senha incorretas");
        return ResponseEntity.ok("nenhum");
    }

    /*
     * private static void devolverLivro() {
     * System.out.print("Digite o titulo do livro: ");
     * String titulo = input.nextLine();
     * 
     * Livro livro = biblioteca.buscarLivro(titulo);
     * 
     * if (livro == null) {
     * System.out.println("Livro nao encontrado!");
     * return;
     * }
     * 
     * if (!cliente.estaComLivroAlugado(livro)) {
     * System.out.println("Você não alugou este livro!");
     * return;
     * }
     * 
     * if (livro.dataDevolucao != null) {
     * if(livro.isAtrasado()) {
     * LocalDate hoje = LocalDate.now();
     * long atraso = ChronoUnit.DAYS.between(livro.dataDevolucao, hoje);
     * System.out.println("Digite (ok) para comfirmar o pagamento da multa de R$:" +
     * (5 + atraso*.75) + "\n(R$ 5.00 + R$ 0.75 por dia de atraso.)");
     * String ok = input.nextLine();
     * 
     * if(!ok.equals("ok")) return;
     * }
     * }
     * 
     * cliente.devolverLivro(livro,biblioteca);
     * System.out.println("Livro devolvido com sucesso!");
     * 
     * }
     * 
     * private static void olharMulta()
     * {
     * int atrasado = 0;
     * 
     * if (cliente.livrosAlugados.size() > 0) {
     * 
     * for (Livro livro: cliente.livrosAlugados) {
     * if (livro.isAtrasado()) {
     * LocalDate hoje = LocalDate.now();
     * long atraso = ChronoUnit.DAYS.between(livro.dataDevolucao, hoje);
     * 
     * System.out.print("Titulo:");
     * System.out.print(" "+ livro.getTitulo());
     * System.out.print(" multa de R$: " + (5 + atraso*.75) + " / ");
     * atrasado++;
     * }
     * }
     * }
     * if(atrasado == 0)
     * {
     * System.out.println("Sem multas pendentes!");
     * }
     * else
     * {
     * System.out.println();
     * }
     * 
     * }
     * 
     * private static void alugarLivro() {
     * 
     * for(Livro l: cliente.livrosAlugados) {
     * if(l.isAtrasado()) {
     * System.out.
     * println("Devido a existencia de livros atrasados, esta função está indisponivel!"
     * );
     * return;
     * }
     * }
     * 
     * 
     * 
     * System.out.print("Digite o titulo do livro: ");
     * String titulo = input.nextLine();
     * 
     * Livro livro = biblioteca.buscarLivro(titulo);
     * 
     * if (livro == null) {
     * System.out.println("Livro nao encontrado!");
     * return;
     * }
     * 
     * 
     * System.out.print("Digite a data para devolução do livro (YYYY-MM-DD):");
     * String devolucao = input.nextLine();
     * 
     * 
     * DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;
     * 
     * boolean formatoCorreto = false;
     * while (!formatoCorreto) {
     * try {
     * livro.dataDevolucao = LocalDate.parse(devolucao, formato);
     * formatoCorreto = true;
     * } catch (Exception e) {
     * System.out.
     * println("A Input não apresenta o formato correto, digite novamente a data no formato (YYYY-MM-DD)"
     * );
     * devolucao = input.nextLine();
     * }
     * }
     * 
     * if (livro.isDisponivel()) {
     * cliente.alugarLivro(livro);
     * System.out.println("Livro alugado com sucesso!");
     * } else {
     * System.out.println("Livro indisponivel!");
     * }
     * }
     * 
     * private static void cadastrarUsuario() {
     * System.out.print("Digite o Username: ");
     * String username = input.nextLine();
     * 
     * if (biblioteca.buscarUsuario(username) != null) {
     * System.out.println("Usuario já existente!");
     * return;
     * }
     * 
     * System.out.print("Digite forma de contato(email/telefone): ");
     * String contato = input.nextLine();
     * 
     * System.out.print("Digite sua senha: ");
     * String senha = input.nextLine();
     * 
     * biblioteca.criarUsuario(username, senha, contato);
     * System.out.println("Usuario cadastrado com sucesso!");
     * }
     * 
     * private static void cadastrarLivro() {
     * System.out.println("Nome do Livro:");
     * String titulo = input.nextLine();
     * 
     * System.out.println("Autor do Livro:");
     * String autor = input.nextLine();
     * 
     * System.out.println("Editora do Livro:");
     * String editora = input.nextLine();
     * 
     * System.out.println("Ano de lançamento do Livro:");
     * String anoLancamento = input.nextLine();
     * 
     * Livro livro = new Livro(titulo, autor, editora, anoLancamento);
     * 
     * biblioteca.cadastrarLivro(livro);
     * }
     * 
     * private static void buscarLivro()
     * {
     * System.out.print("Digite o titulo do livro: ");
     * String titulo = input.nextLine();
     * 
     * Livro livro = biblioteca.buscarLivro(titulo);
     * 
     * if(livro == null)
     * System.out.println("Livro nao encontrado!");
     * 
     * else {
     * livro.printLivro();
     * if (livro.reviews.isEmpty()) {
     * System.out.println("Nenhuma review.");
     * } else {
     * System.out.println("Reviews:");
     * for(Review R: livro.reviews) {
     * R.printReview();
     * }
     * }
     * }
     * }
     * 
     * private static void reservar()
     * {
     * for(Livro l: cliente.livrosAlugados) {
     * if(l.isAtrasado()) {
     * System.out.
     * println("Devido a existencia de livros atrasados, esta função está indisponivel!"
     * );
     * return;
     * }
     * }
     * 
     * System.out.print("Digite o titulo do livro: ");
     * String titulo = input.nextLine();
     * 
     * Livro livro = biblioteca.buscarLivro(titulo);
     * 
     * if (livro == null) {
     * System.out.println("Livro nao encontrado!");
     * return;
     * }
     * 
     * cliente.reservar(livro);
     * }
     * 
     * // private static void escreverReview() {
     * // if (cliente.livrosDevolvidos.isEmpty()) {
     * // System.out.
     * println("Nenhum livro valido (devolva um livro para poder escrever uma review)."
     * );
     * // } else {
     * // int count = 1;
     * // for (Livro l: cliente.livrosDevolvidos) {
     * // System.out.println(count++ + " - " + l.titulo);
     * // }
     * //
     * System.out.println("Digite o número do livro que quer escrever a review: ");
     * // int numl = input.nextInt();
     * 
     * // Livro selecionado = null;
     * // boolean formatoCorreto = false;
     * // while (!formatoCorreto) {
     * // try {
     * // selecionado = cliente.livrosDevolvidos.get(numl - 1);
     * // formatoCorreto = true;
     * // } catch (Exception e) {
     * // System.out.println("A Input não apresenta o formato correto");
     * // numl = input.nextInt();
     * // }
     * // }
     * 
     * // System.out.println("Digite sua review:");
     * // String rev = input.nextLine();
     * // rev = input.nextLine();
     * // selecionado.reviews.add(new Review(rev, cliente.username));
     * // }
     * // }
     * 
     * private static void removerReview() {
     * System.out.println("Digite o username do usuario:");
     * String username = input.nextLine();
     * if(biblioteca.buscarCliente(username) == null) {
     * System.out.println("Usuario não encotrado");
     * return;
     * }
     * System.out.print("Digite o titulo do livro: ");
     * String titulo = input.nextLine();
     * 
     * Livro livro = biblioteca.buscarLivro(titulo);
     * if(livro == null) {
     * System.out.println("Livro não encotrado");
     * return;
     * }
     * 
     * int index = -1;
     * for(Review r: livro.reviews) {
     * if (r.reviewerUsername.equals(username)) {
     * index = livro.reviews.indexOf(r);
     * }
     * }
     * if (index == -1) {
     * System.out.println("Usuario não possui review desse livro.");
     * } else {
     * livro.reviews.remove(index);
     * System.out.println("Review removida com sucesso.");
     * }
     * }
     */
}