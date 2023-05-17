package biblioteca.biblio;

import java.util.*;

public class Biblioteca {

    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Livro> livros = new ArrayList<>();

    public Usuario login(String username, String senha) {
        Usuario usuario = buscarUsuario(username);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    public void criarUsuario(String username, String senha, String contato) {
        Cliente novoUsuario = new Cliente(username, senha, contato);
        this.usuarios.add(novoUsuario);
    }

    public void cadastrarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public ArrayList<String> listarUsuarios() {
        ArrayList<String> listaUsuarios = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            listaUsuarios.add(usuario.printUsuario());
        }
        return listaUsuarios;
    }

    public void criarAdmin(String username, String senha, String contato) {
        Admin novoUsuario = new Admin(username, senha, contato);
        this.usuarios.add(novoUsuario);
    }

    public Livro buscarLivro(String titulo) {
        Livro livro = null;

        for (Livro l : this.livros) {
            if (l.getTitulo().equals(titulo))
                livro = l;
        }

        return livro;
    }

    public Livro buscarLivroId(int id) {

        Livro livro = null;

        for (Livro l : this.livros) {
            if (l.getId() == id)
                livro = l;
        }

        return livro;
    }

    public Usuario buscarCliente(String username) {
        for (Usuario usuario : this.usuarios) {
            if (usuario instanceof Cliente) {
                if (usuario.getUsername().equals(username)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Usuario buscarAdmin(String username) {
        for (Usuario admin : this.usuarios) {
            if (admin instanceof Admin) {
                if (admin.getUsername().equals(username)) {
                    return admin;
                }
            }
        }
        return null;
    }

    public Usuario buscarUsuario(String username) {
        Usuario usuario = buscarAdmin(username);

        if (usuario == null)
            usuario = buscarCliente(username);

        return usuario;
    }
}
