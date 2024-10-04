import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PokemonGame {
    private Inventory currentInventory;
    private Area currentArea;
    private Scanner scan;
    private Area area1;
    private Area area2;
    private Area area3;
    private Creature activeCreature;
    private List<String> availableCreatures;
    private PokemonGameGUI pokemonGameGUI;
    private CreatureInventoryApp inventoryApp;
    private BattlePhase battlePhase;

    public PokemonGame(PokemonGameGUI pokemonGameGUI) {
        currentInventory = new Inventory();
        currentArea = new Area(1,5);
        scan = new Scanner(System.in);

        area1 = new Area(1, 5);
        area2 = new Area(3, 3);
        area3 = new Area(4, 4);

        availableCreatures = new ArrayList<>();
        this.pokemonGameGUI = pokemonGameGUI;
        inventoryApp = null;
    }

    /**
     * Executes the main menu of the game.
     *
     * @param  None
     * @return None
     */
    public void mainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nPRESS ANY KEY TO START THE GAME");
            scanner.nextLine();
    
            System.out.println(" ~ Welcome to the World of Pokemon! ~ \n");
    
            selectStarterCreature();
    
            while (true) {
                System.out.println("------------------------------------------------------");
                System.out.println("\n~~~~~ Main Menu ~~~~~\n");
                System.out.println("1: View Inventory");
                System.out.println("2: Explore Area");
                System.out.println("3: Evolve Creature");
                System.out.println("4: Exit Game");
                System.out.println("------------------------------------------------------");
    
                int userChoice = userInput.getUserChoice(1, 4); // only 1,2,3,4 options are allowed
    
                switch (userChoice) {
                    case 1:
                        viewInventory();
                        break;
                    case 2:
                        exploreArea();
                        break;
                    case 3:
                        evolveCreature();
                        break;
                    case 4:
                        exitGame();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
    
    /**
     * Selects a starter creature for the player.
     *
     * @param  None
     * @return None
     */
    public Creature selectStarterCreature() {
        int i;
        Creature starterCreature1 = new Creature("Strawander", "Fire", "Family A", 1, 100, "Strawander.jpg");
        Creature starterCreature2 = new Creature("Chocowool", "Fire", "Family B", 1, 100, "Chocowool.jpg");
        Creature starterCreature3 = new Creature("Parfwit", "Fire", "Family C", 1, 100, "Parfwit.jpg");
        Creature starterCreature4 = new Creature("Brownisaur", "Grass", "Family D", 1, 100, "Brownisaur.jpg");
        Creature starterCreature5 = new Creature("Frubat", "Grass", "Family E", 1, 100, "Frubat.jpg");
        Creature starterCreature6 = new Creature("Malts", "Grass", "Family F", 1, 100, "Malts.jpg");
        Creature starterCreature7 = new Creature("Squirpie", "Water", "Family G", 1, 100, "Squirpie.jpg");
        Creature starterCreature8 = new Creature("Chocolite", "Water", "Family H", 1, 100, "Chocolite.jpg");
        Creature starterCreature9 = new Creature("Oshacone", "Water", "Family I", 1, 100,"Oshacone.jpg");
    
        // Display a list of EL1 creatures for the user to choose from
        ArrayList<Creature> el1Creatures = new ArrayList<>();
        el1Creatures.add(starterCreature1);
        el1Creatures.add(starterCreature2);
        el1Creatures.add(starterCreature3);
        el1Creatures.add(starterCreature4);
        el1Creatures.add(starterCreature5);
        el1Creatures.add(starterCreature6);
        el1Creatures.add(starterCreature7);
        el1Creatures.add(starterCreature8);
        el1Creatures.add(starterCreature9);
    
        System.out.println("\nSelect a Level 1 (EL1) starter creature:");
        for (i = 0; i < el1Creatures.size(); i++) {
            System.out.println((i + 1) + ": " + el1Creatures.get(i).getName());
        }
    
        int starterChoice = userInput.getUserChoice(1, el1Creatures.size());
        Creature starterCreature = el1Creatures.get(starterChoice - 1);
    
        // Add the starter creature to the inventory and set it as active
        currentInventory.addCreature(starterCreature);
        currentInventory.setActiveCreature(starterCreature);
    
        System.out.println("You selected " + starterCreature.getName() + " as your starter creature!\n");
    
        // battlePhaseGUI.setUserCreature(starterCreature);
        return starterCreature;
    }
    
   
    public void setActiveCreature(Creature creature) {
        this.activeCreature = creature;
    }
    // -----------------------------VIEW INVENTORY-----------------------------

    /**
     * View the inventory and display the active creature, captured creatures,
     * and options to change the active creature or go back to the main menu.
     *
     * @param  None
     * @return None
     */
    private void viewInventory() {
        ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();
        Creature activeCreature = currentInventory.getActiveCreature();// Get current active creature
        int i;

        System.out.println("\nINVENTORY:\n");
        if(activeCreature != null) {
            System.out.println("\nActive Creature:\n");
            System.out.println(activeCreature.toString());
        } else {
            System.out.println("\nNo Active Creatures.");
        }

        if(allCreatures.isEmpty()) {
            System.out.println("\nEmpty Inventory");
        } else {
            System.out.println("\nCaptured Creatures:");

            for(i = 0; i < allCreatures.size(); i++) {
                Creature creature = allCreatures.get(i);
                System.out.println((i + 1) + ": " + creature.getName() + " (EL" + creature.getEvolutionLevel() + ")");
            }
        }
            CreatureInventoryApp inventoryApp = new CreatureInventoryApp(currentInventory);
            inventoryApp.setupUI();

            System.out.println("\n");
            System.out.println("[0] Change Active Creature\n");
            System.out.println("[1] Back to Main Menu\n");

            int choice = userInput.getUserChoice(0, 1);

            if(choice == 0){
                changeActiveCreature();
            }


    }

    /**
     * Changes the active creature in the inventory.
     *
     * @return         	void
     */
    private void changeActiveCreature() {
        System.out.println("\nSelect a new active creature from your inventory:");
    
        int optionNumber = 1;
        List<Creature> availableCreatures = new ArrayList<>();

    
        for (Creature creature : currentInventory.getAllCreatures()) {
            if (!creature.equals(currentInventory.getActiveCreature())) {
                availableCreatures.add(creature);
                System.out.println("[" + optionNumber + "] " + creature.getName());
                optionNumber++;
            
            }
        }
    
        if (availableCreatures.isEmpty()) {
            System.out.println("\nNo other creatures available for swapping.");
            return;
        }
    
        int choice = userInput.getUserChoice(1, optionNumber - 1);
    
        if (choice >= 1 && choice <= availableCreatures.size()) {
            Creature newActiveCreature = availableCreatures.get(choice - 1);
            System.out.println("\nYou selected " + newActiveCreature.getName() + " as your new active creature.");
            currentInventory.setActiveCreature(newActiveCreature);
        } else {
            System.out.println("\nInvalid choice. Please select a valid option.");
        }
    }

// -----------------------------EXPLORE AREA-----------------------------
    
    /**
     * Executes the exploreArea function.
     *
     * @param  None
     * @return None
     */
    private void exploreArea() {
        System.out.println("------------------------------------------------------");
        System.out.println("\nSelect an area to explore:");
        System.out.println("[1] Area 1 (5x1)");
        System.out.println("[2] Area 2 (3x3)");
        System.out.println("[3] Area 3 (4x4)");
        System.out.println("------------------------------------------------------");
        System.out.print("\nEnter your choice: ");
    
        int areaChoice = scan.nextInt();
    
        Area selectedArea;
    
        switch (areaChoice) {
            case 1:
                selectedArea = area1;
                break;
            case 2:
                selectedArea = area2;
                break;
            case 3:
                selectedArea = area3;
                break;
            default:
                System.out.println("Invalid choice. Returning to the main menu...");
                return;
        }
    
        System.out.println("\nExploring Area...\n");
        selectedArea.displayArea();
    
        boolean exploring = true;
    
        while (exploring) {
            movePlayer(selectedArea);
            selectedArea.displayArea();
    
            if (selectedArea.chanceEncounterCreature()) {
                handleCreatureEncounter();
                exploring = false;
            }
    
            // Sleep for a short duration to make the exploration smoother
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

    private Creature getCurrentAreaCreature(ArrayList<Creature> creatures) {
        int currentAreaLevel = currentArea.getAreaLevel();

        switch (currentAreaLevel) {
            case 1:
                return getRandomCreatureFromList(creatures, area1);
            case 2:
                return getRandomCreatureFromList(creatures, area2);
            case 3:
                return getRandomCreatureFromList(creatures, area3);
            default:
                return null;
        }
    }

    private Creature getRandomCreatureFromList(ArrayList<Creature> creatures, Area area) {
        return area.getRandomCreature(creatures);
    }

    private void handleCreatureEncounter() {
        ArrayList<Creature> creatures = new ArrayList<>();

        getCurrentAreaCreature(creatures);

        System.out.println("------------------------------------------------------");
        System.out.println("\nCreature Encountered!");
        System.out.println("------------------------------------------------------");

        Creature userCreature = currentInventory.getActiveCreature();

        List<String> availableCreatures = new ArrayList<>();  // Provide the available creatures
        int areaLevel = 1;  // Provide the area level
        Area currentArea = new Area(areaLevel, availableCreatures);

        Creature enemyCreature = currentArea.encounterCreature();

        BattlePhaseGUI battleGUI = new BattlePhaseGUI(battlePhase);
        battleGUI.setVisible(true);

        BattlePhase battle = new BattlePhase(userCreature, enemyCreature, currentInventory);
        battle.beginBattle(enemyCreature, battleGUI);
    }


/**
 * Retrieves the player's move direction based on user input and updates the player's position.
 *
 * @param selectedArea The area in which the player is currently located.
 */
private Direction movePlayer(Area selectedArea) {
    while (true) {
        System.out.print("Enter your move (UP/DOWN/LEFT/RIGHT): ");
        String input = scan.next().toUpperCase();

        int currentX = selectedArea.getCurrentX();
        int currentY = selectedArea.getCurrentY();

        switch (input) {
            case "UP":
                if (currentX > 0) {
                    selectedArea.moveUp();
                    return Direction.UP;
                } else {
                    System.out.println("You cannot move UP from here.");
                }
                break;

            case "DOWN":
                if (currentX < selectedArea.getTiles().length - 1) {
                    selectedArea.moveDown();
                    return Direction.DOWN;
                } else {
                    System.out.println("You cannot move DOWN from here.");
                }
                break;

            case "LEFT":
                if (currentY > 0) {
                    selectedArea.moveLeft();
                    return Direction.LEFT;
                } else {
                    System.out.println("You cannot move LEFT from here.");
                }
                break;

            case "RIGHT":
                if (currentY < selectedArea.getTiles()[currentX].length - 1) {
                    selectedArea.moveRight();
                    return Direction.RIGHT;
                } else {
                    System.out.println("You cannot move RIGHT from here.");
                }
                break;

            default:
                System.out.println("Invalid direction. Please enter UP, DOWN, LEFT, or RIGHT.");
        }
    }
}

    
// -----------------------------VIEW INVENTORY-----------------------------
    
    /**
    * Evolves a creature by selecting two creatures from the inventory and initiating the evolution phase.
    *
    * @return     void
    */
    private void evolveCreature(){
        ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();

        if(currentInventory.getAllCreatures().size() < 2){
            System.out.println("Insufficient creatures in the inventory to evolve.");
            return;
        }
        System.out.println("\nSelect 2 creatures to evolve");
        displayCreaturesInventory();

        int creatureChoice1 = userInput.getUserChoice(1, allCreatures.size()) - 1;
        int creatureChoice2 = userInput.getUserChoice(1, allCreatures.size()) - 1;

        Creature creature1 = allCreatures.get(creatureChoice1);
        Creature creature2 = allCreatures.get(creatureChoice2);

        EvolvePhase evolvePhase = new EvolvePhase(creature1, creature2, currentInventory);
        evolvePhase.evolve();
    }

    /**
     * Display the inventory of creatures.
     *
     * @param  none
     * @return none
     */
    public void displayCreaturesInventory(){
        ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();
        int i = 1;
        for(Creature creature : allCreatures){
            System.out.println("[" + i + "]" + creature.getName() + " EL" + creature.getEvolutionLevel());
            i++;
        }
    }

// ---------------------------------USER MOVE---------------------------------
    
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    /**
     * Retrieves the player's move direction based on user input.
     *
     * @return Direction  the chosen direction (UP, DOWN, LEFT, RIGHT)
     */
    public Direction getPlayerMoveDirection() {
        Direction chosenDirection = null;
        
        while (chosenDirection == null) {
            System.out.println("\nMake a Move:");
            System.out.print("[1] Move UP\n[2] Move DOWN\n[3] Move LEFT\n[4] Move RIGHT\n[5] Exit Area\n");
            System.out.print("Enter your move: ");
            
            int input = scan.nextInt();
            
            switch (input) {
                case 1:
                    chosenDirection = Direction.UP;
                    break;
                case 2:
                    chosenDirection = Direction.DOWN;
                    break;
                case 3:
                    chosenDirection = Direction.LEFT;
                    break;
                case 4:
                    chosenDirection = Direction.RIGHT;
                    break;
                case 5:
                    return null; // Return null to indicate the user's choice to exit the area
                default:
                    System.out.println("Invalid direction. Out of bounds!");
                    break;
            }
        }
        return chosenDirection;
    }


    /**
     * Moves the player in the specified direction within the selected area.
     *
     * @param  moveUser      the direction in which the player should move
     * @param  selectedArea  the area in which the player is currently located
     */
    public void movePlayer(Direction moveUser, Area selectedArea) {
        switch (moveUser) {
            case UP:
                if (selectedArea.canMoveUp()) {
                    selectedArea.moveUp();
                } else {
                    System.out.println("Cannot move up. Out of bounds!");
                }
                break;

            case DOWN:
                if (selectedArea.canMoveDown()) {
                    selectedArea.moveDown();
                } else {
                    System.out.println("Cannot move down. Out of bounds!");
                }
                break;

            case LEFT:
                if (selectedArea.canMoveLeft()) {
                    selectedArea.moveLeft();
                } else {
                    System.out.println("Cannot move left. Out of bounds!");
                }
                break;

            case RIGHT:
                if (selectedArea.canMoveRight()) {
                    selectedArea.moveRight();
                } else {
                    System.out.println("Cannot move right. Out of bounds!");
                }
                break;
        }
    }
    public Creature getUserCreature() {
        return currentInventory.getActiveCreature();
    }

    public Inventory getInventory() {
        return currentInventory;
    }

    public Area getArea() {
        return currentArea;
    }


    /**
     * Sets up the inventory app reference.
     *
     * @param inventoryApp The CreatureInventoryApp instance.
     */
    public void setInventoryApp(CreatureInventoryApp inventoryApp) {
        this.inventoryApp = inventoryApp;
    }

    public void goBackToMainMenu() {
        if (inventoryApp != null) {
            inventoryApp.setVisible(false);
            // Implement additional logic if needed
        }
    }


// -----------------------------EXIT-----------------------------
    /**
    * A description of the entire Java function.
    *
    * @param  None        No parameters.
    * @return void        No return value.
    */
    public void exitGame() {
        System.out.println("Exiting the game. Goodbye!");
        scan.close();
        System.exit(0); 
    }
}