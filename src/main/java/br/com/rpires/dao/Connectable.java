package br.com.rpires.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Connectable {

  static void closeConnection(
    Connection connection,
    PreparedStatement stm,
    ResultSet rs
  ) {
    try {
      if (rs != null && !rs.isClosed()) {
        rs.close();
      }
      if (stm != null && !stm.isClosed()) {
        stm.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
