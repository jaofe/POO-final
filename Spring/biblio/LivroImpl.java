package biblioteca.biblio;

import java.util.ArrayList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.biblio.command.AlugLiCommand;
import biblioteca.biblio.command.AtraCommand;
import biblioteca.biblio.command.BusLiCommand;
import biblioteca.biblio.command.BuscaCommand;
import biblioteca.biblio.command.CadLiCommand;
import biblioteca.biblio.command.Command;
import biblioteca.biblio.command.DevAtraCommand;
import biblioteca.biblio.command.DevCommand;
import biblioteca.biblio.command.FazerResCommand;
import biblioteca.biblio.command.FazerRevCommand;
import biblioteca.biblio.command.ListarLiCommand;
import biblioteca.biblio.command.RemRevCommand;

@RestController
@RequestMapping("/livro")
public class LivroImpl implements MainController<Livro> {
    
    Command<ArrayList<Livro>> listarLivrosCommand = new ListarLiCommand();

    @Override
    public ResponseEntity<?> cadastro(Livro livro) {
        Command<String> livros = new CadLiCommand(livro);
        try {
            return ResponseEntity.ok(livros.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        try {
            return ResponseEntity.ok(listarLivrosCommand.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {
        Command<Livro> livro = new BusLiCommand(id);
        try {
            return ResponseEntity.ok(livro.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> atrasados() {
        Command<ArrayList<String>> Atrasados = new AtraCommand();
        try {
            return ResponseEntity.ok(Atrasados.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/alugar/{id}/{tempo}")
    public ResponseEntity<?> alugarLivro(@PathVariable int id, @PathVariable int tempo, @RequestBody Usuario user) {

        Command<String> Alugar = new AlugLiCommand(id, tempo, user);
        try {
            return ResponseEntity.ok(Alugar.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<?> devolver(@PathVariable int id, @RequestBody Usuario user) {
        
        Command<String> Devolver = new DevCommand(user, id);
        try {
            return ResponseEntity.ok(Devolver.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/devolver-atrasado/")
    public ResponseEntity<?> devolverAtrasado(@RequestBody Usuario user) {
        
        Command<String> DevAtrasado = new DevAtraCommand(user);
        try {
            return ResponseEntity.ok(DevAtrasado.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/review/{id}")
    public ResponseEntity<?> fazerReview(@PathVariable int id, @RequestBody Review review) {
        
        Command<String> FazerReview = new FazerRevCommand(id, review);
        try {
            return ResponseEntity.ok(FazerReview.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reservar/{id}")
    public ResponseEntity<?> fazerReserva(@PathVariable int id, @RequestBody Usuario user) {
        
        Command<String> FazerReserva = new FazerResCommand(id, user);
        try {
            return ResponseEntity.ok(FazerReserva.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("busca/{search}")
    public ResponseEntity<?> busca(@PathVariable String search) {
        
        Command<ArrayList<Livro>> Busca = new BuscaCommand(search);
        try {
            return ResponseEntity.ok(Busca.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/remover-review/{id}")
    public ResponseEntity<String> removerReview(@PathVariable int id, @RequestBody Usuario user) {
        
        Command<String> Remover = new RemRevCommand(id, user);
        try {
            return ResponseEntity.ok(Remover.execute());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
