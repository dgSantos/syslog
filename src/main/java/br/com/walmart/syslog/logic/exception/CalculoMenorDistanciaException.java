/**
 * 
 */
package br.com.walmart.syslog.logic.exception;

/**
 * @author Diego Santos
 * @since 19/08/2015
 *
 */
public class CalculoMenorDistanciaException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1185475101796094264L;

  /**
   * @param message
   */
  public CalculoMenorDistanciaException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public CalculoMenorDistanciaException(Throwable cause) {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public CalculoMenorDistanciaException(String message, Throwable cause) {
    super(message, cause);
  }

}
