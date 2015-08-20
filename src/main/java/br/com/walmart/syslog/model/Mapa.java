package br.com.walmart.syslog.model;

import java.util.Collections;
import java.util.List;

/**
 * Classe 'Mapa' representa um agrupamento de caminhos({@link Caminho}) conectados ou não 
 * entre si, agrupados por uma localidade.
 * Exemplo: estado de SP.
 * 
 * @author Diego Santos
 * @since 17/08/2015
 *
 */
public class Mapa {
  
  private String nome;
  private List<Caminho> caminhos;
  
  public Mapa(String nome, List<Caminho> caminhos) {
    this.nome = nome;
    this.caminhos = caminhos;
  }

  public String getNome() {
    return nome;
  }
  
  /**
   * Retorna uma lista de caminhos.
   * 
   * @return lista(imutável) de caminhos
   */
  public List<Caminho> getCaminhos() {
    return Collections.unmodifiableList(caminhos);
  }
  
}
