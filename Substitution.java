import java.util.*;

/* Name: Yonie Isaiah Rivera Aguilar
 * Date: 16 April 2025
 * Course: CSE 123
 * Instructor: Professor Brunelle
 * TA: Eeshani Shilamkar
 * This class represents a Substitution cipher, where each character in a message
 * is replaced with a corresponding character defined by a encoding string.
 * Clients can encrypt and decrypt messages using the provided encoding.
 */
public class Substitution extends Cipher {
    private String encoding;
    private Map<Character, Character> encryptMap;
    private Map<Character, Character> decryptMap;

     /*
     * Behavior:
     *   Constructs a Substitution cipher using the given encoding string.
     *   Initializes encryption and decryption maps so that each character in the cipher
     *   maps to the corresponding character in the encoding string.
     * Parameters:
     *   - encoding: A String of characters in the cipher's character range.
     * Exceptions:
     *   - IllegalArgumentException if the encoding is invalid (null, not the same length as 
     *     TOTAL_CHARS, or encoding contains duplicates/out of range characters).
     */
    public Substitution(String encoding) {
        setEncoding(encoding);
        setupMaps();
    }

    /*
     * Behavior:
     *   Constructs a Substitution cipher with empty encoding.
     */
    public Substitution() {
        encoding = "";
    }

    /*
     * Behavior:
     *   Updates the encoding, sets up the maps to adjust to that encoding for the encrypting and
     *   decrypting methods.
     * Parameters:
     *   - encoding: A String of characters in the cipher's character range.
     * Exceptions:
     *   - IllegalArgumentException if the encoding is invalid (null, not the same length as 
     *     TOTAL_CHARS, or encoding contains duplicates/out of range characters).
     */
    public void setEncoding(String encoding) {
        checkValidEncoding(encoding);
        this.encoding = encoding;
        setupMaps();

    }

    /*
     * Behavior:
     *   Decrypts the given input string using the current encoding.
     * Parameters:
     *   - input: The encrypted string to be decrypted.
     * Returns:
     *   - The original plaintext string.
     * Exceptions:
     *   - IllegalStateException if encoding has not been set.
     *   - IllegalArgumentException if input is null or contains invalid characters.
     */
    public String decrypt(String input) {
        if (encoding.isEmpty()) {
            throw new IllegalStateException("Encoding has not been set");
        }
        checkValidInput(input);

        String decryptedString = "";
        for (int i = 0; i < input.length(); i++) {
            decryptedString += decryptMap.get(input.charAt(i));
        }
        return decryptedString;
    }

    /*
     * Behavior:
     *   Encrypts the given input string using the current encoding.
     * Parameters:
     *   - input: The original plaintext string to encrypt.
     * Returns:
     *   - The encrypted version of the input.
     * Exceptions:
     *   - IllegalStateException if encoding has not been set.
     *   - IllegalArgumentException if input is null or contains invalid characters.
     */
    public String encrypt(String input) {
        if (encoding.isEmpty()) {
            throw new IllegalStateException("Encoding has not been set");
        }
        checkValidInput(input);

        String encryptedString = "";
        for (int i = 0; i < input.length(); i++) {
            encryptedString += encryptMap.get(input.charAt(i));
        }
        return encryptedString;
    }

    /*
     * Behavior:
     *   Initializes the encryption and decryption maps based on the current encoding. 
     *   Maps each character in range to its corresponding encoded character.
     *   Builds inverse mapping for decryption.
     */
    public void setupMaps() {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();

        for (int i = 0; i < TOTAL_CHARS; i++) {
            char originalChar = (char) (MIN_CHAR + i);
            char encodedChar = encoding.charAt(i);

            //encryption: original -> encoded
            encryptMap.put(originalChar, encodedChar);

            //decryption: encoded -> original
            decryptMap.put(encodedChar, originalChar);
        }
    }


    /*
     * Behavior:
     *   Validates the encoding string to make sure it's usable for the cipher.
     * Parameters:
     *   - encoding: The encoding string to check (validate).
     * Exceptions:
     *   - IllegalArgumentException if encoding is null, has incorrect length, contains
     *     out of range or duplicate characters.
     */
    public void checkValidEncoding(String encoding) {
        if (encoding == null) {
            throw new IllegalArgumentException("Encoding is null, not valid encoding.");
        }

        if (encoding.length() != TOTAL_CHARS) {
            throw new IllegalArgumentException("Encoding length is not valid, must match the"
                    + "number of characters in the cipher range");
        }

        Set<Character> uniqueCharacters = new HashSet<>();

        for (int i = 0; i < encoding.length(); i++) {
            char currentChar = encoding.charAt(i);

            if (!isCharInRange(currentChar)) {
                throw new IllegalArgumentException("Attempting to swap character outside of Cipher range");
            }

            if (!uniqueCharacters.add(currentChar)) {
                throw new IllegalArgumentException("There is a duplicate character in the encoding string");
            }
        }
    }
    
    /*
     * Behavior:
     *   Validates that a given input string contains only valid characters for the cipher.
     * Parameters:
     *   - input: The input string to validate.
     * Exceptions:
     *   - IllegalArgumentException if input is null or contains out of range characters.
     */
    public void checkValidInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        for (int i = 0; i < input.length(); i++) {
            if (!isCharInRange(input.charAt(i))) {
                throw new IllegalArgumentException("Input contains character outside of Cipher range");
            }
        }
    }
}