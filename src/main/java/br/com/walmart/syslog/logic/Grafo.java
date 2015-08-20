package br.com.walmart.syslog.logic;

import java.util.List;

/**
 * @author Diego Santos
 * @since 19/08/2015
 *
 */
public class Grafo {
  
  private List<Vertice> vertices;
  private List<Aresta> arestas;
  
  public Grafo(List<Vertice> vertices, List<Aresta> arestas) {
    this.vertices = vertices;
    this.arestas = arestas;
  }

  public List<Vertice> getVertices() {
    return vertices;
  }

  public List<Aresta> getArestas() {
    return arestas;
  }

}
