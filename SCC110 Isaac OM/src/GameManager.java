/**
 * The GameManager class manages the game logic and user interface.
 */

public class GameManager {
    private Puck puck_Obj;
    private Striker player1_Obj, player2_Obj;
    private int player1_Score_Var, player_2_Score_Var, win_Score_Var;
    private int bottom_Var, top_Var, left_Var, right_Var, width_Var, height_Var;
    private GameArena game_Obj;
    private boolean won_Obj = false;
    /**
     * Checks if the game has been won.
     * @return true if the game has been won, false otherwise.
     */
    public boolean gameWon(){
        return this.won_Obj;
    }
    private boolean selected_Var = false;
    private Text display_Player1_Var, display_Player2_Var;
    private Rectangle Black_board_Var;
    private Rectangle restart_Var;
    private Text final_Score_Var, Won_Var, restart_Txt_Var;


    private Rectangle p_Var, p_m, p_confirm;
    private Text s_Var, p_Icon_Var, mIcon_Var, co_Var, max_Score_Var;

    boolean b_Striker_Size_Var = false, b_Puck_Size_Var = false, Puck_Speed_Var = false;
    int f_Wait_Var = 7, f_Wait = 0;
    int[] pos_Var = new int[]{0,0};

    private Rectangle puck_Speed_Var, p_Size_VAr,       striker_Size_Var;
    private Text p_SpeedTxt_Var, p_SizeTxt_Var,   striker_Size_Txt_Var;


    private Rectangle music_Var,      sound_Var;
    private Text music_Txt_Var,  sound_Txt_Var;
    private boolean soundchecker = true, musiccheker = true;
    /**
     * Constructs a new GameManager with the specified parameters.
     * @param top_Var The top position of the game area.
     * @param bottom_Var The bottom position of the game area.
     * @param left_Var The left position of the game area.
     * @param right_Var The right position of the game area.
     * @param game_Obj The GameArena object representing the game area.
     * @param width_Var The width of the game area.
     * @param height_Var The height of the game area.
     */
    public GameManager(int top_Var, int bottom_Var, int left_Var, int right_Var, GameArena game_Obj, int width_Var, int height_Var){
        this.bottom_Var = bottom_Var;
        this.top_Var = top_Var;
        this.right_Var = right_Var;
        this.left_Var = left_Var;
        this.game_Obj = game_Obj;
        this.width_Var = width_Var;
        this.height_Var = height_Var;

        Rectangle rect = new Rectangle(left_Var, top_Var, right_Var - left_Var, bottom_Var - top_Var, "WHITE");
        game_Obj.addRectangle(rect);
        Black_board_Var = new Rectangle(0, 0, width_Var, height_Var, "BLACK", 6);

        this.win_Score_Var = 1;

        this.restart_Var = new Rectangle(width_Var /2 - 140, height_Var *3/4, 280, 100, "GREEN", 7);
        this.restart_Txt_Var =  new Text("Re play"  , 50   , width_Var /2-80, height_Var *3/4 + 70 , "BLACK", 8);
        this.Won_Var =       new Text(""         , 80   , width_Var /2-350, (height_Var /2)-50   , "WHITE", 7);
        this.final_Score_Var =   new Text(""         , 80    , width_Var /2-100, height_Var /4-50   , "WHITE", 7);

        this.max_Score_Var = new Text("Maximum score"         , 60    , width_Var /2 - 140, (height_Var)/4 -40   , "WHITE", 8);
        this.p_Icon_Var = new Text("+"         , 80    , width_Var /4-20, height_Var /2+70   , "BLACK", 8);
        this.mIcon_Var = new Text("-"         , 80    , width_Var * 3/4-10, height_Var /2+70   , "BLACK", 8);
        this.co_Var = new Text("Confirm"         , 60    , width_Var /2 - 120, (height_Var *3)/4 + 70   , "WHITE", 8);
        this.s_Var = new Text("1"         , 80    , width_Var /2-20, height_Var /4+50   , "WHITE", 8);
        this.p_Var = new Rectangle(width_Var /4 - 100, height_Var /2, 200, 90, "WHITE", 7);
        this.p_m = new Rectangle(width_Var * 3/4 - 100, height_Var /2, 200, 90, "WHITE", 7);
        this.p_confirm = new Rectangle(width_Var /2 - 140, (height_Var *3)/4, 280, 100, "GREEN", 7);


        MusicPLayer.playBackGroundMusic((float)0.2);

        this.puck_Speed_Var =        new Rectangle(width_Var /4-100,   5, 200, 80, "RED", 7);
        this.p_Size_VAr =         new Rectangle(width_Var /2-100,   5, 200, 80, "RED", 7);
        this.striker_Size_Var =       new Rectangle(width_Var *3/4-100, 5, 200, 80, "RED", 7);
        this.p_SpeedTxt_Var =     new Text("Puck Speed", 30, width_Var /4-85, 53, "WHITE", 8);
        this.p_SizeTxt_Var =      new Text("Puck Size", 30, width_Var /2-70, 53, "WHITE", 8);
        this.striker_Size_Txt_Var =   new Text("Striker Size", 30, width_Var *3/4-85, 53, "WHITE", 8);

        this.sound_Var =            new Rectangle(width_Var -50,   5, 50, 50, "GREEN", 7);
        this.music_Var =              new Rectangle(0,   5, 50, 50, "GREEN", 7);
        this.music_Txt_Var =        new Text("Music Play", 20, 2, 35, "RED", 8);
        this.sound_Txt_Var =          new Text("Sound On", 20, width_Var -40, 35, "RED", 8);
        game_Obj.addRectangle(music_Var);
        game_Obj.addRectangle(sound_Var);
        game_Obj.addText(music_Txt_Var);
        game_Obj.addText(sound_Txt_Var);
    }
    /**
     * Performs collision detection and handles the game logic.
     */

