package br.com.walmart.syslog.services.model;


/**
 * @author Diego Santos
 *
 */
public class MapaJSON {

  private String nome;
  private String[][] malha;
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String[][] getMalha() {
    return malha;
  }
  
  public void setMalha(String[][] malha) {
    this.malha = malha;
  }

}
