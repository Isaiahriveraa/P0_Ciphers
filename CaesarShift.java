import java.util.*;

// TODO: Write your implementation to CaesarShift here!
public class CaesarShift extends Substitution {
    private int shift;

    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException();
        }
        this.shift = shift;
    }

    @Override
    public String encrypt(String input) {
        // if (getEncoding().isEmpty()) {
        // throw new IllegalArgumentException("Encoding has not been set");
        // }
        checkValidInput(input);
        String encrypted = "";

        for (int i = 0; i < input.length(); i++) {
            int currShift = (int) input.charAt(i);
            for (int j = 0; j < shift; j++) {
                if (currShift >= MAX_CHAR) {
                    currShift = MIN_CHAR - 1;
                }
                currShift++;
            }
            encrypted += (char) (currShift) + "";
        }
        return encrypted;
    }

    @Override
    public String decrypt(String input) {
        checkValidInput(input);
        String decrypted = "";

        for (int i = 0; i < input.length(); i++) {
            int currShift = (int) input.charAt(i);
            for (int j = 0; j < shift; j++) {
                if (currShift <= MIN_CHAR) {
                    currShift = MAX_CHAR + 1;
                }
                currShift--;
            }
            decrypted += (char) (currShift) + "";
        }
        return decrypted;
    }
}