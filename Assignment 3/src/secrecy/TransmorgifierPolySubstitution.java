/*
    Name:       Ty Mabee
    Student #:  7453301
    Version:    2.0
 */
package secrecy;

/**
 * Polysub implementation of Transmorgifier
 */
public class TransmorgifierPolySubstitution implements Transmorgifier {
    //Variable declaration
    private String key; //Key used for encryption

    /**
     * Main constructor
     * Sets default key to "LEMON"
     */
    public TransmorgifierPolySubstitution(){
        key = "LEMON";
    }//constructor;

    /**
     * Main encryption method
     * Checks to see if character is within ascii decimal range
     * Uses key to encrypt singular character using polysubstitution
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
            if (ascii >= 32 && ascii <= 126) {
                int index = key.indexOf(character);
                if (index != -1) {
                    int asciiToAdd = (int) key.charAt(index);
                    int newAscii = ascii + asciiToAdd;
                    if (newAscii > 126) {
                        newAscii = newAscii % 127 + 32;
                    }
                    return (char) newAscii;
                }
            }
        }
        return character;
    }//mutate;

    /**
     * Secondary encryption method that calls on mutate to encrypt an entire string
     * Uses stringbuilder to add each char to the string
     *
     * @param plainText plain text string to be encrypted
     * @return  encrypted string
     */
    public String mutate(String plainText) {
        plainText = plainText.toUpperCase();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            char eChar = mutate(c);
            word.append(eChar);
        }
        return word.toString();
    }//mutate;

    //ACCESSOR METHODS

    /**
     * Accesses the key
     * @return  key
     */
    public String getKey() { return key; }//getKey;

    /**
     * Returns an empty string, as there is no antikey
     * @return
     */
    public String getAntiKey() { return ""; }//getAntiKey;
}
