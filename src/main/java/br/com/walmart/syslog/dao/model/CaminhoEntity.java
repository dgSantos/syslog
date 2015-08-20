package br.com.walmart.syslog.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Caminho")
@Table(name="caminho")
public class CaminhoEntity {
  
  @Id
  @GeneratedValue
  @Column(name="id")
  private int id;
  
  @Column(name="origem")
  private String origem;
  
  @Column(name="destino")
  private String destino;
  
  @Column(name="distancia")
  private double distancia;
  
  public CaminhoEntity() {}
  
  public CaminhoEntity(String origem, String destino, double distancia) {
    this.origem = origem;
    this.destino = destino;
    this.distancia = distancia;
  }



  public int getId() {
    return id;
  }

  public String getOrigem() {
    return origem;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public double getDistancia() {
    return distancia;
  }

  public void setDistancia(double distancia) {
    this.distancia = distancia;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setOrigem(String origem) {
    this.origem = origem;
  }
  
}
