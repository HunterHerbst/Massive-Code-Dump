import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LaunchApp {
    public static void main( String[] args ) {
        //Attempt to set the UI's look to the OS default
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        } catch (Exception e) {
            System.out.println( "Unable to set look and feel to system default." );
        }
        //Launch the Poker App
        SwingUtilities.invokeLater( () -> new PokerApp() );
    }
}
