package br.com.casadocodigo.loja.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {

    @Autowired
    private ProdutoDAO produtoDao;

    @PersistenceContext
    private EntityManager manager;


    
    @RequestMapping("form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());
        return modelAndView;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void gravar(Produto produto) {
        manager.persist(produto);
    }

    public List<Produto> listar() {
        return manager.createQuery("select p from Produto p", Produto.class)
            .getResultList();
    }

    
}
