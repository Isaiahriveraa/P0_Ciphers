import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assume.assumeTrue;
import java.util.*;
/* Name: Yonie Isaiah Rivera Aguilar
 * Date: 16 April 2025
 * Course: CSE 123
 * Instructor: Professor Brunelle
 * TA: Eeshani Shilamkar
 * 
 * This class contains JUnit tests for different Cipher implementations,
 * including Substitution, CaesarShift, CaesarKey, and MultiCipher.
 * These tests validate correct encryption, decryption, and exception behavior
 * under different character ranges. 
 */
public class Testing {

    @Test
    @DisplayName("EXAMPLE TEST CASE - 'A'-'G' Spec Example")
    public void subAGTest() {
        // Remember that you can change MIN_CHAR AND MAX_CHAR
        // in Cipher.java to make testing easier! For this
        // example test, we are using MIN_CHAR = A and MAX_CHAR = G

        // If this is false (i.e. the constants are not 'A'-'G'), the test will halt and
        // be ignored (aborted). This doesn't mean that the code is wrong! It just means
        // that
        // the test won't produce any meaningful information if the assumption is not
        // met.
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('G'));

        Cipher testSubstitution = new Substitution("BDFGACE");
        assertEquals("ACE", testSubstitution.encrypt("EFG"), "EFK should encrypt to ACE");
        assertEquals("EFG", testSubstitution.decrypt("ACE"), "ACE should decrypt to EFG");

        // Per the spec, we should throw an IllegalArgumentException if
        // the length of the shifter doesn't match the number of characters
        // within our Cipher's encodable range
        assertThrows(IllegalArgumentException.class, () -> {
            new Substitution("FRT");
        }, "Should throw an exception for invalid length for mapping.");
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - 'A'-'Z' Shifter")
    public void subAZTest() {
        // If this is false (i.e. the constants are not 'A'-'Z'), the test will halt and
        // be ignored (aborted). This doesn't mean that the code is wrong! It just means
        // that
        // the test won't produce any meaningful information if the assumption is not
        // met.
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // Reverse alphabetic
        Cipher testSubstitution = new Substitution(
                "ZYXWVUTSRQPONMLKJIHGFEDCBA");
        assertEquals("ZAY", testSubstitution.encrypt("AZB"));
        assertEquals("AZB", testSubstitution.decrypt("ZAY"));
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - ' '-'}' Shifter")
    public void subComplexTest() {
        // If this is false (i.e. the constants are not ' '-'}'), the test will halt and
        // be ignored (aborted). This doesn't mean that the code is wrong! It just means
        // that
        // the test won't produce any meaningful information if the assumption is not
        // met.
        assumeTrue(Cipher.MIN_CHAR == (int) (' ') && Cipher.MAX_CHAR == (int) ('}'));

        // Swapping lowercase a<->b
        Cipher testSubstitution = new Substitution(
                " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`" +
                        "bacdefghijklmnopqrstuvwxyz{|}");
        assertEquals("FAD", testSubstitution.encrypt("FAD"));
        assertEquals("fbd", testSubstitution.encrypt("fad"));
        assertEquals("BAD", testSubstitution.decrypt("BAD"));
        assertEquals("bad", testSubstitution.decrypt("abd"));
    }

    @Test
    @DisplayName("TODO: CaesarKey - 'A'-'Z'")
    public void keyAZOne() {
        // If this is false (i.e. the constants are not 'A'-'Z'), the test will halt and
        // be ignored (aborted). This doesn't mean that the code is wrong! It just means
        // that
        // the test won't produce any meaningful information if the assumption is not
        // met.
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        Cipher testKey = new CaesarKey("TIN");
        String messageEncrypted = testKey.encrypt("NIGHT");
        
        // result's accurate. Then, take the encrypted message, decrypt it, and
        // check the result's accurate
        assertEquals("LFDES", messageEncrypted, "Assertion 1 not yet implemented!");
        assertEquals("NIGHT", testKey.decrypt(messageEncrypted), "Assertion 2 not yet implemented!");
    }

    @Test
    @DisplayName("TODO: CaesarShift - 'A'-'Z' Shifter")
    public void shiftAZOne() {
        // If this is false (i.e. the constants are not 'A'-'Z'), the test will halt and
        // be ignored (aborted). This doesn't mean that the code is wrong! It just means
        // that
        // the test won't produce any meaningful information if the assumption is not
        // met.
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // TODO: Create a new CaesarShift(6), encrypt the message "HELLO" and check the
        // result's accurate. Then, take the encrypted message, decrypt it, and
        // check the result's accurate
        Substitution shift = new CaesarShift(6);
        String encrypted = shift.encrypt("HELLO");
        

        assertEquals("NKRRU", encrypted,"Assertion 1 not yet implemented!");
        assertEquals("HELLO", shift.decrypt(encrypted), "Assertion 2 not yet implemented!");
    }

    @Test
    @DisplayName("TODO: MultiCipher - 'A'-'Z' Shifter")
    public void multiAZOne() {
        // If this is false (i.e. the constants are not 'A'-'Z'), the test will halt and
        // be ignored (aborted). This doesn't mean that the code is wrong! It just means
        // that
        // the test won't produce any meaningful information if the assumption is not
        // met.
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // TODO: Create a new MultiCipher with ciphers CaesarKey("TIN") and
        // CaesarShift(6)),
        List<Cipher> ciphers = new ArrayList<>();
        ciphers.add(new CaesarKey("TIN"));
        ciphers.add(new CaesarShift(6));
        MultiCipher multi = new MultiCipher(ciphers);
        String encrypted = multi.encrypt("HELLO");

        // encrypt the message "HELLO", and check the result's accurate. Then, take
        // the encrypted message, decrypt it, and check the result's accurate
        assertEquals("KHPPS", encrypted, "Assertion 1 not yet implemented!");
        assertEquals("HELLO", multi.decrypt(encrypted), "Assertion 2 not yet implemented!");
    }
}
