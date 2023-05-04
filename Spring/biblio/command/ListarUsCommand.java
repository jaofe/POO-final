package biblioteca.biblio.command;

import java.util.ArrayList;

import biblioteca.biblio.custonExceptions.InvalidCommandException;

public class ListarUsCommand implements Command<ArrayList<String>>{
    @Override
    public ArrayList<String> execute() throws InvalidCommandException {
        return biblioteca.listarUsuarios();
    }
}
