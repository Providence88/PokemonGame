public class ThirdArea extends Area {

    /**
     * Constructs a FirstArea object, initializing it with a specific size (5x1).
     *
     * @param numRows    the number of rows
     * @param numColumns the number of columns
     */
    public ThirdArea(int numRows, int numColumns) {
        super(4, 4);
    }

    /**
     * Displays the layout of the first area, marking the player's position with "P".
     * Overrides the method in the superclass (Area).
     */
    @Override
    public void displayArea() {
        System.out.println("Third Area:");
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
        System.out.println("You encountered a creature in the second area:");

        // Adjust the logic to only allow EL1 creatures
        int el = random.nextInt(1) + 1;

        switch (el) {
            case 1:
                return encounterEL1Creature();
            case 2:
                return encounterEL2Creature();
            case 3:
                return encounterEL3Creature();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }

    private Creature encounterEL1Creature() {
        // Adjust this logic based on your EL1 creatures
        String[] el1CreatureNames = {"Strawander", "Chocowool", "Parfwit", "Brownisaur", "Frubat", "Malts", "Squirpie", "Chocolite", "Oshacone"};
        String randomCreatureName = el1CreatureNames[random.nextInt(el1CreatureNames.length)];
        return new Creature(randomCreatureName, getAreaLevel());
    }

    /**
     * Handles the encounter of EL2 creatures in the first area.
     *
     * @return The encountered EL2 creature.
     */
    private Creature encounterEL2Creature() {
        // Adjust this logic based on your EL1 creatures
        String[] el1CreatureNames = {"Strawleon", "Chocofluff", "Parfure", "Chocosaur", "Golberry", "Kirlicake", "Tartortle", "Chocolish", "Dewice"};
        String randomCreatureName = el1CreatureNames[random.nextInt(el1CreatureNames.length)];
        return new Creature(randomCreatureName, getAreaLevel());
    }

    private Creature encounterEL3Creature() {
        // Adjust this logic based on your EL1 creatures
        String[] el1CreatureNames = {"Strawizard", "Candaros", "Parfelure", "Fudgasaur", "Croberry", "Velvevoir", "Piestoise", "Icesundae", "Samurcone"};
        String randomCreatureName = el1CreatureNames[random.nextInt(el1CreatureNames.length)];
        return new Creature(randomCreatureName, getAreaLevel());
    }
}