    public void collision_M(){

        if(this.puck_Obj.getYPosition() >= (this.top_Var *3 + this.bottom_Var) / 4 && puck_Obj.getYPosition() <= (this.top_Var + this.bottom_Var *3)/4){
            if      (this.puck_Obj.getXPosition() - this.puck_Obj.getSize()/2 <= this.left_Var){
                score_Added(2);
                MusicPLayer.playSound("wonSound", soundchecker);
            }
            else if (this.puck_Obj.getXPosition() + this.puck_Obj.getSize()/2 >= this.right_Var){
                score_Added(1);
                MusicPLayer.playSound("wonSound", soundchecker);
            }
        }
        if(this.puck_Obj.getYPosition() - this.puck_Obj.getSize()/2 <= this.top_Var || this.puck_Obj.getYPosition() + this.puck_Obj.getSize()/2   >= this.bottom_Var){
            this.puck_Obj.setYVelocity(puck_Obj.getYVelocity() * -1);
            MusicPLayer.playSound("hitBall", soundchecker);
        }
        else if(this.puck_Obj.getXPosition() - this.puck_Obj.getSize()/2 <= this.left_Var || this.puck_Obj.getXPosition() + this.puck_Obj.getSize()/2     >= this.right_Var){
            this.puck_Obj.setXVelocity(this.puck_Obj.getXVelocity() * -1);
            MusicPLayer.playSound("hitBall", soundchecker);
        }
        else if(Functions.isTouching(this.player1_Obj.getXPosition(),this.player1_Obj.getYPosition(), this.player1_Obj.getSize()/2 , this.puck_Obj.getXPosition(), this.puck_Obj.getYPosition(), this.puck_Obj.getSize()/2)){
            double[] out = Functions.deflect(this.puck_Obj.getXPosition(), this.puck_Obj.getYPosition(), this.puck_Obj.getXVelocity(), this.puck_Obj.getYVelocity(), this.player1_Obj.getXPosition(),this.player1_Obj.getYPosition(),this.player1_Obj.getXVelocity(),this.player1_Obj.getYVelocity());
            this.puck_Obj.setXVelocity(out[0]);
            this.puck_Obj.setYVelocity(out[1]);
        }else if(Math.pow(this.player2_Obj.getXPosition() - this.puck_Obj.getXPosition(), 2) + Math.pow(this.player2_Obj.getYPosition() - this.puck_Obj.getYPosition(), 2) <= Math.pow(this.puck_Obj.getSize()/2 + this.player2_Obj.getSize()/2,2)){
            double[] out = Functions.deflect(this.puck_Obj.getXPosition(), this.puck_Obj.getYPosition(), this.puck_Obj.getXVelocity(), this.puck_Obj.getYVelocity(), this.player2_Obj.getXPosition(),this.player2_Obj.getYPosition(),this.player2_Obj.getXVelocity(),this.player2_Obj.getYVelocity());
            this.puck_Obj.setXVelocity(out[0]);
            this.puck_Obj.setYVelocity(out[1]);
        }

        UserInterface_M("game");
    }

