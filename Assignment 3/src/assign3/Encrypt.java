/*
    Name:       Ty Mabee
    Student #:  7453301
    Version:    2.0
 */
package assign3;

import BasicIO.*;
import secrecy.*;

public class Encrypt{
    //Variable Declaration
    ASCIIDataFile oFile;
    ASCIIOutputFile nFile;
    Node header = new Node(null, null);
    Node line = null;
    Transmorgifier offset;
    Transmorgifier chained;
    Transmorgifier poly;


    /**
     * Constructor for Encrypt
     */
    public Encrypt() {
        loadFile();
        offset = new TransmorgifierOffset();
        chained = new TransmorgifierChained();
        poly = new TransmorgifierPolySubstitution();

        for (int i = 0; i < 3; i++){
            writeNew(i);
        }

        System.out.println("Encryption finished");
        System.out.println("KEYS");
        System.out.println("");
        System.out.println("Offset Default Key: " + offset.getKey());
        System.out.println("Chained Default Key: " + chained.getKey());
        System.out.println("Poly Default Key: " + poly.getKey());
        System.out.println("");
        System.out.println("ANTI-KEYS");
        System.out.println("Note, the anti-key for poly will be the same as the key");
        System.out.println("Offset Default Anti-Key: " + offset.getAntiKey());
        System.out.println("Chained Default Anti-Key: " + chained.getAntiKey());
        System.out.println("Poly Default Anti-Key: " + poly.getAntiKey());
        System.exit(0);
    }//constructor;

    /**
     * Loads the file in and adds all the strings to a symetrically-linked list by calling on addLine
     */
    private void loadFile(){
        oFile = new ASCIIDataFile();
        while (!oFile.isEOF()){
            String sentence = oFile.readString();
            addLine(sentence);
        }
    }//loadFile;

    /**
     * Add's a string to a new node to the end of the list
     *
     * @param sentence  a sentence or string passed on to be added to the list
     */
    private void addLine(String sentence){
        Node node = line;
        if (sentence != null && !sentence.equals("")){
            if (header.next == null){
                line = new Node(sentence, null);
                header.next = line;
            }else{
                while (node != null && node.next != null){
                    node = node.next;
                }
                node.next = new Node(sentence, null);
            }
        }
    }//addLine;

    /**
     * Method used to simplify the constructor
     * Used in a loop to create the three encrypted .txt files
     *
     * @param type  int i in the loop, finds out which encryption method should be called on
     */
    private void writeNew(int type){
        line = header.next;
        switch (type){
            case 0:
                nFile = new ASCIIOutputFile("encryptedOff.txt");
                while (line.next != null){
                    try {
                        nFile.writeString(offset.mutate(line.item));
                        line = line.next;
                    }catch (InvalidCodePointException e){
                        System.err.println(e);
                    }
                }
                System.out.println("Offset done");
                break;
            case 1:
                nFile = new ASCIIOutputFile("encryptedPoly.txt");
                while (line.next != null){
                    try {
                        nFile.writeString(poly.mutate(line.item));
                        line = line.next;
                    }catch (InvalidCodePointException e){
                        System.err.println(e);
                    }
                }
                System.out.println("Poly done");
                break;
            case 2:
                nFile = new ASCIIOutputFile("encryptedChained.txt");
                while (line.next != null){
                    try {
                        nFile.writeString(chained.mutate(line.item));
                        line = line.next;
                    }catch (InvalidCodePointException e){
                        System.err.println(e);
                    }
                }
                System.out.println("Chained done");
                break;
            default:
                System.out.println("Type out of bounds, please input either 0, 1, or 2 for encryption");
                break;
        }
    } //writeNew;

    
    public static void main(String[] args) { Encrypt run = new Encrypt(); }
}