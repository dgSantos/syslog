package br.com.walmart.syslog.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Mapa")
@Table(name="mapa")
public class MapaEntity {
  
  @Id
  @Column(name="nome")
  private String nome;
  
  @OneToMany(targetEntity=CaminhoEntity.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
  private List<CaminhoEntity> caminhos;
  
  public MapaEntity() {}
  
  public MapaEntity(String nome, List<CaminhoEntity> caminhos) {
    this.nome = nome;
    this.caminhos = caminhos;
  }

  public String getNome() {
    return nome;
  }

  public List<CaminhoEntity> getCaminhos() {
    return caminhos;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCaminhos(List<CaminhoEntity> caminhos) {
    this.caminhos = caminhos;
  }

}
