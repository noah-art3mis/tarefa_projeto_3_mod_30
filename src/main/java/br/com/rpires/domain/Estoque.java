package br.com.rpires.domain;

public class Estoque {

  //propriedades
  private Produto produto;
  private Integer quantidade;

  //get set
  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }
}
