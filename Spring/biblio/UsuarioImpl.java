package biblioteca.biblio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.biblio.command.Command;
import biblioteca.biblio.command.ListarUsCommand;
import biblioteca.biblio.command.LoginCommand;
import biblioteca.biblio.custonExceptions.InvalidCommandException;
import biblioteca.biblio.command.CadAdCommand;
import biblioteca.biblio.command.CadUsCommand;

@RestController
@RequestMapping("/usuario")
public class UsuarioImpl implements MainController<Usuario> {
    Command<ArrayList<String>> listUsersCommand = new ListarUsCommand();


    @Override
    public ResponseEntity<?> cadastro(Usuario usuario) {
        Command<String> cadCommand = new CadUsCommand(usuario);
        try {
            return ResponseEntity.ok(cadCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        try {
            return ResponseEntity.ok(listUsersCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {
        Usuario usuario = biblioteca.buscarUsuario(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<?> atrasados() {
        ArrayList<String> usuariosAtrasados = new ArrayList<>();
        for (Usuario usuario : biblioteca.usuarios) {
            if (usuario.temLivroAtrasado()) {
                usuariosAtrasados
                        .add("Usuario: " + usuario.getUsername() + ", forma de contato: " + usuario.getContato() + ".");
            }
        }
        return ResponseEntity.ok(usuariosAtrasados);
    }

    @GetMapping("livrosAlugados/{username}")
    public ResponseEntity<?> getLivrosAlugados(@PathVariable String username) {
        Usuario usuario = biblioteca.buscarUsuario(username);
        try {
            if (!usuario.isCliente()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(usuario.pegarLivrosAlugados());
        } catch (Exception e) {
            return ResponseEntity.ok(usuario.pegarLivrosAlugados());
        }

    }

    @GetMapping("livrosDevolvidos/{username}")
    public ResponseEntity<?> getLivrosDevolvidos(@PathVariable String username) {
        Usuario usuario = biblioteca.buscarUsuario(username);

        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.pegarLivrosDevolvidos());

    }

    @PostMapping("/login")
    public ResponseEntity<?> tentarLogar(@RequestBody Usuario user) {
        Command<String> loginCommand = new LoginCommand(user.getUsername(), user.getSenha());
        try {
            return ResponseEntity.ok(loginCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> sair(@RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());

        if (usuario != null && !usuario.isCliente()) {
            try {
                usuario.baterPontoSaida();
            } catch (NullPointerException e) {
                return ResponseEntity.ok("error ao computar horas");
            }
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping("cadastro-admin")
    public ResponseEntity<?> cadastroAdmin(@RequestBody Usuario usuario) {
        Command<String> cadCommand = new CadAdCommand(usuario);
        try {
            return ResponseEntity.ok(cadCommand.execute()); 
        } catch (InvalidCommandException e) {
            return ResponseEntity.notFound().build();
        }
        

    }

    @GetMapping("/multa/{username}")
    public ResponseEntity<?> multa(@PathVariable String username) {
        Usuario usuario = biblioteca.buscarUsuario(username);
        ArrayList<String> multas = new ArrayList<>();
        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.notFound().build();
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
        return ResponseEntity.ok(multas);
    }

    @PostMapping("/senha/{novaSenha}")
    public ResponseEntity<?> mudarSenha(@PathVariable String novaSenha, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.login(user.getUsername(), user.getSenha());

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.alterarSenha(usuario.getSenha(), novaSenha);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/contato/{novoContato}")
    public ResponseEntity<?> mudarContato(@PathVariable String novoContato, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.login(user.getUsername(), user.getSenha());

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.alterarContato(user.getSenha(), novoContato);

        return ResponseEntity.ok("ok");
    }
}