    /**
     * Updates the user interface based on user input.
     * @param sc The current screen or state of the game.
     */
    public void UserInterface_M(String sc){
        f_Wait++;
        if(f_Wait >= f_Wait_Var){
            if(game_Obj.leftMousePressed()) pos_Var = new int[]{game_Obj.getMousePositionX(), game_Obj.getMousePositionY()};
            if(pos_Var[0] != 0 && pos_Var[1] != 0){

                if(sc == "game"){
                    System.out.println(this.pos_Var[0] + " " + this.pos_Var[1]);

                    if(pos_Var[0] >= 140 && this.pos_Var[0] <= 340 && this.pos_Var[1] >= 5 && pos_Var[1] <= 84){
                        this.Puck_Speed_Var = !this.Puck_Speed_Var;
                        this.puck_Obj.setSp_Multiplier_Var((this.Puck_Speed_Var) ? 2.5 : 1);
                        this.puck_Speed_Var.setColour((Puck_Speed_Var) ? "GREEN" : "RED");
                        MusicPLayer.playSound("clickplay", soundchecker);
                    }

                    if(this.pos_Var[0] >= 380 && this.pos_Var[0] <= 580 && pos_Var[1] >= 5 && this.pos_Var[1] <= 84){
                        this.b_Puck_Size_Var = !this.b_Puck_Size_Var;
                        this.puck_Obj.setSize((this.b_Puck_Size_Var) ? this.puck_Obj.getSize() * 2 : this.puck_Obj.getSize() / 2);
                        this.p_Size_VAr.setColour(this.b_Puck_Size_Var ? "GREEN" : "RED");
                        MusicPLayer.playSound("clickplay", soundchecker);
                    }
                    if(this.pos_Var[0] >= 620 && this.pos_Var[0] <= 820 && this.pos_Var[1] >= 5 && this.pos_Var[1] <= 84){
                        this.b_Striker_Size_Var = !this.b_Striker_Size_Var;
                        this.player1_Obj.setSize((this.b_Striker_Size_Var) ? this.player1_Obj.getSize() * 2: this.player1_Obj.getSize() / 2);
                        this.player2_Obj.setSize((b_Striker_Size_Var) ? this.player2_Obj.getSize() * 2: this.player2_Obj.getSize() / 2);
                        this.striker_Size_Var.setColour(this.b_Striker_Size_Var ? "GREEN" : "RED");
                        MusicPLayer.playSound("clickplay", soundchecker);
                    }
                }
                if(sc == "select"){
                        System.out.println(pos_Var[0] + " " + pos_Var[1]);

                        if      (pos_Var[0] >= 140 && pos_Var[0] <= 340 && pos_Var[1] >= 270 && pos_Var[1] <= 360){
                            MusicPLayer.playSound("clickplay", soundchecker);
                            win_Score_Var++;
                        }

                        else if (pos_Var[0] >= 620 && pos_Var[0] <= 820 && pos_Var[1] >= 270 && pos_Var[1] <= 360){
                            MusicPLayer.playSound("clickplay", soundchecker);
                            if(win_Score_Var != 1) win_Score_Var--;
                        }
                        else if (pos_Var[0] >= 340 && pos_Var[0] <= 620 && pos_Var[1] >= 404 && pos_Var[1] <= 504){
                            this.selected_Var = true;
                            MusicPLayer.playSound("clickplay", soundchecker);
                        }
                        s_Var.setText(win_Score_Var + "");
                }
                if(sc == "end"){
                        System.out.println(pos_Var[0] + " " + pos_Var[1]);

                        if(pos_Var[0] >= 340 && pos_Var[0] <= 620 && pos_Var[1] >= 404 && pos_Var[1] <= 504){
                            selected_Var = true;
                            MusicPLayer.playSound("clickplay", soundchecker);
                        }
                }

                if(f_Wait >= f_Wait_Var){
                        if(this.pos_Var[0] >= 0 && this.pos_Var[0] <= 49 && this.pos_Var[1] >= 5 && this.pos_Var[1] <= 54){
                            musiccheker = !musiccheker;
                            this.music_Var.setColour(musiccheker ? "GREEN" : "RED");
                            if(musiccheker) MusicPLayer.playBackGroundMusic((float)0.2);
                            else MusicPLayer.stopBackGroundMusic();
                        }
                        if(this.pos_Var[0] >= this.width_Var -50 && this.pos_Var[0] <= this.width_Var && this.pos_Var[1] >= 5 && this.pos_Var[1] <= 54){
                            soundchecker = !soundchecker;
                            this.sound_Var.setColour(soundchecker ? "GREEN" : "RED");
                        }
                }
                pos_Var = new int[]{0,0};
                f_Wait = 0;
            }
        }
    }
    /**
     * Initializes the game with the given positions for player 1, player 2, and the puck.
     *
     * @param x1 The x-coordinate of player 1.
     * @param y1 The y-coordinate of player 1.
     * @param x2 The x-coordinate of player 2.
     * @param y2 The y-coordinate of player 2.
     */
    public void rigdes(int x1, int y1, int x2, int y2){
        player1_Obj.Body_Rigid(x1, y1);
        player2_Obj.Body_Rigid(x2, y2);
        puck_Obj.rigidBody(this.player1_Obj,this.player2_Obj, this.soundchecker);
    }
    /**
     * Adds the necessary game elements for keeping score and allows the user to select the win score.
     * Starts the game once the win score is selected.
     */
    public void addwinscore(){
        // Add rectangles and text elements for the game interface

        this.game_Obj.addRectangle(Black_board_Var);
        this.game_Obj.addRectangle(p_Var);
        this.game_Obj.addRectangle(p_m);
        this.game_Obj.addRectangle(p_confirm);
        this.game_Obj.addText(co_Var);
        this.game_Obj.addText(p_Icon_Var);
        this.game_Obj.addText(mIcon_Var);
        this.game_Obj.addText(s_Var);
        this.game_Obj.addText(max_Score_Var);
        // Initialize win score and display it

        win_Score_Var = 1;
        s_Var.setText(win_Score_Var + "");
        this.selected_Var = false;
        while(!this.selected_Var){
            UserInterface_M("select");
            game_Obj.pause();
        }
        // Remove the game interface elements

        this.game_Obj.removeRectangle(Black_board_Var);
        this.game_Obj.removeRectangle(p_Var);
        this.game_Obj.removeRectangle(p_m);
        this.game_Obj.removeRectangle(p_confirm);
        this.game_Obj.removeText(co_Var);
        this.game_Obj.removeText(p_Icon_Var);
        this.game_Obj.removeText(mIcon_Var);
        this.game_Obj.removeText(s_Var);
        this.game_Obj.removeText(max_Score_Var);
        // Start the game

        this.playGame();
    }
    /**
     * Handles the end of the game, displays the final score and the winner, and allows the user to restart the game.
     */
    public void ended_Game(){
        // Remove game elements related to the ongoing game

        game_Obj.removeRectangle(this.striker_Size_Var);
        game_Obj.removeRectangle(this.p_Size_VAr);
        game_Obj.removeRectangle(this.puck_Speed_Var);
        game_Obj.removeText(this.striker_Size_Txt_Var);
        game_Obj.removeText(this.p_SizeTxt_Var);
        game_Obj.removeText(this.p_SpeedTxt_Var);
        // Update game state for the end of the game

        this.won_Obj = true;
        game_Obj.removeBall(player1_Obj);
        game_Obj.removeBall(player2_Obj);
        game_Obj.removeBall(puck_Obj);
        this.final_Score_Var.setText(this.player1_Score_Var + " - " + this.player_2_Score_Var);
        this.Won_Var.setText("Player " + (player1_Score_Var > player_2_Score_Var ? 1 : 2) + " has won!");
        // Add elements for the end game screen

        this.game_Obj.addRectangle(Black_board_Var);
        this.game_Obj.addRectangle(restart_Var);
        this.game_Obj.addText(final_Score_Var);
        this.game_Obj.addText(restart_Txt_Var);
        this.game_Obj.addText(Won_Var);
        this.selected_Var = false;
        while(!this.selected_Var){
            UserInterface_M("end");
            game_Obj.pause();
        }
        // Remove elements from the end game screen

        this.game_Obj.removeRectangle(Black_board_Var);
        this.game_Obj.removeRectangle(restart_Var);
        this.game_Obj.removeText(final_Score_Var);
        this.game_Obj.removeText(restart_Txt_Var);
        this.game_Obj.removeText(Won_Var);
        // Restart the game

        this.addwinscore();

    }
    /**
     * Starts a new game by initializing game elements, setting the scores to zero, and adding the players and the puck.
     */
    public void playGame(){
        // Initialize scores and game state

        this.player1_Score_Var = 0; this.player_2_Score_Var = 0;
        this.won_Obj = false;
        // Remove the blackboard element

        game_Obj.removeRectangle(Black_board_Var);
        // Create players and puck

        this.player1_Obj = new Striker( (this.left_Var * 7 + this.right_Var) / 8 , (this.top_Var + this.bottom_Var) / 2 , (this.bottom_Var -this.top_Var) / 8, "BLUE", 4, 20, this.left_Var, (this.left_Var + this.right_Var) / 2, this.top_Var, this.bottom_Var);
        this.player2_Obj = new Striker( (this.left_Var + this.right_Var * 7) / 8 , (this.top_Var + this.bottom_Var) / 2 , (this.bottom_Var -this.top_Var) / 8, "BLUE", 4, 20 , (this.left_Var + this.right_Var) / 2 , this.right_Var, this.top_Var, this.bottom_Var);
        this.puck_Obj = new Puck((double)(this.left_Var + this.right_Var) / 2, (double)(this.top_Var + this.bottom_Var) / 2, (double)(this.bottom_Var -this.top_Var) / 16 , 5 , (double)0.99, this.left_Var, this.right_Var, this.top_Var, this.bottom_Var);
        // Add players and puck to the game

        game_Obj.addBall(player1_Obj);
        game_Obj.addBall(player2_Obj);
        game_Obj.addBall(puck_Obj);
        // Add score display for players

        this.display_Player1_Var = new Text("0", 50, 50, 100, "WHITE", 4);
        this.display_Player2_Var = new Text("0", 50, 870, 100, "WHITE", 4);
        game_Obj.addText(display_Player1_Var);
        game_Obj.addText(display_Player2_Var);
        game_Obj.addRectangle(this.striker_Size_Var);
        game_Obj.addRectangle(this.p_Size_VAr);
        game_Obj.addRectangle(this.puck_Speed_Var);
        game_Obj.addText(this.striker_Size_Txt_Var);
        game_Obj.addText(this.p_SizeTxt_Var);
        game_Obj.addText(this.p_SpeedTxt_Var);
        // Set color and initial values for game interface elements

        this.striker_Size_Var.setColour("RED");
        this.p_Size_VAr.setColour("RED");
        this.puck_Speed_Var.setColour("RED");
        // Reset flags and positions

        b_Striker_Size_Var = false;
        b_Puck_Size_Var = false;
        Puck_Speed_Var = false;
        f_Wait_Var = 10;
        f_Wait = 0;
        pos_Var = new int[]{0,0};
    }


