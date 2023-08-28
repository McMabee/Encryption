/*
    Name:       Ty Mabee
    Student #:  7453301
    Version:    2.0
 */
package secrecy;

/**
 * Offset implementation of Transmorgifier
 */
public class TransmorgifierOffset implements Transmorgifier{
    //Variable declaration
    private int key; //Key used for encryption

    /**
     * Main constructor
     * Sets default key to 1
     */
    public TransmorgifierOffset(){
        key = 1;
    } //constructor;

    /**
     * Main encryption method
     * Checks to see if character is within ascii decimal range
     * Uses key to add one to the characters decimal value
     *
     * @param character plain text char to be encrypted
     * @return  encrypted char
     */
    public char mutate(char character) {
        character = Character.toUpperCase(character);
        int ascii = (int)character;
        if (ascii < 0 || ascii > 127){
            throw new InvalidCodePointException("The inputted character is outside of the standard ASCII decimal range.");
        }else{
            int newAscii = ascii + key;
            return (char)newAscii;
        }
    }//mutate;

    /**
     * Secondary encryption method that calls on mutate to encrypt and entire string
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
    public String getKey() {
        return Integer.toString(key);
    } //getKey;

    /**
     * Generates and returns the anti key
     * @return  opposite of key in string form
     */
    public String getAntiKey() {
        return Integer.toString(-key);
    } //getAnitKey;
}
