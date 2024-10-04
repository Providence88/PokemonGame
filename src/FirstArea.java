public class FirstArea extends Area {

    /**
     * Constructs a FirstArea object, initializing it with a specific size.
     *
     * @param numRows    the number of rows
     * @param numColumns the number of columns
     */
    public FirstArea(int numRows, int numColumns) {
        super(numRows, numColumns);
    }

    /**
     * Displays the layout of the first area, marking the player's position with "P".
     * Overrides the method in the superclass (Area).
     */
    @Override
    public void displayArea() {
        System.out.println("First Area:");
        super.displayArea();
    }

    /**
     * Handles the encounter of creatures in the first area based on their Evolution Level (EL).
     * Overrides the method in the superclass (Area).
     *
     * @return The encountered creature limited to EL1 creatures.
     */
    @Override
    public Creature encounterCreature() {
        System.out.println("You encountered a creature in the first area:");

        // Adjust the logic to only allow EL1 creatures
        int el = random.nextInt(1) + 1;

        switch (el) {
            case 1:
                return encounterEL1Creature();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }

    /**
     * Handles the encounter of EL1 creatures in the first area.
     *
     * @return The encountered EL1 creature.
     */
    private Creature encounterEL1Creature() {
        // Adjust this logic based on your EL1 creatures
        String[] el1CreatureNames = {"Strawander", "Chocowool", "Parfwit", "Brownisaur", "Frubat", "Malts", "Squirpie", "Chocolite", "Oshacone"};
        String randomCreatureName = el1CreatureNames[random.nextInt(el1CreatureNames.length)];
        return new Creature(randomCreatureName, getAreaLevel());
    }
}
