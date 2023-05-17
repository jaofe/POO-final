package biblioteca.biblio;

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
import biblioteca.biblio.command.DevAtraCommand;
import biblioteca.biblio.command.DevCommand;
import biblioteca.biblio.command.FazerResCommand;
import biblioteca.biblio.command.FazerRevCommand;
import biblioteca.biblio.command.ListarLiCommand;
import biblioteca.biblio.command.RemRevCommand;

@RestController
@RequestMapping("/livro")
public class LivroImpl implements MainController<Livro> {
    @Override
    public ResponseEntity<?> cadastro(Livro livro) {
        System.out.println(livro.getSinopse());
        return executeHandler(new CadLiCommand(livro));
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        return executeHandler(new ListarLiCommand());
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {
        return executeHandler(new BusLiCommand(id));
    }

    @Override
    public ResponseEntity<?> atrasados() {
        return executeHandler(new AtraCommand());
    }

    @PostMapping("/alugar/{id}/{tempo}")
    public ResponseEntity<?> alugarLivro(@PathVariable int id, @PathVariable int tempo, @RequestBody Usuario user) {
        return executeHandler(new AlugLiCommand(id, tempo, user));
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<?> devolver(@PathVariable int id, @RequestBody Usuario user) {
        return executeHandler(new DevCommand(user, id));
    }

    @PostMapping("/devolver-atrasado/")
    public ResponseEntity<?> devolverAtrasado(@RequestBody Usuario user) {
        return executeHandler(new DevAtraCommand(user));
    }

    @PostMapping("/review/{id}")
    public ResponseEntity<?> fazerReview(@PathVariable int id, @RequestBody Review review) {
        return executeHandler(new FazerRevCommand(id, review));
    }

    @PostMapping("/reservar/{id}")
    public ResponseEntity<?> fazerReserva(@PathVariable int id, @RequestBody Usuario user) {
        return executeHandler(new FazerResCommand(id, user));
    }

    @GetMapping("busca/{search}")
    public ResponseEntity<?> busca(@PathVariable String search) {
        return executeHandler(new BuscaCommand(search));
    }

    @PutMapping("/remover-review/{id}")
    public ResponseEntity<?> removerReview(@PathVariable int id, @RequestBody Usuario user) {
        return executeHandler(new RemRevCommand(id, user));
    }
}
