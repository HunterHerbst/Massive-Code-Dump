import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class PokerApp extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private GroupLayout layout, bettingLayout, cardsLayout;
    private JButton incBetButton, decBetButton, betButton;
    private JLabel pointsLabel, betLabel;
    private JPanel bettingPanel, cardsPanel;
    private JTextPane betTextPane, pointsTextPane;
    private JTextPane[] cardTextPanes;

    private Game g;
    private int bet, points;

    public PokerApp() {
        bet = 1;
        points = 100;
        
        g = new Game();

        initComponents();
        initListeners();

        g.deal();
    }

    private void initComponents() {
        
        bettingPanel = new JPanel();
        betLabel = new JLabel( "Bet Amount" );
        pointsLabel = new JLabel( "Points" );
        decBetButton = new JButton( "-" );
        incBetButton = new JButton( "+" );
        betButton = new JButton( "Bet" );

        betTextPane = new JTextPane();
        SimpleAttributeSet betTextPaneAttributes = new SimpleAttributeSet();
        StyleConstants.setAlignment( betTextPaneAttributes, StyleConstants.ALIGN_CENTER );
        betTextPane.setParagraphAttributes( betTextPaneAttributes, true );
        betTextPane.setText( String.valueOf( bet ) );
        betTextPane.setEditable( false );

        pointsTextPane = new JTextPane();
        SimpleAttributeSet pointTextPaneAttributes = new SimpleAttributeSet();
        StyleConstants.setAlignment( pointTextPaneAttributes, StyleConstants.ALIGN_CENTER );
        pointsTextPane.setParagraphAttributes( pointTextPaneAttributes, true );
        pointsTextPane.setText( String.valueOf( points ) );
        pointsTextPane.setEditable( false );

        cardsPanel = new JPanel();

        setUpCardPanes();

        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.setTitle( "5 Draw Poker" );

        bettingPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED), "Bets" ) );
        cardsPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Cards" ) );

        layout = new GroupLayout( this.getContentPane() );
        this.setLayout( layout );
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addComponent( bettingPanel )
            .addPreferredGap( ComponentPlacement.UNRELATED )
            .addComponent( cardsPanel )
            .addContainerGap()
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                layout.createParallelGroup( Alignment.LEADING )
                .addComponent( bettingPanel )
                .addComponent( cardsPanel )
            )
            .addContainerGap()
        );

        bettingLayout = new GroupLayout( bettingPanel );
        bettingPanel.setLayout( bettingLayout );
        bettingLayout.setHorizontalGroup(
            bettingLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                bettingLayout.createParallelGroup( GroupLayout.Alignment.CENTER, true )
                .addComponent( pointsLabel )
                .addComponent( pointsTextPane )
                .addComponent( betLabel )
                .addComponent( betTextPane )
                .addGroup( GroupLayout.Alignment.CENTER, 
                    bettingLayout.createSequentialGroup()
                    .addComponent( decBetButton )
                    .addComponent( betButton )
                    .addComponent( incBetButton )
                )
            )
            .addContainerGap()
        );
        bettingLayout.setVerticalGroup(
            bettingLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent( pointsLabel )
            .addComponent( pointsTextPane )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addComponent( betLabel )
            .addComponent( betTextPane )
            .addGroup(
                bettingLayout.createParallelGroup()
                .addComponent( decBetButton )
                .addComponent( betButton )
                .addComponent( incBetButton )
            )
            .addContainerGap()
        );

        cardsLayout = new GroupLayout( cardsPanel );
        cardsPanel.setLayout( cardsLayout );
        cardsLayout.setHorizontalGroup(
            cardsLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                cardsLayout.createParallelGroup( Alignment.CENTER )
                .addComponent( cardTextPanes[0] )
                .addComponent( cardTextPanes[1] )
                .addComponent( cardTextPanes[2] )
                .addComponent( cardTextPanes[3] )
                .addComponent( cardTextPanes[4] )
            )
            .addContainerGap()
        );
        cardsLayout.setVerticalGroup(
            cardsLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent( cardTextPanes[0] )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addComponent( cardTextPanes[1] )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addComponent( cardTextPanes[2] )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addComponent( cardTextPanes[3] )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addComponent( cardTextPanes[4] )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addContainerGap()
        );

        pack();
        this.setVisible( true );
    }

    private final void initListeners() {
        // testButton.addActionListener(
        //     new ActionListener() {
        //         @Override
        //         public void actionPerformed( ActionEvent e ) {
                    
        //         }
        //     }
        // );

        decBetButton.addActionListener(
            l -> {
                if( bet > 1 )
                    bet--;
                betTextPane.setText( String.valueOf( bet ) );
            }
        );

        incBetButton.addActionListener(
            l -> {
                if( bet < 5 )
                    bet++;
                betTextPane.setText( String.valueOf( bet ) );
            }
        );
    }

    private final void setUpCardPanes() {
        cardTextPanes = new JTextPane[5];
        SimpleAttributeSet cardTextPaneAttributes = new SimpleAttributeSet();
        StyleConstants.setAlignment( cardTextPaneAttributes, StyleConstants.ALIGN_CENTER );
        
        for( int i = 0; i < 5; i++ ) {
            cardTextPanes[i] = new JTextPane();
            cardTextPanes[i].setParagraphAttributes( cardTextPaneAttributes, true );
            cardTextPanes[i].setEditable( false );
        }
        updateHandCards();
    }

    private final void updateHandCards() {
        Card[] hand = g.getHand();
        for( int i = 0; i < 5; i++ )
            if( hand[i] != null )
                cardTextPanes[i].setText( String.valueOf( hand[i] ) );
            else
                cardTextPanes[i].setText( "" );
    }
}
