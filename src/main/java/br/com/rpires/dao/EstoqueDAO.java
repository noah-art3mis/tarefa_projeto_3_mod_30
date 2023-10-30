package br.com.rpires.dao;

import br.com.rpires.dao.generic.jdbc.ConnectionFactory;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO extends Connectable {

  public Integer create(Produto produto, Integer quantidade) {
    Connection connection = null;
    PreparedStatement stm = null;

    try {
      connection = ConnectionFactory.getConnection();
      stm =
        connection.prepareStatement(
          "INSERT INTO tb_estoque (produto_id, quantidade) VALUES (?, ?);"
        );
      stm.setLong(1, produto.getId());
      stm.setInt(2, quantidade);
      return stm.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("ERRO ALTERANDO QUANTIDADE DE ITENS ", e);
    } finally {
      closeConnection(connection, stm, null);
    }
  }

  public Integer read(Produto produto) throws DAOException {
    Connection connection = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
      connection = ConnectionFactory.getConnection();
      stm =
        connection.prepareStatement(
          "SELECT * FROM tb_estoque WHERE produto_id = ?;"
        );
      stm.setLong(1, produto.getId());
      rs = stm.executeQuery();
      if (rs.next()) {
        return rs.getInt(3); // coluna 3 é quantidade
      }
    } catch (SQLException e) {
      throw new DAOException("ERRO RECUPERANDO QUANTIDADE DE ITENS ", e);
    } finally {
      closeConnection(connection, stm, rs);
    }
    return 0;
  }

  public Integer update(Produto produto, Integer quantidade) {
    Connection connection = null;
    PreparedStatement stm = null;

    try {
      connection = ConnectionFactory.getConnection();
      stm =
        connection.prepareStatement(
          "UPDATE tb_estoque SET quantidade = ? WHERE id_produto = ?"
        );
      stm.setInt(1, quantidade);
      stm.setLong(2, produto.getId());
      return stm.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("ERRO ALTERANDO QUANTIDADE DE ITENS ", e);
    } finally {
      closeConnection(connection, stm, null);
    }
  }
}
