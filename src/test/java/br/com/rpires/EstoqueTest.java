package br.com.rpires;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
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
import java.util.Random;
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

  private Produto cadastrarProduto(String codigo, BigDecimal valor)
    throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
    Produto produto = new Produto();
    produto.setCodigo(codigo);
    produto.setDescricao("Produto Teste");
    produto.setNome("Produto Teste");
    produto.setValor(valor);
    produto.setFabricante("askldjhasdk");
    produtoDAO.cadastrar(produto);
    return produto;
  }

  //Testes
  @Test
  public void estoque_create_read_ok()
    throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
    Integer codigoInt = new Random().nextInt(100000000);
    String codigo = Integer.toString(codigoInt);
    Produto produto = cadastrarProduto(codigo, BigDecimal.ONE);
    estoqueDAO.create(produto, 10);

    int result = estoqueDAO.read(produtoDAO.consultar(codigo));
    assertEquals(10, result);
  }

  @Test
  public void estoque_create_fails()
    throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
    assertThrows(
      RuntimeException.class,
      () -> {
        Integer codigoInt = new Random().nextInt(100000000);
        String codigo = Integer.toString(codigoInt);
        Produto produto = cadastrarProduto(codigo, BigDecimal.ONE);
        estoqueDAO.create(produto, -99);
      }
    );
  }

 
}
