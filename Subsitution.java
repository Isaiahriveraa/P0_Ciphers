import java.util.*;
// TODO: Write your implementation to Subsitution here!
public abstract class Subsitution extends Cipher {
    private String encoding;

    public Subsitution(String encoding) {
        checkIfValidChars(encoding);
        this.encoding = encoding;
    }

    public Subsitution() {
        encoding = "";
    }

    public void setEncoding(String encoding) {
        checkIfValidChars(encoding);
        this.encoding = encoding;
    }

    //   Behavior: Applies this inverse of this Cipher's encryption scheme to 'input' (reversing
    //             a single round of encryption if previously applied), returning the result
    // Exceptions: None
    //    Returns: The result of applying the inverse of this Cipher's encryption scheme to `input`
    // Parameters: 'input' - the string to be encrypted. Should be non-null and all characters of
    //             'input' should be within the encodable range.
    public abstract String decrypt(String input) {

    }

        //   Behavior: Applies this Cipher's encryption scheme to 'input', returning the result
    // Exceptions: None
    //    Returns: The result of applying this Cipher's encryption scheme to `input`
    // Parameters: 'input' - the string to be encrypted. Should be non-null and all characters of
    //             'input' should be within the encodable range.
    public abstract String encrypt(String input) {

    }

    public void checkIfValidChars(String encoding) {
        if (encoding == null || encoding.length() > TOTAL_CHARS) {
            throw new IllegalArgumentException();
        }

        Set<Character> uniqueCharacters = new HashSet<>();

        for (int i = 0; i < encoding.length(); i++) {
            Character checkifDupeCharacter = encoding.charAt(i);

            if (!isCharInRange(encoding.charAt(i))) {
                throw new IllegalArgumentException("Attempting to swap character"
                    + " outside of Cipher range");
            }

            //if it doesnt add then there is a duplicate char
            if (!uniqueCharacters.add(checkifDupeCharacter)) {
                throw new IllegalArgumentException("There is a Duplicate Character" 
                    + " in the encoding String");
            }
        }
    }
}