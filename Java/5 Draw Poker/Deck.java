import java.util.ArrayList;
import java.util.Random;

public final class Deck {
    
    private static final Random RNG = new Random();
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
    }

    public final void fill() {
        for( int i = 1; i <= 52; i++ )
            cards.add( new Card( i % 13 + 1, i % 4 ) );
    }

    public final Card draw() {
        return cards.remove( RNG.nextInt( cards.size() ) );
    }

    @Override
    public final String toString()
    {
        return cards.toString();
    }
}
