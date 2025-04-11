// TODO: Write your implementation to MultiCipher here!
import java.util.*;

public abstract class MultiCipher extends Cipher {
    private List<Character> swaps;
    private Map <Character, Character> m = new HashMap<>();

    public MultiCipher() {
        swaps =  new ArrayList<>();
    }

    public MultiCipher(List<Character> swaps) {
        if (swaps.size() < 2) {
            throw new IllegalArgumentException();
        } else {
            for (char currChar : swaps) {
                if (!isCharInRange(currChar)) {
                    throw new IllegalArgumentException("Attempting to swap character " +
                                                       "outside of Cipher range");
                }
            }
        }
        this.swaps = swaps;
    }

    public void setSwaps(List<Character> swaps) {
        if (swaps.size() < 2) {
            throw new IllegalArgumentException();
        } else {
            for (char currChar : swaps) {
                if (!isCharInRange(currChar)) {
                    throw new IllegalArgumentException("Attempting to swap character " +
                                                       "outside of Cipher range");
                }
            }
        }
        this.swaps = swaps;
    }

    //   Behavior: Applies this Cipher's encryption scheme to 'input', returning the result
    // Exceptions: None
    //    Returns: The result of applying this Cipher's encryption scheme to `input`
    // Parameters: 'input' - the string to be encrypted. Should be non-null and all characters of
    //             'input' should be within the encodable range.
    public abstract String encrypt(String input) {
        if (this.swaps == null) {
            throw new IllegalArgumentException();
        }
    }
    
    //   Behavior: Applies this inverse of this Cipher's encryption scheme to 'input' (reversing
    //             a single round of encryption if previously applied), returning the result
    // Exceptions: None
    //    Returns: The result of applying the inverse of this Cipher's encryption scheme to `input`
    // Parameters: 'input' - the string to be encrypted. Should be non-null and all characters of
    //             'input' should be within the encodable range.
    public abstract String decrypt(String input) {
        if (this.swaps == null) {
            throw new IllegalArgumentException();
        }
    }
}