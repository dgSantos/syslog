package br.com.syslog.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.syslog.logic.exception.CalculoMenorDistanciaException;
import br.com.syslog.business.MapaBO;
import br.com.syslog.dao.exception.MapaNotFoundException;
import br.com.syslog.logic.CalculoMenorDistancia;
import br.com.syslog.logic.Vertice;
import br.com.syslog.model.Mapa;
import br.com.syslog.services.model.CustoMinimoResponseJSON;

/**
 * Classe que expoe o serviço de calculo do custo minimo.
 * 
 * @author Diego Santos
 * @since 18/08/2015
 *
 */
@ApplicationScoped
@Path("/"+Constantes.CUSTO_SERVICE_PATH)
public class CustoMinimoServicesREST {
  
  @Inject
  MapaBO bo;
  
  /**
   * Servico responsavel pelo calculo da rota e custo minimo.
   * 
   * @param nomeMapa nome do mapa
   * @param origem ponto de partida
   * @param destino ponto de chegada
   * @param autonomia autonomia do veiculo  
   * @param combustivel valor do combustivel
   * @return um objeto CustoMinimoResponseJSON com a melhor rota e o custo.
   */
  @Produces(MediaType.APPLICATION_JSON)
  @GET
  public Response getCusto(
      @QueryParam(Constantes.MAPA_PARAM) String nomeMapa,
      @QueryParam(Constantes.ORIGEM_PARAM) String origem,
      @QueryParam(Constantes.DESTINO_PARAM) String destino,
      @QueryParam(Constantes.AUTONOMIA_PARAM) double autonomia,
      @QueryParam(Constantes.VALOR_COMBUSTIVEL_PARAM) double combustivel) {
    CustoMinimoResponseJSON response = new CustoMinimoResponseJSON();
    try {
      if (combustivel == 0 ||
          autonomia == 0) {
        return Response.status(Status.BAD_REQUEST).build();
      }
      // Obtem o mapa pelo nome
      Mapa mapa = bo.obtemMapaPorNome(nomeMapa);
      CalculoMenorDistancia calculoMenorDistancia = new CalculoMenorDistancia(mapa);
      calculoMenorDistancia.execute(origem);
      // Obtem o caminho calculado
      LinkedList<Vertice> caminho = calculoMenorDistancia.getCaminho(destino);
      List<String> rota = new ArrayList<String>(caminho.size());
      for (Vertice vertice : caminho) {
        rota.add(vertice.getNome());
      }
      response.setRota((String[])rota.toArray(new String[0]));
      response.setCusto((combustivel / autonomia) * calculoMenorDistancia.getDistancia(destino));
      
    } catch (MapaNotFoundException ex) {
      // TODO: tratar erro
      Response.status(Status.NOT_FOUND).build();
    } catch (CalculoMenorDistanciaException ex) {
      Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.status(Status.OK).entity(response).build();
  }
}
