/**
 * This class represents a striker in the game. It inherits from the `Ball` class and adds specific properties and behavior for a striker.
 */
public class Striker extends Ball {
    private double sp_Var = 0;
    private double x_Velocity_VAr, y_Velocity_Var;
    private int l_Bound_Var, r_Bound_Var, tBound_Var, b_Bound_Var;
    private double[] spawn_Var;

    /**
     * Constructs a Striker object with the specified position, diameter, color, layer, speed, and boundary values.
     *
     * @param x            The x-coordinate of the striker's position.
     * @param y            The y-coordinate of the striker's position.
     * @param diameter     The diameter of the striker.
     * @param col          The color of the striker.
     * @param layer        The layer of the striker.
     * @param sp_Var       The speed of the striker.
     * @param l_Bound_Var  The left boundary of the game area.
     * @param r_Bound_Var  The right boundary of the game area.
     * @param tBound_Var   The top boundary of the game area.
     * @param b_Bound_Var  The bottom boundary of the game area.
     */
    public Striker(double x, double y, double diameter, String col, int layer, double sp_Var, int l_Bound_Var, int r_Bound_Var, int tBound_Var, int b_Bound_Var) {
        super(x, y, diameter, col, layer);
        this.sp_Var = sp_Var;
        this.spawn_Var = new double[]{x, y};
        this.x_Velocity_VAr = -10;
        this.l_Bound_Var = l_Bound_Var;
        this.r_Bound_Var = r_Bound_Var;
        this.b_Bound_Var = b_Bound_Var;
        this.tBound_Var = tBound_Var;
    }

    /**
     * Initializes the striker with the specified velocity in the x-axis and y-axis.
     *
     * @param x_Axis The velocity in the x-axis.
     * @param y_Axis The velocity in the y-axis.
     */
    public void init(int x_Axis, int y_Axis) {
        double sp_Var = 0.7072;
        if (x_Axis != 0 && y_Axis != 0) {
            this.x_Velocity_VAr = x_Axis * sp_Var * this.sp_Var;
            this.y_Velocity_Var = y_Axis * sp_Var * this.sp_Var;
        } else {
            this.x_Velocity_VAr = x_Axis * this.sp_Var;
            this.y_Velocity_Var = y_Axis * this.sp_Var;
        }
    }

    /**
     * Resets the position of the striker to its initial spawn position.
     */
    public void reset_Position() {
        super.setXPosition(this.spawn_Var[0]);
        super.setYPosition(this.spawn_Var[1]);
    }

    /**
     * Handles the rigid body behavior of the striker, including movement and boundary collisions.
     *
     * @param x_Axis The movement direction in the x-axis (-1 for left, 1 for right).
     * @param y_Axis The movement direction in the y-axis (-1 for up, 1 for down).
     */
    public void Body_Rigid(int x_Axis, int y_Axis) {
        init(x_Axis, y_Axis);
        super.setXPosition(super.getXPosition() + x_Velocity_VAr);
        super.setYPosition(super.getYPosition() + y_Velocity_Var);

        if (super.getYPosition() - super.getSize() / 2 <= this.tBound_Var)
            super.setYPosition(this.tBound_Var + super.getSize() / 3 + 6);

        if (super.getYPosition() + super.getSize() / 2 >= this.b_Bound_Var)
            super.setYPosition(this.b_Bound_Var - super.getSize() / 3 - 6);

        if (super.getXPosition() - super.getSize() / 2 <= this.l_Bound_Var)
            super.setXPosition(this.l_Bound_Var + super.getSize() / 3 + 6);

        if (super.getXPosition() + super.getSize() / 2 >= this.r_Bound_Var)
            super.setXPosition(this.r_Bound_Var - super.getSize() / 3 - 6);
    }

    /**
     * Returns the current velocity in the x-axis of the striker.
     *
     * @return The x-velocity of the striker.
     */
    public double getXVelocity() {
        return x_Velocity_VAr;
    }

    /**
     * Returns the current velocity in the y-axis of the striker.
     *
     * @return The y-velocity of the striker.
     */
    public double getYVelocity() {
        return y_Velocity_Var;
    }
}
