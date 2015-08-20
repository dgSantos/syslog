package br.com.walmart.syslog.logic;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.walmart.syslog.business.MapaBO;
import br.com.walmart.syslog.dao.MapaDAO;
import br.com.walmart.syslog.dao.MapaDAOInterface;
import br.com.walmart.syslog.dao.exception.MapaNotFoundException;
import br.com.walmart.syslog.dao.model.MapaEntity;
import br.com.walmart.syslog.logic.exception.CalculoMenorDistanciaException;
import br.com.walmart.syslog.model.Caminho;
import br.com.walmart.syslog.model.Mapa;

@RunWith(Arquillian.class)
public class CalculoMenorDistanciaImplTest {
  
  @Deployment
  public static Archive<?> createDeployment() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
          .addClass(MapaDAOInterface.class)
          .addClass(MapaDAO.class)
          .addClass(Mapa.class)
          .addClass(Caminho.class)
          .addPackage(MapaEntity.class.getPackage())
          .addClass(CalculoMenorDistancia.class)
          .addClass(CalculoMenorDistancia.class)
          .addPackage(CalculoMenorDistanciaException.class.getPackage())
          .addAsWebInfResource("test-ds.xml")
          .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
          .addAsResource("Messages.properties")
          .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  }
  
  @Inject
  MapaBO bo;
  
  @Test
  @UsingDataSet("datasets/dataset.xml")
  public void testExcute() {
    try {
    Mapa mapa = bo.obtemMapaPorNome("SP");
    } catch(MapaNotFoundException ex) {
      fail(ex.getMessage());
    }
  }

}
