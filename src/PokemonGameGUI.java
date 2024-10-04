import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PokemonGameGUI extends JFrame {
    private PokemonGame pokemonGame;
    private JPanel currentPanel;
    private JPanel mainPanel;
    private boolean transitionOccurred = false;

    // Define the starter creatures
    private Creature starterCreature1 = new Creature("Strawander", "Fire", "Family A", 1, 100, "Strawander.jpg");
    private Creature starterCreature2 = new Creature("Chocowool", "Fire", "Family B", 1, 100, "Chocowool.jpg");
    private Creature starterCreature3 = new Creature("Parfwit", "Fire", "Family C", 1, 100, "Parfwit.jpg");
    private Creature starterCreature4 = new Creature("Brownisaur", "Grass", "Family D", 1, 100, "Brownisaur.jpg");
    private Creature starterCreature5 = new Creature("Frubat", "Grass", "Family E", 1, 100, "Frubat.jpg");
    private Creature starterCreature6 = new Creature("Malts", "Grass", "Family F", 1, 100, "Malts.jpg");
    private Creature starterCreature7 = new Creature("Squirpie", "Water", "Family G", 1, 100, "Squirpie.jpg");
    private Creature starterCreature8 = new Creature("Chocolite", "Water", "Family H", 1, 100, "Chocolite.jpg");
    private Creature starterCreature9 = new Creature("Oshacone", "Water", "Family I", 1, 100, "Oshacone.jpg");

    public PokemonGameGUI(PokemonGame pokemonGame) {
        this.pokemonGame = pokemonGame;

        setTitle("Pokemon Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Show the welcome page initially
    
        initComponents();
        showWelcomePage();
    }

    /**
     * Shows the welcome page of the Pokemon: Cafe Cuties Edition game.
     *
     * @param  None   This function does not take any parameters.
     * @return        This function does not return any value.
     */
    public void showWelcomePage() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Color.black);
    
        JLabel welcomeLabel1 = new JLabel("Welcome to Pokemon: Cafe Cuties Edition");
        JLabel welcomeLabel2 = new JLabel("Click Anywhere to Start");
    
        customizeWelcomeLabel(welcomeLabel1);
        customizeWelcomeLabel(welcomeLabel2);
    
        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeLabel1);
        welcomePanel.add(welcomeLabel2);
        welcomePanel.add(Box.createVerticalGlue());
    
        setContentPane(welcomePanel);
        currentPanel = welcomePanel;
    
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPanel == welcomePanel && !transitionOccurred) {
                    transitionOccurred = true;
                    transitionToMainMenu();
                }
            }
        });
    }
    
    /**
     * Customizes the welcome label by centering it horizontally,
     * setting the text color to pink, and changing the font to a cute
     * Comic Sans MS with a bold style and size 24.
     *
     * @param  label  the JLabel to be customized
     */
    private void customizeWelcomeLabel(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.pink);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 24)); // Change the font to a cute one and increase the size
    }
    

    /**
     * Transitions the application to the main menu.
     *
     * @param  None  This function does not take any parameters.
     * @return None  This function does not return anything.
     */
    private void transitionToMainMenu() {
    // Set the content pane back to the mainPanel
    setContentPane(mainPanel);
    
    // Initialize the main components after transitioning
    initComponents();
    
    // Update the current panel to the mainPanel
    currentPanel = mainPanel;
    
    // Repaint the frame to reflect the changes
    revalidate();
    repaint();
}

/**
 * Displays the starter creatures on the screen.
 *
 * @param  none
 * @return none
 */
private void displayStarterCreatures() {
    JPanel starterPanel = new JPanel(new GridLayout(3, 3, 10, 10));
    starterPanel.setBackground(Color.lightGray);

    Creature[] starterCreatures = {starterCreature1, starterCreature2, starterCreature3, starterCreature4,
            starterCreature5, starterCreature6, starterCreature7, starterCreature8, starterCreature9};

    for (Creature starterCreature : starterCreatures) {
        String absolutePath = "Creatures/" + starterCreature.getFamily() + "/" + starterCreature.getImagePath();

        ImageIcon creatureImageIcon = new ImageIcon(absolutePath);

        if (creatureImageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image resizedImage = creatureImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            creatureImageIcon = new ImageIcon(resizedImage);

            JLabel creatureImageLabel = new JLabel(creatureImageIcon);
            creatureImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel creatureLabel = new JLabel("<html>Name: " + starterCreature.getName() +
                    "<br>Type: " + starterCreature.getType() +
                    "<br>Family: " + starterCreature.getFamily() +
                    "<br>Health: " + starterCreature.getHealth() + "</html>");
            creatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton selectButton = new JButton("Select");
            selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            selectButton.addActionListener(e -> handleStarterCreatureSelection(starterCreature));

            JPanel creaturePanel = new JPanel();
            creaturePanel.setLayout(new BoxLayout(creaturePanel, BoxLayout.Y_AXIS));
            creaturePanel.add(creatureImageLabel);
            creaturePanel.add(creatureLabel);
            creaturePanel.add(selectButton);
            creaturePanel.setBackground(Color.white); // Adjust the color as needed

            starterPanel.add(creaturePanel);
        } else {
            System.out.println("Failed to load image: " + absolutePath);
        }
    }

    setContentPane(starterPanel);
    currentPanel = starterPanel;
    revalidate();
    repaint();
}

   

/**
 * Initializes the components of the GUI.
 */
