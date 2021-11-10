/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.darkwaterkiller;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Dimension;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.google.gson.*;

public class App extends JFrame {

    //private static final int MINIMUM_TEXT_FIELD_HEIGHT = 20;
    private static final Dimension STFD = new Dimension(150, 20); //Standard Text Field Dimension
    private static final Dimension ATFD = new Dimension(65, 20); //Ammo Text Field Dimension

    //JSON creator
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //panel layouts
    private GroupLayout layout,
        meleeWeaponLayout,
        rangedWeaponLayout;
    
    //main panel
    private JTabbedPane mainSelectorPanel;
    
    //panels
    private JPanel meleeWeaponPanel,
        rangedWeaponPanel;
    
    //main panel buttons
    private JButton closeButton,
        saveButton;
    
    //melee weapon labels
    private JLabel meleeWeaponNameLabel,
        meleeWeaponDamageLabel,
        meleeWeaponSpeedLabel,
        meleeWeaponWeightLabel,
        meleeWeaponValueLabel;
    
    //ranged weapon labels
    private JLabel rangedWeaponNameLabel,
        rangedWeaponDamageLabel,
        rangedWeaponROFLabel,
        rangedWeaponAccLabel,
        rangedWeaponWeightLabel,
        rangedWeaponValueLabel,
        rangedWeaponAmmoLabel,
        rangedWeaponAmmoSeparatorLabel;

    //melee weapon fields
    private JTextField meleeWeaponNameField,
        meleeWeaponDamageField,
        meleeWeaponSpeedField,
        meleeWeaponWeightField,
        meleeWeaponValueField;

    //ranged weapon fields
    private JTextField rangedWeaponNameField,
        rangedWeaponDamageField,
        rangedWeaponROFField,
        rangedWeaponAccField,
        rangedWeaponWeightField,
        rangedWeaponValueField,
        rangedWeaponCurAmmoField,
        rangedWeaponMaxAmmoField;

