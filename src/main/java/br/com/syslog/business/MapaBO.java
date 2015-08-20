package br.com.syslog.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.syslog.dao.MapaDAOInterface;
import br.com.syslog.dao.exception.MapaNotFoundException;
import br.com.syslog.dao.model.CaminhoEntity;
import br.com.syslog.dao.model.MapaEntity;
import br.com.syslog.model.Caminho;
import br.com.syslog.model.Mapa;

/**
 * @author Diego Santos
 *
 */
public class MapaBO {
  
  @Inject
  private MapaDAOInterface dao;

  public Mapa obtemMapaPorNome(String nome) throws MapaNotFoundException {
    MapaEntity mapaEntity = dao.obtemMapaPorNome(nome);
    List<Caminho> caminhos = new ArrayList<Caminho>();
    for (CaminhoEntity caminho: mapaEntity.getCaminhos()) {
      caminhos.add(new Caminho(caminho.getOrigem(), caminho.getDestino(), caminho.getDistancia()));
    }
    return new Mapa(mapaEntity.getNome(), caminhos);
  }
  
  public void persisteMapa(Mapa mapa) {
    List<CaminhoEntity> caminhos = new ArrayList<CaminhoEntity>();
    for (Caminho caminho : mapa.getCaminhos()) {
      caminhos.add(new CaminhoEntity(caminho.getOrigem(), caminho.getDestino(), caminho.getDistancia()));
    }
    dao.persisteMapa(new MapaEntity(mapa.getNome(), caminhos));
  }

}
