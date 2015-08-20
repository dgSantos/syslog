package br.com.syslog.dao;

import java.sql.SQLException;

import br.com.syslog.dao.exception.MapaNotFoundException;
import br.com.syslog.dao.model.MapaEntity;

/**
 * Contrato do DAO de acesso aos Mapas.
 * 
 * @author Diego Santos
 * @since 17/08/2015
 *
 */
public interface MapaDAOInterface {
  
  /**
   * Obtem um mapa a partir do nome.
   * 
   * @param nome nome do mapa
   * @return mapa
   * @throws MapaNotFoundException caso o mapa não seja localizado.
   * @throws SQLException caso ocorra uma falha de banco ao obter o mapa.
   * 
   */
  public MapaEntity obtemMapaPorNome(String nome) throws MapaNotFoundException;
  
  public void persisteMapa(MapaEntity mapa);

}
