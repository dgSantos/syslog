package br.com.syslog.dao.exception;

public class MapaNotFoundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1404955410903235847L;

  public MapaNotFoundException(String arg0) {
    super(arg0);
  }

  public MapaNotFoundException(Throwable arg0) {
    super(arg0);
  }

  public MapaNotFoundException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public MapaNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}
