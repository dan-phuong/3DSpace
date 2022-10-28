public class Computation {
    static double drawX = 0, drawY = 0, distance = 0;
    public static double calculatePositionX(double[] viewFrom, double[] viewTo, double x, double y, double z){
        initialize(viewFrom, viewTo, x, y, z);
        return drawX;
    }

    public static double calculatePositionY(double[] viewFrom, double[] viewTo, double x, double y, double z){
        initialize(viewFrom, viewTo, x, y, z);
        return drawY;
    }

    public static void initialize(double[] viewFrom, double[] viewTo, double x, double y, double z){
        /**
         * viewTo[0] is x coordinate, viewTo[1] is y coordinate, 
         * viewFrom[0] is x coordinate
         */
        Vector viewVector = new Vector(viewTo[0] - viewFrom[0], viewTo[1] - viewFrom[1], viewTo[2] - viewFrom[2]);
        Vector directionVector = new Vector(1,1,1);

        Vector planeVector1 = viewVector.crossProduct(directionVector);
        Vector planeVector2 = viewVector.crossProduct(planeVector1);

        Vector ViewToPoint = new Vector(x - viewFrom[0], y - viewFrom[1], z - viewFrom[2]);
		
		distance = (viewVector.x * viewTo[0] + viewVector.y*viewTo[1] + viewVector.z*viewTo[2]
			   	-  (viewVector.x * viewFrom[0] + viewVector.y*viewFrom[1] + viewVector.z*viewFrom[2]))
				/  (viewVector.x * ViewToPoint.x + viewVector.y*ViewToPoint.y + viewVector.z*ViewToPoint.z);
		
		x = viewFrom[0] + ViewToPoint.x * distance;
		y = viewFrom[1] + ViewToPoint.y * distance;
		z = viewFrom[2] + ViewToPoint.z * distance;
		
		if(distance >= 0)
		{
			drawX = planeVector2.x * x + planeVector2.y * y + planeVector2.z * z;
			drawY = planeVector1.x * x + planeVector1.y * y + planeVector1.z * z;
		}
    }
}
