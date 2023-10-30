/**
 *
 */
package br.com.rpires;

import static org.junit.Assert.assertTrue;

import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.domain.Cliente;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteDAOTest {

  private IClienteDAO clienteDao;

  public ClienteDAOTest() {
    clienteDao = new ClienteDAO();
  }

  @After
  public void end() throws DAOException {
    Collection<Cliente> list = clienteDao.buscarTodos();
    list.forEach(cli -> {
      try {
        clienteDao.excluir(cli.getCpf());
      } catch (DAOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
  }

  private Cliente buildCliente(Long randomCpf) {
    Cliente cliente = new Cliente();
    cliente.setCpf(randomCpf);
    cliente.setNome("Rodrigo");
    cliente.setCidade("São Paulo");
    cliente.setEnd("End");
    cliente.setEstado("SP");
    cliente.setNumero(10);
    cliente.setTel(1199999999L);
    cliente.setEmail("123@gmail.com");
    return cliente;
  }

  @Test
  public void pesquisarCliente()
    throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
    Cliente cliente = buildCliente(new Random().nextLong());
    clienteDao.cadastrar(cliente);

    Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
    Assert.assertNotNull(clienteConsultado);

    clienteDao.excluir(cliente.getCpf());
  }

  @Test
  public void salvarCliente()
    throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
    Cliente cliente = buildCliente(new Random().nextLong());
    Boolean retorno = clienteDao.cadastrar(cliente);
    Assert.assertTrue(retorno);

    Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
    Assert.assertNotNull(clienteConsultado);

    clienteDao.excluir(cliente.getCpf());
  }

  @Test
  public void excluirCliente()
    throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
    Cliente cliente = buildCliente(new Random().nextLong());
    Boolean retorno = clienteDao.cadastrar(cliente);
    Assert.assertTrue(retorno);

    Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
    Assert.assertNotNull(clienteConsultado);

    clienteDao.excluir(cliente.getCpf());
    clienteConsultado = clienteDao.consultar(cliente.getCpf());
    Assert.assertNull(clienteConsultado);
  }

  @Test
  public void alterarCliente()
    throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
    Cliente cliente = buildCliente(new Random().nextLong());

    Boolean retorno = clienteDao.cadastrar(cliente);
    Assert.assertTrue(retorno);

    Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
    Assert.assertNotNull(clienteConsultado);

    clienteConsultado.setNome("Rodrigo Pires");
    clienteDao.alterar(clienteConsultado);

    Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
    Assert.assertNotNull(clienteAlterado);
    Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

    clienteDao.excluir(cliente.getCpf());
    clienteConsultado = clienteDao.consultar(cliente.getCpf());
    Assert.assertNull(clienteConsultado);
  }

  @Test
  public void buscarTodos()
    throws TipoChaveNaoEncontradaException, DAOException {
    clienteDao.cadastrar(buildCliente(new Random().nextLong()));
    clienteDao.cadastrar(buildCliente(new Random().nextLong()));

    Collection<Cliente> list = clienteDao.buscarTodos();
    assertTrue(list != null);
    assertTrue(list.size() == 2);

    list.forEach(cli -> {
      try {
        clienteDao.excluir(cli.getCpf());
      } catch (DAOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });

  }
}
