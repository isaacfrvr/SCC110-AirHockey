public class Functions {
    /**
     * Calculates the deflection of two objects based on their positions and velocities during an impact.
     *
     * @param xPosition1 The x-coordinate of object 1's position.
     * @param yPosition1 The y-coordinate of object 1's position.
     * @param xSpeed1 The x-component of object 1's velocity.
     * @param ySpeed1 The y-component of object 1's velocity.
     * @param xPosition2 The x-coordinate of object 2's position.
     * @param yPosition2 The y-coordinate of object 2's position.
     * @param xSpeed2 The x-component of object 2's velocity.
     * @param ySpeed2 The y-component of object 2's velocity.
     * @return An array of doubles representing the updated velocities of the two objects after the deflection.
     */
    public static double[] deflect(double xPosition1, double yPosition1, double xSpeed1, double ySpeed1, double xPosition2, double yPosition2, double xSpeed2, double ySpeed2)
    {
        // Calculate initial momenta of objects

        double p1InitialMomentum = Math.sqrt(xSpeed1 * xSpeed1 + ySpeed1 * ySpeed1);
        double p2InitialMomentum = Math.sqrt(xSpeed2 * xSpeed2 + ySpeed2 * ySpeed2);

        // Create trajectory vectors for objects

        double[] p1Trajectory = {xSpeed1, ySpeed1};
        double[] p2Trajectory = {xSpeed2, ySpeed2};
        // Calculate impact vector between objects

        double[] impactVector = {xPosition2 - xPosition1, yPosition2 - yPosition1};
        // Normalize the impact vector

        double[] impactVectorNorm = normalizeVector(impactVector);
        // Calculate dot products of trajectories with the impact vector

        double p1dotImpact = Math.abs(p1Trajectory[0] * impactVectorNorm[0] + p1Trajectory[1] * impactVectorNorm[1]);
        double p2dotImpact = Math.abs(p2Trajectory[0] * impactVectorNorm[0] + p2Trajectory[1] * impactVectorNorm[1]);
        // Calculate deflect vectors for each object

        double[] p1Deflect = { -impactVectorNorm[0] * p2dotImpact, -impactVectorNorm[1] * p2dotImpact };
        double[] p2Deflect = { impactVectorNorm[0] * p1dotImpact, impactVectorNorm[1] * p1dotImpact };
        // Calculate final trajectories for each object

        double[] p1FinalTrajectory = {p1Trajectory[0] + p1Deflect[0] - p2Deflect[0], p1Trajectory[1] + p1Deflect[1] - p2Deflect[1]};
        double[] p2FinalTrajectory = {p2Trajectory[0] + p2Deflect[0] - p1Deflect[0], p2Trajectory[1] + p2Deflect[1] - p1Deflect[1]};
        // Calculate final momenta of objects

        double p1FinalMomentum = Math.sqrt(p1FinalTrajectory[0] * p1FinalTrajectory[0] + p1FinalTrajectory[1] * p1FinalTrajectory[1]);
        double p2FinalMomentum = Math.sqrt(p2FinalTrajectory[0] * p2FinalTrajectory[0] + p2FinalTrajectory[1] * p2FinalTrajectory[1]);
        // Calculate the magnitude ratio of initial and final momenta


        double mag = (p1InitialMomentum + p2InitialMomentum) / (p1FinalMomentum + p2FinalMomentum);
        // Update velocities of the objects

        xSpeed1 = p1FinalTrajectory[0] * mag;
        ySpeed1 = p1FinalTrajectory[1] * mag;
        xSpeed2 = p2FinalTrajectory[0] * mag;
        ySpeed2 = p2FinalTrajectory[1] * mag;
        // Handle NaN (Not-a-Number) values for velocities

        Double x1 = new Double(xSpeed1);
        Double y1 = new Double(ySpeed1);
        Double x2 = new Double(xSpeed2);
        Double y2 = new Double(ySpeed2);
        // Create an array with updated velocities

        double[] list = {(x1.isNaN() ? 0 : x1),(y1.isNaN() ? 0 : y1),(x2.isNaN() ? 0 : x2),(y2.isNaN() ? 0 : y2)};
        return(list);
    }
    /**
     * Normalizes a given vector.
     *
     * @param vec The vector to be normalized.
     * @return An array of doubles representing the normalized vector.
     */
    public static double[] normalizeVector(double[] vec)
    {
        double mag = 0.0;
        int dimensions = vec.length;
        double[] result = new double[dimensions];
        // Calculate the magnitude of the vector

        for (int i=0; i < dimensions; i++)
            mag += vec[i] * vec[i];
        mag = Math.sqrt(mag);
        // Handle the case when magnitude is 0

        if (mag == 0.0)
        {
            result[0] = 1.0;
            for (int i=1; i < dimensions; i++)
            result[i] = 0.0;
        }
        else
        {
            // Normalize the vector

            for (int i=0; i < dimensions; i++)
            result[i] = vec[i] / mag;
        }
        return result;
    }
    /**
     * Checks if two circles are touching or overlapping.
     *
     * @param x1 The x-coordinate of the center of circle 1.
     * @param y1 The y-coordinate of the center of circle 1.
     * @param r1 The radius of circle 1.
     * @param x2 The x-coordinate of the center of circle 2.
     * @param y2 The y-coordinate of the center of circle 2.
     * @param r2 The radius of circle 2.
     * @return A boolean value indicating if the circles are touching or overlapping.
     */
    public static boolean isTouching(double x1, double y1, double r1, double x2, double y2, double r2){
        // Calculate the distance between the centers of the two circles

        double a = x1 - x2;
        double b = y1 - y2;
        // Check if the distance is less than or equal to the sum of the radii

        return(Math.pow(a,2) + Math.pow(b,2) <= Math.pow(r1 + r2, 2));
    }
}

