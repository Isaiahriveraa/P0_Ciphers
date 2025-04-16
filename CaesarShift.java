import java.util.*;
public class CaesarShift extends Substitution {
    private int shift;

    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException();
        }
        this.shift = shift;
        setEncoding("");
    }

    @Override
    public void setEncoding(String encoding) {
        Queue<Character> shiftEncoding = new LinkedList<>();

        for (int i = 0; i < TOTAL_CHARS; i++) {
            shiftEncoding.add((char) (MIN_CHAR + i));
        }

        for (int i = 0; i < shift; i++) {
            shiftEncoding.add(shiftEncoding.remove());
        }

        String newEncoding = "";
        while (!shiftEncoding.isEmpty()) {
            newEncoding += shiftEncoding.remove() + "";
        }
        super.setEncoding(newEncoding);
    }
}