import java.io.Serializable;

public class Fraction implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int numerator, denominator;

    public Fraction( int numerator, int denominator ) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public void setNumerator( int numerator ){ this.numerator = numerator; }
    public void setDenominator( int denominator ){ this.denominator = denominator; }
    public int getNumerator(){ return numerator; }
    public int getDenominator(){ return denominator; }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
