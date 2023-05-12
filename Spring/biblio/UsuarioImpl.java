package biblioteca.biblio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.biblio.command.Command;
import biblioteca.biblio.command.GetLivAlugCommand;
import biblioteca.biblio.command.GetLivDevCommand;
import biblioteca.biblio.command.ListarUsCommand;
import biblioteca.biblio.command.LoginCommand;
import biblioteca.biblio.command.LogoutCommand;
import biblioteca.biblio.command.MudarContCommand;
import biblioteca.biblio.command.MudarSenCommand;
import biblioteca.biblio.command.MultaCommand;
import biblioteca.biblio.command.UsAtraCommand;
import biblioteca.biblio.custonExceptions.InvalidCommandException;
import biblioteca.biblio.command.BuscarUsCommand;
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
        Command<Usuario> BuscarUs = new BuscarUsCommand(id);
        try {
            return ResponseEntity.ok(BuscarUs.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> atrasados() {
        Command<ArrayList<String>> UsAtrasados = new UsAtraCommand();
        try {
            return ResponseEntity.ok(UsAtrasados.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("livrosAlugados/{username}")
    public ResponseEntity<?> getLivrosAlugados(@PathVariable String username) {
        Command<List<Livro>> getLivroAlg = new GetLivAlugCommand(username);
        try {
            return ResponseEntity.ok(getLivroAlg.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("livrosDevolvidos/{username}")
    public ResponseEntity<?> getLivrosDevolvidos(@PathVariable String username) {
        Command<List<Livro>> getLivroDev = new GetLivDevCommand(username);
        try {
            return ResponseEntity.ok(getLivroDev.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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
        Command<String> logoutCommand = new LogoutCommand(user);
        try {
            return ResponseEntity.ok(logoutCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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
        Command<ArrayList<String>> multaCommand = new MultaCommand(username);
        try {
            return ResponseEntity.ok(multaCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/senha/{novaSenha}")
    public ResponseEntity<?> mudarSenha(@PathVariable String novaSenha, @RequestBody Usuario user) {
        Command<String> mudarsenhCommand = new MudarSenCommand(novaSenha, user);
        try {
            return ResponseEntity.ok(mudarsenhCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contato/{novoContato}")
    public ResponseEntity<?> mudarContato(@PathVariable String novoContato, @RequestBody Usuario user) {
       Command<String> mudarcontCommand = new MudarContCommand(novoContato, user);
       try {
        return ResponseEntity.ok(mudarcontCommand.execute());
       } catch (Exception e) {
        return ResponseEntity.notFound().build();
       }
    }
}
