import java.util.Arrays;
import java.util.Comparator;

public final class Game {
    
    public static final Comparator<Card> bySuit = (a,b) -> (a.getSuit() - b.getSuit());
    public static final Comparator<Card> byValue = (a,b) -> (a.getValue() - b.getValue());

    private final Deck d;
    private Card[] hand;

    public Game() {
        d = new Deck();
        d.fill();
        hand = new Card[5];
    }

    public final void deal() {
        for( int i = 0; i < 5; i++ )
            if( hand[i] == null )
                hand[i] = d.draw();
    }

    public final void discard( final int index ) {
        if( index > -1 && index < 5 )
            hand[index] = null;
    }

    public final void sort( Comparator<Card> comparator ) {
        Arrays.sort( hand, comparator );
    }

    private final int getWinState() {
        return 0;
    }

    public final int calculateWinMult() {
        return 0;
    }

    @Override
    public final String toString() {
        String tmp = "";
        for( int i = 0; i < 5; i++ )
            tmp += hand[i] + ((i==4)?"":"\n");
        return tmp;
    }
}
