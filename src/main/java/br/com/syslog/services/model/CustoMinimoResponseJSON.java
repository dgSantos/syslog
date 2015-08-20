package br.com.syslog.services.model;

public class CustoMinimoResponseJSON {
  
  private String[] rota;
  private double custo;
  
  public String[] getRota() {
    return rota;
  }
  
  public void setRota(String[] rota) {
    this.rota = rota;
  }
  
  public double getCusto() {
    return custo;
  }
  
  public void setCusto(double custo) {
    this.custo = custo;
  }

}
