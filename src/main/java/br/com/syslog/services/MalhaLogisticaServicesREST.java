/**
 * 
 */
package br.com.syslog.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.syslog.business.MapaBO;
import br.com.syslog.model.Caminho;
import br.com.syslog.model.Mapa;
import br.com.syslog.services.model.MapaJSON;

/**
 * Classe que expoe o serviço de cadastro de Malha Logistica.
 * 
 * @author Diego Santos
 * @since 18/08/2015
 *  
 */
@ApplicationScoped
@Path("/"+Constantes.MALHA_SERVICE_PATH)
public class MalhaLogisticaServicesREST {
  
  @Inject
  private MapaBO bo;
  
  /**
   * Servico responsavel por cadastrar os dados da malha logistica
   * 
   * @param mapa um objeto do tipo {@link MapaJSON} com o nome do mapa e os dados
   *  da malha logistica, origem, destino e distancia.
   */
  @Consumes(MediaType.APPLICATION_JSON)
  @POST
  public void insert(MapaJSON mapa) {
    List<Caminho> caminhos = new ArrayList<Caminho>();
    for (String[] linha : mapa.getMalha()) {
      try {
        String origem = linha[0];
        String destino = linha [1];
        double distancia = Double.valueOf(linha[2]);
        caminhos.add(new Caminho(origem, destino, distancia));
      } catch (NumberFormatException ex) {
        // TODO tratar erro
        return;
      }
    }
    bo.persisteMapa(new Mapa(mapa.getNome(), caminhos));
  }

}
