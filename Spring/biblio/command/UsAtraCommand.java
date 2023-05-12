package biblioteca.biblio.command;

import java.util.ArrayList;

import biblioteca.biblio.Usuario;
import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class UsAtraCommand implements Command<ArrayList<String>>{

    @Override
    public ArrayList<String> execute() throws InvalidCommandException {
        ArrayList<String> usuariosAtrasados = new ArrayList<>();
        for (Usuario usuario : biblioteca.usuarios) {
            if (usuario.temLivroAtrasado()) {
                usuariosAtrasados
                        .add("Usuario: " + usuario.getUsername() + ", forma de contato: " + usuario.getContato() + ".");
            }
        }
        return usuariosAtrasados;
    }
    
}
