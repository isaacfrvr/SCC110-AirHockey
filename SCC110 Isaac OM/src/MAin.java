public class MAin {
    /**
     * The main method that starts the game.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args){
        // Create a GameArena object with dimensions 1000x600

        GameArena frameobj = new GameArena(1000, 600);
        // Create a GameManager object with initial positions and dimensions

        GameManager gm = new GameManager(142, 523, 41, 903, frameobj, 1000, 600);
        // Create Line objects to define the boundaries and elements of the game

        Line top_Var = new Line(41, 142, 903, 142, 5, "BLUE", 2);
        Line left_Var = new Line(41, 142, 41, 523, 5, "BLUE", 2);
        Line right_Var = new Line(903, 523, 903, 142, 5, "BLUE", 2);
        Line bottom_Var = new Line(41, 523, 903, 523, 5, "BLUE", 2);
        Line left_Goal_Var = new Line(45, 237, 45, 428, 5, "RED", 3);
        Line right_Goal_Var = new Line(899, 428, 899, 237, 5, "RED", 3);
        Line middle_Seperator_Var = new Line(473, 523, 471, 142, 5, "RED", 1);
        // Store the Line objects in an array representing the play area

        Line[] play_Area_Var = {top_Var,left_Var,right_Var,bottom_Var,left_Goal_Var,right_Goal_Var, middle_Seperator_Var};
        // Add the play area lines to the GameArena

        for(Line i : play_Area_Var)  frameobj.addLine(i);
        // Add the score display to the GameArena

        gm.addwinscore();
        // Game loop

        while(true){
            // Get the player's inputs from the GameArena

            int[] pInput_Var = frameobj.p_Inputs();
            // Check for collisions between game elements


            gm.collision_M();
            // Move the game elements according to player inputs

            gm.rigdes(pInput_Var[0],pInput_Var[1],pInput_Var[2],pInput_Var[3]);
            // Pause the frame

            frameobj.pause();
        }
    }
}
