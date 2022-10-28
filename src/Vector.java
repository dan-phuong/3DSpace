public class Vector {
    double x = 0, y = 0, z = 0;
    public Vector(double x, double y, double z){
        /*
         * Make all vectors of length 1
         */
        double length = Math.sqrt(x*x + y*y + z*z);
        if(length >0){
            this.x = x/length;
            this.y = y/length;
            this.z = z/length;
        }
    }

    /**
     * Creates new vector orthogonal to the two vectors
     * @param vector - Vector
     * @return cross product
     */
    public Vector crossProduct(Vector vector){
        Vector crossVector = new Vector(
            y*vector.z - z*vector.y,
            z*vector.x - x*vector.z,
            x*vector.y - y*vector.x);
        return crossVector;
    }
}
