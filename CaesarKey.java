import java.util.*;

/* Name: Yonie Isaiah Rivera Aguilar
 * Date: 16 April 2025
 * Course: CSE 123
 * Instructor: Professor Brunelle
 * TA: Eeshani Shilamkar
 * This class represents a Caesar Key cipher, a subclass of the substitution cipher.
 * The encoding is generated using a provided key, followed by the remaining characters
 * in the cipher range that were not already included in the key.
 * The key must consist of unique, valid characters.
 */
public class CaesarKey extends Substitution {
    private String key;

    /*
     * Behavior:
     *   Constructs a CaesarKey cipher using the given key.
     *   The key forms the beginning of the encoding and remaining characters 
     *   are added to encoding to make it the same length as the Total characters available.
     * Parameters:
     *   - key: A strung of unique characters to start the encoding.
     * Exceptions:
     *   - IllegalArgumentException if the key is null, empty, or contains invalid/duplicate characters
     */
    public CaesarKey(String key) {
        checkValidEncoding(key);
        if (key.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.key = key;
        setEncoding();
    }

    /*
     * Behavior:
     *   Builds the encoding string using the given key followed by any unused valid characters,
     *   then sets the encoding for this cipher using the substitution class's method.
     */
    public void setEncoding() {
        String encoding = key;
        for (int i = 0; i < TOTAL_CHARS; i++) {
            String currChar = (char) (MIN_CHAR + i) + "";
            if (!encoding.contains(currChar)) {
                encoding += currChar;
            }
        }
        super.setEncoding(encoding);
    }

    /*
     * Behavior:
     *   Validates that the provided encoding or key is composed of only unique,
     *   valid characters in the cipher range.
     * Parameters:
     *   - encoding: The key string to validate.
     * Exceptions:
     *   - IllegalArgumentException if encoding is null, contains duplicates, or includes
     *     characters outside the valid cipher range
     * Notes:
     *   - This method overrides the parent class to allow reuse of validation logic for keys
     */
    @Override
    public void checkValidEncoding(String encoding) {
        if (encoding == null) {
            throw new IllegalArgumentException("Key is null, not valid key.");
        }

        Set<Character> uniqueCharacters = new HashSet<>();

        for (int i = 0; i < encoding.length(); i++) {
            char currentChar = encoding.charAt(i);

            if (!isCharInRange(currentChar)) {
                throw new IllegalArgumentException("Attempting to swap character outside of Cipher range");
            }

            if (!uniqueCharacters.add(currentChar)) {
                throw new IllegalArgumentException("There is a duplicate character in the Key");
            }
        }
    }
}