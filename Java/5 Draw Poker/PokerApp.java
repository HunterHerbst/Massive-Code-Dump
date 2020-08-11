import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class PokerApp extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private GroupLayout layout;
    private JPanel bettingPanel;

    public PokerApp() {
        initComponents();
    }

    private void initComponents() {
        
        bettingPanel = new JPanel();

        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        this.setTitle( "5 Draw Poker" );

        bettingPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED), "Bets" ) );

        layout = new GroupLayout( this.getContentPane() );
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

        pack();
        this.setVisible( true );

    }
}
