public class TestApp {
    
    public static void main( String[] args ) {
        Fraction f = (Fraction) ObjectSerializer.readObjectFromFile("fract1.obj");
        System.out.println(f);
    }
}