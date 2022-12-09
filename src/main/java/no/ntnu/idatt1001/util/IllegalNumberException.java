package no.ntnu.idatt1001.util;

/**
 * Thrown to indicate that a method has been passed an illegal or
 * inappropriate number.
 *
 * @author Daniel Ireneo Neri Saren
 * @version 1.0.0
 */
public class IllegalNumberException extends IllegalArgumentException {
  /**
   * Constructs an {@code IllegalNumberException} with no
   * detail message.
   */
  public IllegalNumberException() {
    super();
  }

  /**
   * Constructs an {@code IllegalNumberException} with the
   * specified detail message.
   *
   * @param   s   the detail message.
   */
  public IllegalNumberException(String s) {
    super(s);
  }


}
