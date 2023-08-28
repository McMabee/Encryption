/*
    Name:       Ty Mabee
    Student #:  7453301
    Version:    2.0
 */
package secrecy;

import java.util.Random;

/**
 * Chained implementation of Transmorgifier
 */
public class TransmorgifierChained implements Transmorgifier{
    //Variable Declaration
    private Random mixer; //Random number used for encryption
    private long key; //Key used for encryption

    /**
     * Main constructor
     * Sets default key to 12345
     */
    public TransmorgifierChained(){ key = 12345; }//constructor;

    /**
     * Main encryption method
     * Checks to see if character is within ascii decimal range
     * Uses random number and key to encrypt singular character using the seeded random number
     * generator method
     *
     * @param character plain text char passed on to be encrypted
     * @return  encrypted char
     */
    public char mutate(char character) {
        character = Character.toUpperCase(character);
        int ascii = (int)character;
        if (ascii < 0 || ascii > 127){
            throw new InvalidCodePointException("The inputted character is outside of the standard ASCII decimal range.");
        }
        mixer = new Random(key);
        int rInt = mixer.nextInt(128);
        int newAscii = rInt ^ (int) character;
        return (char) newAscii;
    }//mutate;

    /**
     * Secondary encryption method that calls on mutate to encrypt an entire string
     * Uses StringBuilder to add the char to the string
     *
     * @param plainText plain text string to be encrypted
     * @return  encrypted string
     */

    public String mutate(String plainText) {
        plainText = plainText.toUpperCase();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++){
            word.append(mutate(plainText.charAt(i)));
        }
        return word.toString();
    }//mutate;

    //ACCESSOR METHODS

    /**
     * Accesses the key
     * @return  key in string form
     */
    public String getKey() { return Long.toString(key); }//getKey;

    /**
     * Generates and returns anti key
     * @return  opposite of key in string form
     */
    public String getAntiKey() { return Long.toString(-key); }//getAntiKey;
}
