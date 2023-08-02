public class Render {
    static double drawX = 0, drawY = 0;

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
        //Vector directionVector = new Vector(1,1,1);

        //Vector planeVector1 = viewVector.crossProduct(directionVector);
        //Vector planeVector2 = viewVector.crossProduct(planeVector1);

        Vector rotationVector = getRotationVector(viewFrom, viewTo);
        Vector rotationAxisVector = viewVector.crossProduct(rotationVector);
        Vector rotationAxisVector2 = viewVector.crossProduct(rotationAxisVector);

        Vector viewToPoint = new Vector(x - viewFrom[0], y - viewFrom[1], z - viewFrom[2]);
		
		double distance = (viewVector.x * viewTo[0] + viewVector.y*viewTo[1] + viewVector.z*viewTo[2]
			   	-  (viewVector.x * viewFrom[0] + viewVector.y*viewFrom[1] + viewVector.z*viewFrom[2]))
				/  (viewVector.x * viewToPoint.x + viewVector.y*viewToPoint.y + viewVector.z*viewToPoint.z);
		
		x = viewFrom[0] + viewToPoint.x * distance;
		y = viewFrom[1] + viewToPoint.y * distance;
		z = viewFrom[2] + viewToPoint.z * distance;
		
		if(distance > 0)
		{
			drawX = rotationAxisVector2.x * x + rotationAxisVector2.y * y + rotationAxisVector2.z * z;
			drawY = rotationAxisVector.x * x + rotationAxisVector.y * y + rotationAxisVector.z * z;
		}
    }

    public static Vector getRotationVector(double[] viewFrom, double[] viewTo){
        double dx = Math.abs(viewFrom[0]-viewTo[0]);
		double dy = Math.abs(viewFrom[1]-viewTo[1]);
		double xRot, yRot;
		
		xRot=dy/(dx+dy);		
		yRot=dx/(dx+dy);
		
		if(viewFrom[1]>viewTo[1])
			xRot = -xRot;
		if(viewFrom[0]<viewTo[0])
			yRot = -yRot;

		Vector V = new Vector(xRot, yRot, 0);
		return V;  
    }
}
