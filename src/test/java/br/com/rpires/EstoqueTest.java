package br.com.rpires;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import br.com.rpires.dao.EstoqueDAO;
import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.IVendaDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.dao.VendaDAO;
import br.com.rpires.domain.Estoque;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EstoqueTest {

  IVendaDAO vendaDAO;
  IProdutoDAO produtoDAO;
  EstoqueDAO estoqueDAO;

  @Before
  public void init() {
    vendaDAO = new VendaDAO();
    produtoDAO = new ProdutoDAO();
    estoqueDAO = new EstoqueDAO();
  }

  @After
  public void end() throws DAOException {
    Collection<Produto> list = produtoDAO.buscarTodos();
    list.forEach(prod -> {
      try {
        produtoDAO.excluir(prod.getCodigo());
      } catch (DAOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private Produto cadastrarProduto(String codigo, BigDecimal valor)
    throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
    Produto produto = new Produto();
    produto.setCodigo(codigo);
    produto.setDescricao("Produto Teste");
    produto.setNome("Produto Teste");
    produto.setValor(valor);
    produtoDAO.cadastrar(produto);
    return produto;
  }

  //Testes
  @Test
  public void estoque_create_read_ok()
    throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
    Produto produto = cadastrarProduto("codigo", BigDecimal.ONE);
    estoqueDAO.create(produto, 10);

    int result = estoqueDAO.read(produtoDAO.consultar("codigo"));
    assertEquals(10, result);
  }

  @Test
  public void estoque_create_fails()
    throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
    Produto produto = cadastrarProduto("codigo", BigDecimal.ONE);
    int result = estoqueDAO.create(produto, -99);

    fail();
  }

  @Test
  public void estoque_read_fails() {
    fail();
  }

  @Test
  public void estoque_update_positivo() {
    fail();
  }

  @Test
  public void estoque_update_negativo() {
    fail();
  }

  @Test
  public void estoque_update_oob() {
    fail();
  }
}