    /**
     * Handles the addition of a score to a player and updates the game state accordingly.
     *
     * @param player The player who scored (1 for player 1, 2 for player 2).
     */
    public void score_Added(int player){
        MusicPLayer.playSound("wonSound", soundchecker);
        switch(player){
            case 1:
                this.player1_Score_Var++;
                this.display_Player1_Var.setText("" + this.player1_Score_Var);
                puck_Obj.setXPosition((this.left_Var + this.right_Var * 3) / 4 );
                break;
            case 2:
                this.player_2_Score_Var++;
                this.display_Player2_Var.setText("" + this.player_2_Score_Var);
                puck_Obj.setXPosition((this.left_Var * 3 + this.right_Var) / 4 );
                break;
        }
        if(this.player1_Score_Var >= this.win_Score_Var || this.player_2_Score_Var >= this.win_Score_Var){
            // Set puck position and velocity to reset the game

            puck_Obj.setYPosition((this.top_Var + this.bottom_Var) / 2 );
            puck_Obj.setXVelocity( 0 );
            puck_Obj.setYVelocity( 0 );
            player1_Obj.reset_Position();
            player2_Obj.reset_Position();
            // Remove score display for players

            game_Obj.removeText(display_Player1_Var);
            game_Obj.removeText(display_Player2_Var);
            // Handle the end of the game

            this.ended_Game();
        }else{
            // Set puck position and velocity to reset the game

            puck_Obj.setYPosition((this.top_Var + this.bottom_Var) / 2 );
            puck_Obj.setXVelocity( 0 );
            puck_Obj.setYVelocity( 0 );
            player1_Obj.reset_Position();
            player2_Obj.reset_Position();
        }
    }
}
