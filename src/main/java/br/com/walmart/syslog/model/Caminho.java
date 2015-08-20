package br.com.walmart.syslog.model;

import java.util.List;

/**
 * Classe 'Caminho' representa uma origem(ponto de partida), destino(ponto de chegada) 
 * e a distancia em KM entre os pontos. 
 * 
 * @author Diego Santos
 * @since 17/08/2015
 *
 */
public class Caminho {

  private int id;
  private String origem;
  private String destino;
  private double distancia;
  
  /**
   * Constroi um objeto Caminho.
   * 
   * @param origem ponto de partida
   * @param destino ponto de destino
   * @param distancia distancia em KM
   */
  public Caminho(String origem, String destino, double distancia) {
    this.origem = origem;
    this.destino = destino;
    this.distancia = distancia;
  }
  
  /**
   * Obtem o id de banco.
   * 
   * @return id
   */
  public int getId() {
    return id;
  }
  
  public String getOrigem() {
    return origem;
  }
  
  public String getDestino() {
    return destino;
  }

  public double getDistancia() {
    return distancia;
  }
  
}
