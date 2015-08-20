package br.com.walmart.syslog.logic;

/**
 * @author Diego Santos
 * @since 19/08/2015
 *
 */
public class Vertice {
  
  private String nome;

  public Vertice(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
  
  @Override
  public String toString() {
    return nome;
  }
  

}
