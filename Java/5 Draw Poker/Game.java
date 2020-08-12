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

    private final boolean allSameSuit() {
        return hand[0].getSuit() == hand[4].getSuit();
    }

    private final boolean areConsecutive() {
        for( int i = 0; i < 4; i++ )
            if( hand[i].getValue() != hand[i+1].getValue() + 1 )
                return false;
        return true;
    }

    private final boolean areRoyalOrder() {
        return hand[0].getValue() == 1
            && hand[1].getValue() == 10
            && hand[2].getValue() == 11
            && hand[3].getValue() == 12
            && hand[4].getValue() == 13;
    }

    private final boolean isFourOfAKind() {
        for( int i = 0; i < 2; i++ )
            if( hand[i].getValue() == hand[i+1].getValue()
             && hand[i].getValue() == hand[i+2].getValue()
             && hand[i].getValue() == hand[i+3].getValue() )
                return true;
        return false;
    }

    private final boolean isFullHouse() {
        if( hand[0].getValue() == hand[1].getValue()
         && hand[0].getValue() == hand[2].getValue()
         && hand[3].getValue() == hand[4].getValue() )
            return true;
        if( hand[0].getValue() == hand[1].getValue()
         && hand[2].getValue() == hand[3].getValue()
         && hand[2].getValue() == hand[4].getValue() )
            return true;
        return false;
    }

    private final boolean isThreeOfAKind() {
        for( int i = 0; i < 3; i++ )
            if( hand[i].getValue() == hand[i+1].getValue()
             && hand[i].getValue() == hand[i+2].getValue() )
                return true;
        return false;
    }

    private final boolean isTwoPair() {
        if( hand[0].getValue() == hand[1].getValue()
         && hand[2].getValue() == hand[3].getValue() )
            return true;
        if( hand[0].getValue() == hand[1].getValue()
         && hand[3].getValue() == hand[4].getValue() )
            return true;
        if( hand[1].getValue() == hand[2].getValue()
         && hand[3].getValue() == hand[4].getValue() )
            return true;
        return false;
    }

    private final boolean isTwoOfAKind() {
        for( int i = 0; i < 4; i++ )
            if( hand[i].getValue() == hand[i+1].getValue() )
                return true;
        return false;
    }

    private final int getWinState() {
        this.sort( bySuit );
        this.sort( byValue );

        //Royal Flush and Straight Flush
        if( allSameSuit() )
            if( areRoyalOrder() )
                return 0;
            else if( areConsecutive() )
                return 1;

        //Four of a Kind
        if( isFourOfAKind() )
            return 2;
        
        //Full House
        if( isFullHouse() )
            return 3;

        //Flush
        if( allSameSuit() )
            return 4;

        //Straight
        if( areConsecutive() )
            return 5;

        //Three of a Kind
        if( isThreeOfAKind() )
            return 6;
        
        //Two Pair
        if( isTwoPair() )
            return 7;

        //Two of a Kind
        if( isTwoOfAKind() )
            return 8;
        
        //Crap Hand
        return 9;
    }

    public final int calculateWinMult() {
        switch( getWinState() ) {
            case 0:
                return 50;
            case 1:
                return 30;
            case 2:
                return 15;
            case 3:
                return 10;
            case 4:
                return 7;
            case 5:
                return 5;
            case 6:
                return 3;
            case 7:
                return 2;
            case 8:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public final String toString() {
        String tmp = "";
        for( int i = 0; i < 5; i++ )
            tmp += hand[i] + ((i==4)?"":"\n");
        return tmp;
    }
}
