public class Robot {
    private static final String TO_HEX = "%X";
    private static final int MAX_POSITIONS = 10;
    private static final int[] blockCount = new int[MAX_POSITIONS];
    private static final int NOT_PICKED = -1;
    private static final int MAX_BLOCKS = 15;
    static int sno = 1;
    static String[] moves = {"", "QWERTYUI", "PPPPPP", "LLLLLL", "MMMMMMMMMMMMMMM", "PMLPMMLPMLPMMML", "PLPLPLPLPLPLPLPLPLPL",
            "PPPPPPLL", "PMMMMMLMMMMMML", "LLLLLLM", "MMMMMMMMMMMMMMML", "MMMMMMMMLMMMMMMML", "MLMLMLMLMLMLMLMLMLMLMLML"};

    public static void main(String[] args) {
        for (String move : moves) {
            String position = blocksAtEachPosition(move);
            System.out.println(move + ":" + position);
        }
    }

    /**
     * Scan each move and update the block counts at each position based on the below conditions
     * Robot moves
     * P: PICKUP FROM THE STASH AND MOVE TO STASH 0
     * M: MOVE TO THE NEXT POSITION
     * L: LOWER THE BLOCK I.E. PUT THE BLOCK
     * <p>
     * Restrictions
     * 1. There are always block in the stash (Pickup will always get a block)
     * 2. If the robot holds the block pickup will reset the block position to 0
     * 3. The robot will not go beyond position 9. Trying to Move further does nothing
     * 4. Lowering a block on a pile of 15 blocks does nothing(and the robot will keep any block it holds)
     * 5. Lowering without a block does nothing
     * 6. The robot ignores any command other than P,M,L
     * <p>
     * Outputs the number of blocks at each position
     *
     * @param input
     * @return
     */
    private static String blocksAtEachPosition(String input) {
        StringBuilder builder = new StringBuilder();
        int pos = -1;
        boolean robotHoldsBlock = false;
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'P': {
                    pos = 0;
                    robotHoldsBlock = true;
                    break;
                }
                case 'M': {
                    if (pos < MAX_POSITIONS - 1)
                        pos++;
                    break;
                }
                case 'L': {
                    if (pos > NOT_PICKED && blockCount[pos] < MAX_BLOCKS && robotHoldsBlock) {
                        robotHoldsBlock = false;
                        blockCount[pos]++;
                    }
                    break;
                }
                default: {} // do nothing
            }
        }
        for (int i = 0; i < blockCount.length; i++) {
            builder.append(String.format(TO_HEX, blockCount[i]));
            blockCount[i] = 0;// reset count
        }
        return builder.toString();
    }
}