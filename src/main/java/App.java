import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.EstoqueDAO;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.IVendaDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.dao.VendaDAO;
import br.com.rpires.domain.Cliente;
import br.com.rpires.domain.Produto;
import br.com.rpires.domain.ProdutoQuantidade;
import br.com.rpires.domain.Venda;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import java.math.BigDecimal;
import org.junit.Assert;

public class App {

  static IProdutoDAO produtoDAO = new ProdutoDAO();
  static IClienteDAO clienteDAO = new ClienteDAO();
  static IVendaDAO vendaDAO = new VendaDAO();
  static EstoqueDAO estoqueDAO = new EstoqueDAO();

  public static void main(String[] args)
    throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
    produtoDAO.cadastrar(criarProduto("codigo"));
    clienteDAO.cadastrar(criarCliente(1L));

    Produto produto = produtoDAO.consultar("codigo");
    Cliente cliente = clienteDAO.consultar(1L);

    Venda venda = criarVenda(cliente);
    venda.adicionarProduto(produto, 1);
    vendaDAO.cadastrar(venda);

    vender(venda);
  }

  private static Produto criarProduto(String codigo)
    throws TipoChaveNaoEncontradaException, DAOException {
    Produto produto = new Produto();
    produto.setCodigo(codigo);
    produto.setDescricao("Produto 1");
    produto.setNome("Produto 1");
    produto.setValor(BigDecimal.TEN);
    return produto;
  }

  private static Cliente criarCliente(Long cpf) {
    Cliente cliente = new Cliente();
    cliente.setCpf(cpf);
    cliente.setNome("Rodrigo");
    cliente.setCidade("São Paulo");
    cliente.setEnd("End");
    cliente.setEstado("SP");
    cliente.setNumero(10);
    cliente.setTel(1199999999L);
    return cliente;
  }

  private static Venda criarVenda(Cliente consultar) {
    Venda venda = new Venda();
    venda.setCliente(consultar);
    venda.setCodigo("codigo");
    venda.setDataVenda(null);
    venda.setId(null);
    venda.setProdutos(null);
    venda.setStatus(null);
    venda.setValorTotal(null);
    return venda;
  }

  public static void vender(Venda venda) {
    for (ProdutoQuantidade item : venda.getProdutos()) {
      Produto produto = item.getProduto();
      Integer quantidade = item.getQuantidade() * -1;
      estoqueDAO.update(produto, quantidade);
    }

    try {
      vendaDAO.finalizarVenda(venda);
    } catch (TipoChaveNaoEncontradaException | DAOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
