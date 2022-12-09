package no.ntnu.idatt1001.common;

/**
 * A functional interface for receiving input from
 * the user. This interface can be used for when wanting
 * to process user input for live feedback applications.
 *
 * @author Daniel Ireneo Neri Saren
 * @version 1.0.0
 */
@FunctionalInterface
public interface UserInput {
  /**
   * Method for receiving input from the user.
   *
   * @return String containing the user input
   */
  String input();
}
