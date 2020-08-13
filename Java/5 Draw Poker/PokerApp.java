import java.awt.event.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

public class PokerApp extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private GroupLayout layout, bettingLayout;
    private JPanel bettingPanel;
    private JButton testButton;
    private JToggleButton testToggle;

    private Game g;

    public PokerApp() {
        initComponents();
        initListeners();

        g = new Game();
        g.deal();
    }

    private void initComponents() {
        
        bettingPanel = new JPanel();
        testButton = new JButton( "Print Toggle" );
        testToggle = new JToggleButton( "Toggle Me" );

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
            .addComponent( testButton )
            .addPreferredGap( ComponentPlacement.RELATED )
            .addComponent( testToggle )
            .addContainerGap()
        );
        bettingLayout.setVerticalGroup(
            bettingLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                bettingLayout.createParallelGroup()
                .addComponent( testButton )
                .addComponent( testToggle )
            )
            .addContainerGap()
        );

        pack();
        this.setVisible( true );
    }

    private final void initListeners() {
        testButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                    System.out.println(e);
                }
            }
        );
    }
}
