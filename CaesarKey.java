// TODO: Write your implementation to CaesarKey here!

import java.util.*;

public class CaesarKey extends Substitution {
    private String key;

    public CaesarKey(String key) {
        // FACE is the key passed in
        checkValidEncoding(key);
        if (key.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.key = key;
        setupEncoding();
    }

    public void setupEncoding() {
        String encoding = key;
        for (int i = 0; i < TOTAL_CHARS; i++) {
            String currChar = (char) (MIN_CHAR + i) + "";
            if (!encoding.contains(currChar)) {
                encoding += currChar;
            }
        }
        setEncoding(encoding);
    }

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