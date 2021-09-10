package java.lang;

public class String {
    private final char[] chars;

    public String(char[] chars) {
        this.chars = chars;
    }

    public int length(){
        return chars.length;
    }

    public char[] toCharArray(){
        return chars;
    }
}
