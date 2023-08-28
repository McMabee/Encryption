/*
    Name:       Ty Mabee
    Student #:  7453301
    Version:    2.0
 */
package secrecy;

/**
 * Main interface
 * Contains the encryption methods and the accessor methods
 */
public interface Transmorgifier {
     char mutate(char character);
     String mutate(String plainText);
     String getKey();
     String getAntiKey();
}//interface;
