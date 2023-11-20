/**
 * 
 */
package br.com.rpires.dao.jpa;

import br.com.rpires.domain.jpa.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO {

	public ProdutoDAO() {
		super(Produto.class);
	}

}
