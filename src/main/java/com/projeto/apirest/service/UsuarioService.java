package com.projeto.apirest.service;

import com.projeto.apirest.model.Usuario;
import com.projeto.apirest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    //Construtor e injeção de dependencias
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //Metodos de requisiçao

    //Cria um usuario do tipo (Usuario) e salva no repositorio
    public Usuario criarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    //Cria uma lista do tipo Usuario, findAll busca no BD as informaçoes e joga nessa lista retornando ela
    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    //Procura o usuario por id, se não encontrar retorna nulo
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    //Recebe por parametro o id do usuario a ser atualizado, e as informacoes da mudanca (mudanca completa)
    //A nova variavel Usuario procura pelo usuario atual atraves do id, se achar atualizar e salva no repositorio
    public Usuario atualizarUsuario(Long id, Usuario mudanca) {
        Usuario atualizar = buscarPorId(id);
        if(atualizar == null) return null;

        atualizar.setNome(mudanca.getNome());
        atualizar.setEmail(mudanca.getEmail());
        atualizar.setSenha(mudanca.getSenha());
        return repository.save(atualizar);
    }

    //Se existir esse Id, as info sao deletadas, se nao existir retorna false
    public boolean deletarUsuario(Long id) {
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
