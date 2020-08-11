import java.util.Arrays;

public final class Game {
    
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

    public final void sortBySuit() {
        Card[] sorted = new Card[5];
        for( int i = 0; i < 5; i++ ) {
            for( int j = 0; j < 5; j++ ) {
                if( sorted[j] != null && sorted[j].getSuit() > hand[i].getSuit() ) {
                    for( int k = 4; k > j; k-- ) {
                        sorted[k] = sorted[k-1];
                    }
                    sorted[j+1] = sorted[j];
                    sorted[j] = hand[i];
                    break;
                }
                else if( sorted[j] == null ) {
                    sorted[j] = hand[i];
                    break;
                }
            }
        }
        hand = sorted;
    }

    public final void sortByValue() {
        // Card[] sorted = new Card[5];
        // for( int i = 0; i < 5; i++ ) {
        //     for( int j = 0; j < 5; j++ ) {
        //         if( sorted[j] != null && sorted[j].getValue() > hand[i].getValue() ) {
        //             for( int k = 4; k > j; k-- ) {
        //                 sorted[k] = sorted[k-1];
        //             }
        //             sorted[j+1] = sorted[j];
        //             sorted[j] = hand[i];
        //             break;
        //         }
        //         else if( sorted[j] == null ) {
        //             sorted[j] = hand[i];
        //             break;
        //         }
        //     }
        // }
        // hand = sorted;
        Arrays.sort(hand, (a, b) -> (a.getValue() - b.getValue()));
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
