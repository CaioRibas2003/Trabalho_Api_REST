package com.projeto.apirest.service;import com.projeto.apirest.model.Produto;
import com.projeto.apirest.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    //Metodos de requisiçao
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    //Cria um produto do tipo (Produto) e salva no repositorio
    public Produto criarProduto(Produto produto) {
        return repository.save(produto);
    }

    //Cria uma lista do tipo Produto, findAll busca no BD as informaçoes e jogo nessa lista retornando ela
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    //Procura o usuario por id, se não encontrar retorna nulo
    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    //Recebe por paramentro o id do usuario a ser atualizado, e as informacoes da mudanca (mudanca completa)
    //A nova variavel Usuario procura pelo usuario atual atraves do id, se achar atualizar e salva no repositorio
    public Produto atualizarProduto(Long id, Produto mudanca) {
        Produto atualizar = buscarPorId(id);
        if(atualizar == null) return null;

        atualizar.setNome(mudanca.getNome());
        atualizar.setPreco(mudanca.getPreco());
        atualizar.setDescricao(mudanca.getDescricao());
        return repository.save(atualizar);
    }

    //Se existir esse Id, as info sao deletas, se nao existir retorna false
    public boolean deletarProduto(Long id) {
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
