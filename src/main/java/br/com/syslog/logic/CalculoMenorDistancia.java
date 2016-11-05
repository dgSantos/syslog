package br.com.syslog.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.syslog.logic.exception.CalculoMenorDistanciaException;
import br.com.syslog.model.Caminho;
import br.com.syslog.model.Mapa;

/**
 * Implementação do cálculo de menor distância.
 * Baseado no algortimo de custo mínimo de E. Dijkstra.
 * 
 * Artigo utilizado para implementacao:
 * @see <a href="http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html">http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html</a>
 * 
 */
public class CalculoMenorDistancia {

  private final List<Aresta> arestas;
  private Set<Vertice> nosFechados;
  private Set<Vertice> nosAbertos;
  private Map<Vertice, Vertice> precedentes;
  private Map<Vertice, Double> distancia;
  Map<String, Vertice> vertices;
  
  /**
   * Instancia o algoritmo de custo minimo dado um grafo.
   * 
   * @param grafo o grafo
   */
  public CalculoMenorDistancia(Mapa mapa) {
    vertices = new HashMap<String, Vertice>(); 
    List<Aresta> arestas = new ArrayList<Aresta>();
    // Percorre a malha logistica
    for (Caminho caminho : mapa.getCaminhos()) {
      Vertice origem = vertices.get(caminho.getOrigem()),
               destino = vertices.get(caminho.getDestino());
      if (origem == null) {
        origem = new Vertice(caminho.getOrigem());
        vertices.put(caminho.getOrigem(), origem);
      }
      if (destino == null) {
        destino = new Vertice(caminho.getDestino());
        vertices.put(caminho.getDestino(), destino);
      }
      arestas.add(new Aresta(origem, destino, caminho.getDistancia()));
    }
    this.arestas = new ArrayList<Aresta>(arestas);
  }

  /**
   * Executa o calculo de custo minimo.
   * 
   * @param origem
   */
  public void execute(String s) throws CalculoMenorDistanciaException {
    Vertice origem = vertices.get(s);
    if (origem == null) {
      throw new CalculoMenorDistanciaException(String.format("Destino '%s' nulo ou nao encontrado no mapa!!", s));
    }
    nosFechados = new HashSet<Vertice>();
    nosAbertos = new HashSet<Vertice>();
    distancia = new HashMap<Vertice, Double>();
    precedentes = new HashMap<Vertice, Vertice>();
    // No Raiz tem peso 0
    distancia.put(origem, 0d);
    nosAbertos.add(origem);
    while (nosAbertos.size() > 0) {
      Vertice no = getMinimo(nosAbertos);
      // Fecha o no
      nosFechados.add(no);
      nosAbertos.remove(no);
      // Percorre os nos Adjacentes
      encontraMenorDistancia(no);
    }
  }

  private void encontraMenorDistancia(Vertice node) {
    List<Vertice> nosAdjacentes = getVizinhos(node);
    for (Vertice destino : nosAdjacentes) {
      if (obtemMenorDistancia(destino) > obtemMenorDistancia(node)
          + getDistancia(node, destino)) {
        distancia.put(destino, obtemMenorDistancia(node)
            + getDistancia(node, destino));
        precedentes.put(destino, node);
        nosAbertos.add(destino);
      }
    }
  }

  private double getDistancia(Vertice no, Vertice destino) {
    for (Aresta Aresta : arestas) {
      if (Aresta.getOrigem().equals(no)
          && Aresta.getDestino().equals(destino)) {
        return Aresta.getPeso();
      }
    }
    throw new RuntimeException("Erro inesperado!!");
  }

  private List<Vertice> getVizinhos(Vertice no) {
    List<Vertice> vizinhos = new ArrayList<Vertice>();
    for (Aresta aresta : arestas) {
      if (aresta.getOrigem().equals(no)
          && !isAberto(aresta.getDestino())) {
        vizinhos.add(aresta.getDestino());
      }
    }
    return vizinhos;
  }

  private Vertice getMinimo(Set<Vertice> vertices) {
    Vertice minimo = null;
    for (Vertice vertice : vertices) {
      if (minimo == null) {
        minimo = vertice;
      } else {
        if (obtemMenorDistancia(vertice) < obtemMenorDistancia(minimo)) {
          minimo = vertice;
        }
      }
    }
    return minimo;
  }

  private boolean isAberto(Vertice vertice) {
    return nosFechados.contains(vertice);
  }

  private double obtemMenorDistancia(Vertice destino) {
    Double d = distancia.get(destino);
    if (d == null) {
      return Double.MAX_VALUE;
    } else {
      return d;
    }
  }

  /**
   * Obtem o melhor caminho(menor custo)
   * 
   * @param destino
   * @return uma lista ligada representando o melhor caminho ate destino.
   */
  public LinkedList<Vertice> getCaminho(String w) throws CalculoMenorDistanciaException {
    LinkedList<Vertice> caminho = new LinkedList<Vertice>();
    Vertice destino = vertices.get(w);
    if (destino == null) {
      throw new CalculoMenorDistanciaException(String.format("Destino '%s' nulo ou nao encontrado no mapa!!", w));
    }
    Vertice step = destino;
    if (precedentes.get(step) == null) {
      return null;
    }
    caminho.add(step);
    while (precedentes.get(step) != null) {
      step = precedentes.get(step);
      caminho.add(step);
    }
    // Coloca na ordem certa, precedente antes.
    Collections.reverse(caminho);
    return caminho;
  }
  
  /**
   * Obtem a distancia para determinado vertice.
   * 
   * @param destino
   * @return
   */
  public double getDistancia(String w) throws CalculoMenorDistanciaException {
    Vertice destino = vertices.get(w);
    if (destino == null) {
      throw new CalculoMenorDistanciaException(String.format("Destino '%s' nulo ou nao encontrado no mapa!!", w));
    }
    if (distancia.containsKey(destino)) {
      return distancia.get(destino);
    }
    throw new RuntimeException("Erro inesperado!!");
  }
  
}
