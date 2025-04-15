
// TODO: Write your implementation to MultiCipher here!
import java.util.*;

public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException();
        }
        this.ciphers = new ArrayList<>();
        this.ciphers = ciphers;
    }

    @Override
    public String encrypt(String input) {
        for (Cipher currCipher : ciphers) {
            input = currCipher.encrypt(input);
        }
        return input;
    }

    @Override
    public String decrypt(String input) {
        for (int currCipher = ciphers.size() - 1; currCipher >= 0; currCipher--) {
            input = ciphers.get(currCipher).decrypt(input);
        }
        return input;
    }
}