private void initComponents() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(Color.black);

    JLabel headerLabel = new JLabel("Pokemon: Cafe Cuties Special Edition");
    headerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    headerLabel.setForeground(Color.pink);
    headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(headerLabel);

    JButton selectStarterButton = new JButton("Select Starter Creature");
    customizeButton(selectStarterButton);

    JButton exploreButton = new JButton("Explore Area");
    customizeButton(exploreButton);

    JButton viewInventoryButton = new JButton("View Inventory");
    customizeButton(viewInventoryButton);

    JButton evolutionButton = new JButton("Evolution");
    customizeButton(evolutionButton);

    JButton exitButton = new JButton("Exit Game");
    customizeButton(exitButton);

    mainPanel.add(Box.createVerticalGlue());
    mainPanel.add(selectStarterButton);
    mainPanel.add(exploreButton);
    mainPanel.add(viewInventoryButton);
    mainPanel.add(evolutionButton); // Added Evolution button
    mainPanel.add(exitButton);
    mainPanel.add(Box.createVerticalGlue());

    setContentPane(mainPanel);
    currentPanel = mainPanel;
}

/**
 * Customizes the given button.
 *
 * @param  button  the JButton to be customized
 */
private void customizeButton(JButton button) {
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setBackground(Color.pink);
    button.setForeground(Color.black);
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 30)); // Change the font size
    button.setPreferredSize(new Dimension(300, 60)); // Set preferred size to twice the size
    button.addActionListener(e -> handleButtonAction(button));
}

/**
 * Handles button actions based on the text of the button.
 *
 * @param  button  the button that was clicked
 */
private void handleButtonAction(JButton button) {
    // Handle button actions here
    if (button.getText().equals("Select Starter Creature")) {
        displayStarterCreatures();
    } else if (button.getText().equals("Explore Area")) {
        openAreaGUI(pokemonGame.getArea());
    } else if (button.getText().equals("View Inventory")) {
        openInventoryGUI();
    } else if (button.getText().equals("Evolution")) {
        openEvolutionGUI();
    } else if (button.getText().equals("Exit Game")) {
        handleExitGame();
    }
}

    /**
     * Opens the graphical user interface for the specified area.
     *
     * @param  selectedArea  the area to be displayed in the GUI
     */
    private void openAreaGUI(Area selectedArea) {
        AreaDisplayGUI areaDisplayGUI = new AreaDisplayGUI();
        areaDisplayGUI.setVisible(true);
    }

    
    /**
     * Opens the inventory GUI.
     *
     * @param  None
     * @return None
     */
    private void openInventoryGUI() {
        CreatureInventoryApp inventoryDisplayApp = new CreatureInventoryApp(pokemonGame.getInventory());
        inventoryDisplayApp.setVisible(true);
    }

     /**
      * Opens the Evolution GUI.
      *
      * This function creates a new instance of the EvolutionPhaseGUI class and sets it to be visible.
      * It does this by using the SwingUtilities.invokeLater method with a lambda expression.
      */
     private void openEvolutionGUI() {
         SwingUtilities.invokeLater(() -> {
             EvolutionPhaseGUI evolutionPhaseGUI = new EvolutionPhaseGUI();
            evolutionPhaseGUI.setVisible(true);
         });
     }

    /**
     * Handles the selection of a starter creature.
     *
     * @param  selectedStarter  the selected starter creature
     */
    private void handleStarterCreatureSelection(Creature selectedStarter) {
        // Display a message indicating the selected starter creature
        JOptionPane.showMessageDialog(
                this,
                "You selected " + selectedStarter.getName() + " as your starter creature!",
                "Starter Creature Selected",
                JOptionPane.INFORMATION_MESSAGE
        );
    
        // Set the selected creature as the active creature in the inventory
        pokemonGame.getInventory().setActiveCreature(selectedStarter);
    
        // Add the selected creature to the inventory
        pokemonGame.getInventory().addCreature(selectedStarter);
    
        // Perform any additional logic here if needed
    
        // Transition back to the main menu without disposing the frame
        transitionToMainMenu();
    }

    /**
     * Handles the selection of the starter creature.
     *
     * @param  None   This function does not take any parameters.
     * @return None   This function does not return any value.
     */
    private void handleSelectStarterCreature() {
        displayStarterCreatures();
    }

    /**
     * Selects the starter creature for the player and displays a dialog with the selection.
     *
     * @return         	The selected starter creature.
     */
    private void selectStarterCreatureDialog() {
        Creature selectedStarter = pokemonGame.selectStarterCreature();

        if (selectedStarter != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "You selected " + selectedStarter.getName() + " as your starter creature!",
                    "Starter Creature Selected",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Handles the exit game event.
     *
     * @param  None    This function does not have any parameters.
     * @return None    This function does not return any value.
     */
    private void handleExitGame() {
        pokemonGame.exitGame();
    }

    /**
     * Sets the Pokemon game.
     *
     * @param  pokemonGame  the Pokemon game object to set
     */
    public void setPokemonGame(PokemonGame pokemonGame) {
        this.pokemonGame = pokemonGame;
    }

    /**
     * Initializes and starts the Pokemon game.
     *
     * @param  args   the command-line arguments
     * @return        void
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PokemonGame pokemonGame = new PokemonGame(null);  // You can provide null or adjust the constructor as needed
            PokemonGameGUI pokemonGameGUI = new PokemonGameGUI(pokemonGame);  // Create an instance of PokemonGameGUI
    
            // Set the PokemonGame instance in PokemonGameGUI after creating it
            pokemonGameGUI.setPokemonGame(pokemonGame);
    
            pokemonGameGUI.setVisible(true);
        });
    }
}
