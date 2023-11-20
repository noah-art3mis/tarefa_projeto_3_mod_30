/**
 * 
 */
package br.com.rpires.dao.jpa;

import br.com.rpires.domain.jpa.Cliente;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super(Cliente.class);
	}

}