    public App() {
        this.setTitle("Config Creator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        initComponents();
        initListeners();
    }

    private void initComponents() {
        //#init misc features
        closeButton = new JButton("Close");
        saveButton = new JButton("Save");

        //Melee Weapon
        meleeWeaponNameLabel = new JLabel("Name");
        meleeWeaponNameField = new JTextField();
        meleeWeaponNameField.setMinimumSize(STFD);
        meleeWeaponNameField.setMaximumSize(STFD);
        meleeWeaponDamageLabel = new JLabel("Damage");
        meleeWeaponDamageField = new JTextField();
        meleeWeaponDamageField.setMinimumSize(STFD);
        meleeWeaponDamageField.setMaximumSize(STFD);
        meleeWeaponSpeedLabel = new JLabel("Speed");
        meleeWeaponSpeedField = new JTextField();
        meleeWeaponSpeedField.setMinimumSize(STFD);
        meleeWeaponSpeedField.setMaximumSize(STFD);
        meleeWeaponWeightLabel = new JLabel("Weight");
        meleeWeaponWeightField = new JTextField();
        meleeWeaponWeightField.setMinimumSize(STFD);
        meleeWeaponWeightField.setMaximumSize(STFD);
        meleeWeaponValueLabel = new JLabel("Value");
        meleeWeaponValueField = new JTextField();
        meleeWeaponValueField.setMinimumSize(STFD);
        meleeWeaponValueField.setMaximumSize(STFD);
        
        //Ranged Weapon
        rangedWeaponNameLabel = new JLabel("Name");
        rangedWeaponNameField = new JTextField();
        rangedWeaponNameField.setMinimumSize(STFD);
        rangedWeaponNameField.setMaximumSize(STFD);
        rangedWeaponDamageLabel = new JLabel("Damage");
        rangedWeaponDamageField = new JTextField();
        rangedWeaponDamageField.setMinimumSize(STFD);
        rangedWeaponDamageField.setMaximumSize(STFD);
        rangedWeaponROFLabel = new JLabel("Rate of Fire");
        rangedWeaponROFField = new JTextField();
        rangedWeaponROFField.setMinimumSize(STFD);
        rangedWeaponROFField.setMaximumSize(STFD);
        rangedWeaponAccLabel = new JLabel("Accuracy");
        rangedWeaponAccField = new JTextField();
        rangedWeaponAccField.setMinimumSize(STFD);
        rangedWeaponAccField.setMaximumSize(STFD);
        rangedWeaponWeightLabel = new JLabel("Weight");
        rangedWeaponWeightField = new JTextField();
        rangedWeaponWeightField.setMinimumSize(STFD);
        rangedWeaponWeightField.setMaximumSize(STFD);
        rangedWeaponValueLabel = new JLabel("Value");
        rangedWeaponValueField = new JTextField();
        rangedWeaponValueField.setMinimumSize(STFD);
        rangedWeaponValueField.setMaximumSize(STFD);
        rangedWeaponAmmoLabel = new JLabel("Ammo");
        rangedWeaponCurAmmoField = new JTextField();
        rangedWeaponCurAmmoField.setMinimumSize(ATFD);
        rangedWeaponCurAmmoField.setMaximumSize(ATFD);
        rangedWeaponMaxAmmoField = new JTextField();
        rangedWeaponMaxAmmoField.setMinimumSize(ATFD);
        rangedWeaponMaxAmmoField.setMaximumSize(ATFD);   
        rangedWeaponAmmoSeparatorLabel = new JLabel("/");

        //#make the selector panel
        mainSelectorPanel = new JTabbedPane(JTabbedPane.TOP);

        //#weapons panel
        meleeWeaponPanel = new JPanel();
        meleeWeaponLayout = new GroupLayout(meleeWeaponPanel);
        meleeWeaponPanel.setLayout(meleeWeaponLayout);
        meleeWeaponLayout.setHorizontalGroup(
            meleeWeaponLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                meleeWeaponLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(meleeWeaponNameLabel)
                .addComponent(meleeWeaponNameField)
                .addComponent(meleeWeaponDamageLabel)
                .addComponent(meleeWeaponDamageField)
                .addComponent(meleeWeaponSpeedLabel)
                .addComponent(meleeWeaponSpeedField)
                .addComponent(meleeWeaponWeightLabel)
                .addComponent(meleeWeaponWeightField)
                .addComponent(meleeWeaponValueLabel)
                .addComponent(meleeWeaponValueField)
            )
            .addContainerGap()
        );
        meleeWeaponLayout.setVerticalGroup(
            meleeWeaponLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(meleeWeaponNameLabel)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(meleeWeaponNameField)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(meleeWeaponDamageLabel)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(meleeWeaponDamageField)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(meleeWeaponSpeedLabel)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(meleeWeaponSpeedField)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(meleeWeaponWeightLabel)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(meleeWeaponWeightField)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(meleeWeaponValueLabel)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(meleeWeaponValueField)
            .addContainerGap()
        );

        rangedWeaponPanel = new JPanel();
        rangedWeaponLayout = new GroupLayout(rangedWeaponPanel);
        rangedWeaponPanel.setLayout(rangedWeaponLayout);
        rangedWeaponLayout.setHorizontalGroup(
            rangedWeaponLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                rangedWeaponLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(rangedWeaponNameLabel)
                .addComponent(rangedWeaponNameField)
                .addComponent(rangedWeaponDamageLabel)
                .addComponent(rangedWeaponDamageField)
                .addComponent(rangedWeaponROFLabel)
                .addComponent(rangedWeaponROFField)
                .addComponent(rangedWeaponAccLabel)
                .addComponent(rangedWeaponAccField)
                .addComponent(rangedWeaponWeightLabel)
                .addComponent(rangedWeaponWeightField)
                .addComponent(rangedWeaponValueLabel)
                .addComponent(rangedWeaponValueField)
                .addComponent(rangedWeaponAmmoLabel)
                .addGroup(
                    rangedWeaponLayout.createSequentialGroup()
                    .addComponent(rangedWeaponCurAmmoField)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(rangedWeaponAmmoSeparatorLabel)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(rangedWeaponMaxAmmoField)
                )
            )
            .addContainerGap()
        );
    rangedWeaponLayout.setVerticalGroup(
        rangedWeaponLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(rangedWeaponNameLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(rangedWeaponNameField)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(rangedWeaponDamageLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(rangedWeaponDamageField)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(rangedWeaponROFLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(rangedWeaponROFField)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(rangedWeaponAccLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(rangedWeaponAccField)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(rangedWeaponWeightLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(rangedWeaponWeightField)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(rangedWeaponValueLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(rangedWeaponValueField)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(rangedWeaponAmmoLabel)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(
            rangedWeaponLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(rangedWeaponCurAmmoField)
            .addComponent(rangedWeaponAmmoSeparatorLabel)
            .addComponent(rangedWeaponMaxAmmoField)
        )
        .addContainerGap()
    );

        //add panels to selector
        mainSelectorPanel.addTab("M.Weapon", meleeWeaponPanel);
        mainSelectorPanel.addTab("R.Weapon", rangedWeaponPanel);

        //#layout main
        layout = new GroupLayout(this.getContentPane());
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                .addComponent(mainSelectorPanel)
                .addGroup(
                    layout.createSequentialGroup()
                    .addComponent(saveButton)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(closeButton)
                )
            )
            .addContainerGap()
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(mainSelectorPanel)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addGroup(
                layout.createParallelGroup()
                .addComponent(saveButton)
                .addComponent(closeButton)
            )
            .addContainerGap()
        );

        pack();
        this.setVisible(true);
    }

    private void initListeners() {
        //Close button ends application
        closeButton.addActionListener(l -> {
            this.dispose();
        });

        //Save button saves the config file
        saveButton.addActionListener(l -> {
            switch(mainSelectorPanel.getSelectedIndex()) {
                case 0:
                    saveMeleeWeapon();
                    break;
                case 1:
                    saveRangedWeapon();
                    break;
                default:
                    System.err.println("Error, selected tab does not have a save feature implemented");
            }
        });
    }

    private void saveMeleeWeapon() {
        try {
            MeleeWeapon tmp = new MeleeWeapon(
                meleeWeaponNameField.getText(),
                Integer.parseInt(meleeWeaponDamageField.getText()),
                Integer.parseInt(meleeWeaponSpeedField.getText()),
                Integer.parseInt(meleeWeaponWeightField.getText()),
                Integer.parseInt(meleeWeaponValueField.getText())
            );
            String jsonString = gson.toJson(tmp);
            writeToFile(jsonString, String.format("./configs/weapons/melee/MW_%s.json", tmp.getName()));
        } catch(Exception e) {
            e.printStackTrace();
            System.err.printf("Unable to parse melee weapon data\n");
        }
    }

    private void saveRangedWeapon() {
        try {
            RangedWeapon tmp = new RangedWeapon(
                rangedWeaponNameField.getText(),
                Integer.parseInt(rangedWeaponDamageField.getText()),
                Integer.parseInt(rangedWeaponROFField.getText()),
                Integer.parseInt(rangedWeaponAccField.getText()),
                Integer.parseInt(rangedWeaponWeightField.getText()),
                Integer.parseInt(rangedWeaponValueField.getText()),
                Integer.parseInt(rangedWeaponCurAmmoField.getText()),
                Integer.parseInt(rangedWeaponMaxAmmoField.getText())
            );
            String jsonString = gson.toJson(tmp);
            writeToFile(jsonString, String.format("./configs/weapons/ranged/RW_%s.json", tmp.getName()));
        } catch(Exception e) {
            e.printStackTrace();
            System.err.printf("Unable to parse ranged weapon data\n");
        }
    }

    private static void writeToFile(String JSON, String filename) {
        try{
            //open, write, close
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(JSON);
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.err.printf("Could not save JSON data to file '%s'\n", filename);
        }
    }

    public static void main(String[] args) {
        //Attempt to set the UI's look to the OS default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e) {
            System.err.println("Unable to set look and feel");
        }


        //!This is just test code, delete before buildDist
        
        // Entity e = new Entity("Billy");
        // e.getInventory().add(new MeleeWeapon("stick", 1, 1, 1, 0));
        // e.getInventory().add(new RangedWeapon("pea shooter", 1, 1, 100, 1, 0, 1, 1));
        // String tmp = gson.toJson(e);
        // writeToFile(tmp, "./configs/Entity_Billy.json");
        
        //!end test code

        //Launch the poker app
        SwingUtilities.invokeLater(() -> new App());

    }
}
