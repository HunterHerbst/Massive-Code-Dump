import java.util.Arrays;
import java.util.Comparator;

public final class Game {
    private enum WinState {
        RoyalFlush, StraightFlush, FourKind, FullHouse, Flush, Straight, ThreeKind, TwoPair, TwoKind, Crap
    }
    public static final Comparator<Card> bySuit = (a,b) -> (a.getSuit() - b.getSuit());
    public static final Comparator<Card> byValue = (a,b) -> (a.getValue() - b.getValue());

    private final Deck d;
    private Card[] hand;

    public Game() {
        d = new Deck();
        d.fill();
        hand = new Card[5];
    }

    public final Card[] getHand() {
        return hand;
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

    public final void discard( final boolean[] cardsToDiscard ) {
        for( int i = 0; i < 5; i++ )
            if( cardsToDiscard[i] )
                hand[i] = null;
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

    private final WinState getWinState() {
        this.sort( bySuit );
        this.sort( byValue );

        //Royal Flush and Straight Flush
        if( allSameSuit() )
            if( areRoyalOrder() )
                return WinState.RoyalFlush;
            else if( areConsecutive() )
                return WinState.StraightFlush;

        //Four of a Kind
        if( isFourOfAKind() )
            return WinState.FourKind;
        
        //Full House
        if( isFullHouse() )
            return WinState.FullHouse;

        //Flush
        if( allSameSuit() )
            return WinState.Flush;

        //Straight
        if( areConsecutive() )
            return WinState.Straight;

        //Three of a Kind
        if( isThreeOfAKind() )
            return WinState.ThreeKind;
        
        //Two Pair
        if( isTwoPair() )
            return WinState.TwoPair;

        //Two of a Kind
        if( isTwoOfAKind() )
            return WinState.TwoKind;
        
        //Crap Hand
        return WinState.Crap;
    }

    public final int calculateWinMult() {
        switch( getWinState() ) {
            case RoyalFlush:
                return 50;
            case StraightFlush:
                return 30;
            case FourKind:
                return 15;
            case FullHouse:
                return 10;
            case Flush:
                return 7;
            case Straight:
                return 5;
            case ThreeKind:
                return 3;
            case TwoPair:
                return 2;
            case TwoKind:
                return 1;
            case Crap:
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
