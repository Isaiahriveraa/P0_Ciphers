import java.util.*;

// TODO: Write your implementation to Subsitution here!
public class Substitution extends Cipher {
    private String encoding;
    private Map<Character, Character> encryptMap;
    private Map<Character, Character> decryptMap;

    public Substitution(String encoding) {
        checkValidEncoding(encoding);
        this.encoding = encoding;
        setupMaps();
    }

    public Substitution() {
        encoding = "";
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();
    }

    public void setEncoding(String encoding) {
        checkValidEncoding(encoding);
        this.encoding = encoding;
        setupMaps();
    }

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

    public String encrypt(String input) {
        if (encoding == null) {
            throw new IllegalArgumentException("Encoding is null, not valid encoding.");
        }

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

    public void setupMaps() {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();

        for (int i = 0; i < TOTAL_CHARS; i++) {
            char originalChar = (char) (MIN_CHAR + i);
            char encodedChar = encoding.charAt(i);

            // encryption: original -> encoded
            encryptMap.put(originalChar, encodedChar);

            // decryption: encoded -> original
            decryptMap.put(encodedChar, originalChar);
        }
    }

    public void checkValidEncoding(String encoding) {
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

    public String getEncoding() {
        return encoding;
    }
}