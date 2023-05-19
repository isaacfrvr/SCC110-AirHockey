/**
 * This class represents a puck in the game. It inherits from the `Ball` class and adds specific properties and behavior for a puck.
 */
public class Puck extends Ball {
    private double x_Velocity_Var = 0, y_Velocity_Var = 0, friction_Var = 0.9, sp_Multiplier_Var = 1;
    private int l_Bound_Var, r_Bound_Var, t_Bound_Var, b_Bound_Var;

    /**
     * Constructs a Puck object with the specified position, diameter, layer, friction, and boundary values.
     *
     * @param x            The x-coordinate of the puck's position.
     * @param y            The y-coordinate of the puck's position.
     * @param diameter     The diameter of the puck.
     * @param layer        The layer of the puck.
     * @param friction_Var The friction value of the puck.
     * @param l_Bound_Var  The left boundary of the game area.
     * @param r_Bound_Var  The right boundary of the game area.
     * @param t_Bound_Var  The top boundary of the game area.
     * @param b_Bound_Var  The bottom boundary of the game area.
     */
    public Puck(double x, double y, double diameter, int layer, double friction_Var, int l_Bound_Var, int r_Bound_Var, int t_Bound_Var, int b_Bound_Var) {
        super(x, y, diameter, "BLACK", layer);
        this.friction_Var = friction_Var;
        this.l_Bound_Var = l_Bound_Var;
        this.r_Bound_Var = r_Bound_Var;
        this.b_Bound_Var = b_Bound_Var;
        this.t_Bound_Var = t_Bound_Var;
    }

    /**
     * Sets the speed multiplier value for the puck.
     *
     * @param speed The speed multiplier value.
     */
    public void setSp_Multiplier_Var(double speed) {
        this.sp_Multiplier_Var = speed;
    }

    /**
     * Updates the position and velocity of the puck based on collisions with the players and the game boundaries.
     *
     * @param player1 The first player (striker) object.
     * @param player2 The second player (striker) object.
     * @param sfx     Flag indicating whether sound effects should be played.
     */
    public void rigidBody(Striker player1, Striker player2, Boolean sfx) {
        super.setXPosition(super.getXPosition() + this.x_Velocity_Var * sp_Multiplier_Var);
        super.setYPosition(super.getYPosition() + this.y_Velocity_Var * sp_Multiplier_Var);
        this.x_Velocity_Var *= this.friction_Var;
        this.y_Velocity_Var *= this.friction_Var;

        if (Functions.isTouching(super.getXPosition(), super.getYPosition(), super.getSize() / 2, player1.getXPosition(), player1.getYPosition(), player1.getSize() / 2)) {
            // Handle collision with player 1 (striker)
            double[] vector_Var = new double[]{super.getXPosition() - player1.getXPosition(), super.getYPosition() - player1.getYPosition()};
            vector_Var = Functions.normalizeVector(vector_Var);
            double velocity_Var = Math.sqrt(this.x_Velocity_Var * this.x_Velocity_Var + this.y_Velocity_Var * this.y_Velocity_Var);
            this.x_Velocity_Var = velocity_Var * vector_Var[0];
            this.y_Velocity_Var = velocity_Var * vector_Var[1];
            vector_Var = new double[]{vector_Var[0] * (super.getSize() / 2 + player1.getSize() / 2), vector_Var[1] * (super.getSize() / 2 + player1.getSize() / 2)};
            super.setXPosition(player1.getXPosition() + vector_Var[0]);
            super.setYPosition(player1.getYPosition() + vector_Var[1]);
            MusicPLayer.playSound("hitBall", sfx);
        }

        if (Functions.isTouching(super.getXPosition(), super.getYPosition(), super.getSize() / 2, player2.getXPosition(), player2.getYPosition(), player2.getSize() / 2)) {
            // Handle collision with player 2 (striker)
            double[] vector_Var_Var = new double[]{super.getXPosition() - player2.getXPosition(), super.getYPosition() - player2.getYPosition()};
            vector_Var_Var = Functions.normalizeVector(vector_Var_Var);
            double velocity_Var = Math.sqrt(this.x_Velocity_Var * this.x_Velocity_Var + this.y_Velocity_Var * this.y_Velocity_Var);
            this.x_Velocity_Var = velocity_Var * vector_Var_Var[0];
            this.y_Velocity_Var = velocity_Var * vector_Var_Var[1];
            vector_Var_Var = new double[]{vector_Var_Var[0] * (super.getSize() / 2 + player2.getSize() / 2), vector_Var_Var[1] * (super.getSize() / 2 + player2.getSize() / 2)};
            super.setXPosition(player2.getXPosition() + vector_Var_Var[0]);
            super.setYPosition(player2.getYPosition() + vector_Var_Var[1]);
            MusicPLayer.playSound("hitBall", sfx);
        }

        if (super.getYPosition() - super.getSize() / 2 <= this.t_Bound_Var)
            super.setYPosition(this.t_Bound_Var + super.getSize() / 2);

        if (super.getYPosition() + super.getSize() / 2 >= this.b_Bound_Var)
            super.setYPosition(this.b_Bound_Var - super.getSize() / 2);

        if (super.getXPosition() - super.getSize() / 2 <= this.l_Bound_Var)
            super.setXPosition(this.l_Bound_Var + super.getSize() / 2);

        if (super.getXPosition() + super.getSize() / 2 >= this.r_Bound_Var)
            super.setXPosition(this.r_Bound_Var - super.getSize() / 2);
    }

    /**
     * Returns the current velocity in the x-direction of the puck.
     *
     * @return The x-velocity of the puck.
     */
    public double getXVelocity() {
        return this.x_Velocity_Var;
    }

    /**
     * Returns the current velocity in the y-direction of the puck.
     *
     * @return The y-velocity of the puck.
     */
    public double getYVelocity() {
        return this.y_Velocity_Var;
    }

    /**
     * Returns the friction value of the puck.
     *
     * @return The friction value.
     */
    public double getFriction_Var() {
        return this.friction_Var;
    }

    /**
     * Sets the velocity in the x-direction of the puck.
     *
     * @param numbr The x-velocity value to set.
     */
    public void setXVelocity(double numbr) {
        this.x_Velocity_Var = numbr;
    }

    /**
     * Sets the velocity in the y-direction of the puck.
     *
     * @param numbr The y-velocity value to set.
     */
    public void setYVelocity(double numbr) {
        this.y_Velocity_Var = numbr;
    }
    /**
     * Sets the Friction value of the puck.
     *
     * @param numbr The Friction value to set.
     */

    public void setFriction_Var(double numbr){
        this.friction_Var = numbr;
    }
}
