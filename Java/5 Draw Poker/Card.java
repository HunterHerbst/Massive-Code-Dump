public final class Card {
    public static final int SPADES = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;

    private final int value, suit;

    public Card( final int value, final int suit )
    {
        this.value = value;
        this.suit = suit;
    }

    public final int getValue(){ return value; }
    public final int getSuit() { return suit; }

    private final String getValueString() {
        switch( value ) {
            case 1:
                return "Ace";
            case 10:
                return "Jack";
            case 11:
                return "Queen";
            case 12:
                return "King";
            default:
                return String.valueOf( value );
        }
    }

    private final String getSuitString() {
        switch( suit ) {
            case 0:
                return "Spades";
            case 1:
                return "Hearts";
            case 2:
                return "Clubs";
            case 3:
                return "Diamonds";
            default:
                return "Non-Suit";
        }
    }

    @Override
    public final String toString() {
        return getValueString() + " of " + getSuitString();
    }

}
