package br.com.syslog.logic;

/**
 * 
 * @author Diego Santos
 * @since 19/08/2015
 *
 */
public class Aresta {
  
  private Vertice origem;
  private Vertice destino;
  private double peso;
  
  public Aresta(Vertice origem, Vertice destino, double peso) {
    this.origem = origem;
    this.destino = destino;
    this.peso = peso;
  }

  public Vertice getOrigem() {
    return origem;
  }

  public Vertice getDestino() {
    return destino;
  }

  public double getPeso() {
    return peso;
  }

}
