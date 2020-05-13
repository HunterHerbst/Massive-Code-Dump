import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class GameOfLifeApp extends JFrame {
    private static final long serialVersionUID = 1L;

    //components
    private Conway game;
    private JButton startTimerButton, stopTimerButton, updateGridButton;
    private JLabel timerSpeedLabel;
    private JPanel buttonPanel;
    private JTextArea gameGrid;
    private JTextField timerSpeed;
    private Timer gameTimer;

    public GameOfLifeApp() {
        game = new Conway(40, 80);

        //initialization methods
        initComponents();
        initListeners();

        //display the frame
        this.setVisible( true );
    }

    private void initComponents() {

        //component initialization
        startTimerButton = new JButton( "Start Timer" );
        stopTimerButton = new JButton( "Stop Timer" );
        updateGridButton = new JButton( "Update" );
        timerSpeedLabel = new JLabel( "Timer Speed" );
        buttonPanel = new JPanel();
        gameGrid = new JTextArea( game.toString() );
        timerSpeed = new JTextField( "1" );
        gameTimer = new Timer( 1000, e -> { updateBoardEvent(); } );


        //frame title
        this.setTitle( "Conway's Game of Life" );

        //operation when red x is pressed
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        //timer settings
        gameTimer.setCoalesce( true );
        gameTimer.setInitialDelay( 0 );
        gameTimer.setRepeats( true );

        //timer isn't running at first, so stop timer button should not be clickable
        stopTimerButton.setEnabled( false );

        //button panel layout
        GroupLayout buttonPanelLayout = new GroupLayout( buttonPanel );
        buttonPanel.setLayout( buttonPanelLayout );
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                buttonPanelLayout.createParallelGroup( Alignment.CENTER )
                .addComponent( updateGridButton )
                .addGroup(
                    buttonPanelLayout.createSequentialGroup()
                    .addComponent( startTimerButton )
                    .addPreferredGap( ComponentPlacement.RELATED )
                    .addComponent( stopTimerButton )
                )
                .addGroup(
                    buttonPanelLayout.createSequentialGroup()
                    .addComponent( timerSpeedLabel )
                    .addPreferredGap( ComponentPlacement.RELATED )
                    .addComponent( timerSpeed )
                )
            )
            .addContainerGap()
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent( updateGridButton )
            .addPreferredGap( ComponentPlacement.UNRELATED )
            .addGroup(
                buttonPanelLayout.createParallelGroup()
                .addComponent( startTimerButton )
                .addComponent( stopTimerButton )
            )
            .addPreferredGap( ComponentPlacement.UNRELATED )
            .addGroup(
                buttonPanelLayout.createParallelGroup()
                .addComponent( timerSpeedLabel )
                .addComponent( timerSpeed )
            )
            .addContainerGap()
        );

        //main frame layout
        GroupLayout layout = new GroupLayout( this.getContentPane() );
        this.setLayout( layout );
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                layout.createParallelGroup()
                .addComponent( gameGrid )
                .addComponent( buttonPanel )
            )
            .addContainerGap()
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addComponent( gameGrid )
            .addPreferredGap( ComponentPlacement.UNRELATED )
            .addComponent( buttonPanel )
            .addContainerGap()
        );

        //make things pretty
        this.pack();
    }

    private void initListeners() {
        //update grid button
        updateGridButton.addActionListener(
            e -> {
                updateBoardEvent();
            }
        );

        //start timer button
        startTimerButton.addActionListener(
            e -> {
                startTimerButton.setEnabled( false );
                stopTimerButton.setEnabled( true );
                updateGridButton.setEnabled( false );
                timerSpeed.setEditable( false );
                gameGrid.setEditable( false );
                try {
                    gameTimer.setDelay( 1000 / Integer.parseInt( timerSpeed.getText() ) );
                } catch( Exception ex ) {
                    gameTimer.setDelay( 1000 );
                    timerSpeed.setText( "1" );
                }
                gameTimer.start();
            }
        );

        //stop timer button
        stopTimerButton.addActionListener(
            e -> {
                stopTimerButton.setEnabled( false );
                startTimerButton.setEnabled( true );
                updateGridButton.setEnabled( true );
                timerSpeed.setEditable( true );
                gameGrid.setEditable( true );
                gameTimer.stop();
            }
        );
    }

    private void updateBoardEvent() {
        game.setBoardWithString( gameGrid.getText(), '#' );
        game.update();
        gameGrid.setText( game.toString() );
    }

    public static void main( String[] args ) {
        //change the look and feel
        try {
            javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName() );
        } catch ( ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex
                ) {
            java.util.logging.Logger.getLogger( GameOfLifeApp.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }

        //invoke an instance of the Game of Life app
        javax.swing.SwingUtilities.invokeLater( () -> { new GameOfLifeApp(); } );
    }
}
