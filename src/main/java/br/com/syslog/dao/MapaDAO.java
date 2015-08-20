/**
 * 
 */
package br.com.syslog.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.syslog.dao.exception.MapaNotFoundException;
import br.com.syslog.dao.model.MapaEntity;

/**
 * DAO para o mapa.
 * 
 * @author Diego Santos
 * @since 18/08/2015
 *
 */
@Stateless
public class MapaDAO implements MapaDAOInterface {
  
  @PersistenceContext
  private EntityManager em;

  /* (non-Javadoc)
   * @see br.com.syslog.dao.MapaDAOInterface#obtemMapaPorNome(java.lang.String)
   */
  @Override
  public MapaEntity obtemMapaPorNome(String nome) throws MapaNotFoundException{
    MapaEntity map = null;
    map = em.find(MapaEntity.class, nome);
    if (map == null) {
      throw new MapaNotFoundException(String.format("Mapa '%s' não encontrado!", nome));
    }
    return map;
  }

  /* (non-Javadoc)
   * @see br.com.syslog.dao.MapaDAOInterface#persisteMapa(br.com.syslog.dao.model.MapaEntity)
   */
  @Override
  public void persisteMapa(MapaEntity mapa) {
    em.merge(mapa);
  }

}
