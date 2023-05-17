package biblioteca.biblio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.biblio.command.GetLivAlugCommand;
import biblioteca.biblio.command.GetLivDevCommand;
import biblioteca.biblio.command.ListarUsCommand;
import biblioteca.biblio.command.LoginCommand;
import biblioteca.biblio.command.LogoutCommand;
import biblioteca.biblio.command.MudarContCommand;
import biblioteca.biblio.command.MudarSenCommand;
import biblioteca.biblio.command.MultaCommand;
import biblioteca.biblio.command.UsAtraCommand;
import biblioteca.biblio.command.BuscarUsCommand;
import biblioteca.biblio.command.CadAdCommand;
import biblioteca.biblio.command.CadUsCommand;

@RestController
@RequestMapping("/usuario")
public class UsuarioImpl implements MainController<Usuario> {
    @Override
    public ResponseEntity<?> cadastro(Usuario usuario) {
        return executeHandler(new CadUsCommand(usuario));
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        return executeHandler(new ListarUsCommand());
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {
        return executeHandler(new BuscarUsCommand(id));
    }

    @Override
    public ResponseEntity<?> atrasados() {
        return executeHandler(new UsAtraCommand());
    }

    @GetMapping("livrosAlugados/{username}")
    public ResponseEntity<?> getLivrosAlugados(@PathVariable String username) {
        return executeHandler(new GetLivAlugCommand(username));
    }

    @GetMapping("livrosDevolvidos/{username}")
    public ResponseEntity<?> getLivrosDevolvidos(@PathVariable String username) {
        return executeHandler(new GetLivDevCommand(username));
    }

    @PostMapping("/login")
    public ResponseEntity<?> tentarLogar(@RequestBody Usuario user) {
        return executeHandler(new LoginCommand(user.getUsername(), user.getSenha()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> sair(@RequestBody Usuario user) {
        return executeHandler(new LogoutCommand(user));
    }

    @PostMapping("cadastro-admin")
    public ResponseEntity<?> cadastroAdmin(@RequestBody Usuario usuario) {
        return executeHandler(new CadAdCommand(usuario));
    }

    @GetMapping("/multa/{username}")
    public ResponseEntity<?> multa(@PathVariable String username) {
        return executeHandler(new MultaCommand(username));
    }

    @PostMapping("/senha/{novaSenha}")
    public ResponseEntity<?> mudarSenha(@PathVariable String novaSenha, @RequestBody Usuario user) {
        return executeHandler(new MudarSenCommand(novaSenha, user));
    }

    @PostMapping("/contato/{novoContato}")
    public ResponseEntity<?> mudarContato(@PathVariable String novoContato, @RequestBody Usuario user) {
        return executeHandler(new MudarContCommand(novoContato, user));
    }
}
