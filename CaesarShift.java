import java.util.*;

/* Name: Yonie Isaiah Rivera Aguilar
 * Date: 16 April 2025
 * Course: CSE 123
 * Instructor: Professor Brunelle
 * TA: Eeshani Shilamkar
 * This class represents a Caesar Shift cipher, a specific type of substitution cipher.
 * where each character in the alphabet is shifted by a fixed number of positions.
 * Extends the Substitution class and automatically generates the encoding based on the shift.
 */
public class CaesarShift extends Substitution {
    private int shift;


    /*
     * Behavior:
     *   Constructs a CaesarShift cipher with a given shift amount and sets a empty encoding.
     * Parameters:
     *   - shift: The number of positions each character will be shifted.
     * Exceptions:
     *   - IllegalArgumentException if shift is zero or negative
     */
    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException();
        }
        this.shift = shift;
        setEncoding("");
    }

    /*
     * Behavior:
     *   Overrides the setEncoding method to automatically generate a Caesar cipher encoding
     *   based on the current shift value.
     * Parameters:
     *   - encoding: Ignored input it is required by superclass signature
     */
    @Override
    public void setEncoding(String encoding) {
        Queue<Character> shiftEncoding = new LinkedList<>();

        for (int i = 0; i < TOTAL_CHARS; i++) {
            shiftEncoding.add((char) (MIN_CHAR + i));
        }

        for (int i = 0; i < shift; i++) {
            shiftEncoding.add(shiftEncoding.remove());
        }

        String newEncoding = "";
        while (!shiftEncoding.isEmpty()) {
            newEncoding += shiftEncoding.remove() + "";
        }
        super.setEncoding(newEncoding);
    }
}