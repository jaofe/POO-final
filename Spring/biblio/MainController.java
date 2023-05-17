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
        public default <commandType> ResponseEntity<?> executeHandler(Command<commandType> command) {
                try {
                        commandType result = command.execute();
                        return ResponseEntity.ok(result);
                } catch (InvalidCommandException e) {
                        return ResponseEntity.notFound().build();
                } catch (Exception e) {
                        System.out.println("Error inesperado");
                        return ResponseEntity.notFound().build();
                }
        }

        @PostConstruct
        public default void iniciar() {
                Command<Void> initCommand = new InitCommand();
                executeHandler(initCommand);
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