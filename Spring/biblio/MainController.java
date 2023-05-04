package biblioteca.biblio;

import biblioteca.biblio.command.Command;
import biblioteca.biblio.command.InitCommand;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.PostConstruct;

public interface MainController<T> {
        static Biblioteca biblioteca = new Biblioteca();

        Command<Void> initCommand = new InitCommand();

        @PostConstruct
        public default void iniciar() {
                try {
                        initCommand.execute();
                } catch (InvalidCommandException e) {
                        System.out.println(e.getMessage());
                }
        }

        @PostMapping("/cadastro")
        public ResponseEntity<?> cadastro(@RequestBody T tipo);

        @GetMapping("/getall")
        public ResponseEntity<?> listarObjetos();

        @GetMapping("/{id}")
        public ResponseEntity<?> pegarObjeto(@PathVariable String id);

        @GetMapping("/get-atrasados")
        public ResponseEntity<?> atrasados();

}