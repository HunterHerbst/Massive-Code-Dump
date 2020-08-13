import java.awt.ComponentOrientation;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class PokerApp extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private GroupLayout layout, bettingLayout;
    private JButton incBetButton, decBetButton, betButton;
    private JLabel pointsLabel, betLabel;
    private JPanel bettingPanel;
    private JTextArea pointTextArea;
    private JTextPane betTextPane;

    private Game g;
    private int bet, points;

    public PokerApp() {
        bet = 1;
        points = 100;
        
        initComponents();
        initListeners();

        g = new Game();
        g.deal();
    }

    private void initComponents() {
        
        bettingPanel = new JPanel();
        betLabel = new JLabel( "Bet Amount" );
        decBetButton = new JButton( "-" );
        incBetButton = new JButton( "+" );
        betButton = new JButton( "Bet" );

        betTextPane = new JTextPane();
        SimpleAttributeSet betTextPaneAttributes = new SimpleAttributeSet();
        StyleConstants.setAlignment( betTextPaneAttributes, StyleConstants.ALIGN_CENTER );
        betTextPane.setParagraphAttributes( betTextPaneAttributes, true );
        betTextPane.setText( String.valueOf( bet ) );
        betTextPane.setEditable( false );

        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.setTitle( "5 Draw Poker" );

        bettingPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED), "Bets" ) );

        layout = new GroupLayout( this.getContentPane() );
        this.setLayout( layout );
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addComponent( bettingPanel )
            .addContainerGap()
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addComponent( bettingPanel )
            .addContainerGap()
        );

        bettingLayout = new GroupLayout( bettingPanel );
        bettingPanel.setLayout( bettingLayout );
        bettingLayout.setHorizontalGroup(
            bettingLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                bettingLayout.createParallelGroup( GroupLayout.Alignment.CENTER, true )
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
}
