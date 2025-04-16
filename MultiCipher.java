import java.util.*;

/* Name: Yonie Isaiah Rivera Aguilar
 * Date: 16 April 2025
 * Course: CSE 123
 * Instructor: Professor Brunelle
 * TA: Eeshani Shilamkar
 * This class represents a MultiCipher, which en/decrypts a sequence of individual Cipher objects.
 * The MultiCipher encrypts and decrypts input by applying each cipher in order (for encryption)
 * and in reverse order (for decryption).
 */
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    /*
     * Behavior:
     *   Constructs a MultiCipher using a list of Cipher objects.
     *   The list represents the order in which encryption and decryption will occur.
     * Parameters:
     *   - ciphers: A list of Cipher objects to apply
     * Exceptions:
     *   - IllegalArgumentException if the list is null
     */
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException();
        }
        this.ciphers = new ArrayList<>();
        this.ciphers = ciphers;
    }

     /*
     * Behavior:
     *   Encrypts the input by applying each Cipher in the list in order.
     * Parameters:
     *   - input: The string to encrypt
     * Returns:
     *   - A word that is encrypted as much times as the size of ciphers list.
     */
    @Override
    public String encrypt(String input) {
        for (Cipher currCipher : ciphers) {
            input = currCipher.encrypt(input);
        }
        return input;
    }

        /*
     * Behavior:
     *   Decrypts the input by applying each Cipher in reverse order.
     * Parameters:
     *   - input: The string to decrypt
     * Returns:
     *   - A word that is decrypted as much times as the size of ciphers list.
     */
    @Override
    public String decrypt(String input) {
        for (int currCipher = ciphers.size() - 1; currCipher >= 0; currCipher--) {
            input = ciphers.get(currCipher).decrypt(input);
        }
        return input;
    }
}