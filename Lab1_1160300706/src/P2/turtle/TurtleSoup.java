/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i=0;i<4;i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(90.00);
    	}
    		
    }  

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
    	if(sides<3)
    	{
    		System.out.println("Sides must be > 2.");
    		return 0;
    	}
    	double angle = (sides-2) * 180.0 / sides;
    	return angle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //throw new RuntimeException("implement me!");
    	if(angle<=0 || angle >=180)
    	{
    		System.out.println("Angle must be > 0 and < 180.");
    		return 0;
    	}
        int sides = 360/(int)(180 - angle);
        return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        //throw new RuntimeException("implement me!");
    	double angle = calculateRegularPolygonAngle(sides);
    	turtle.turn(270);
    	turtle.forward(sideLength);
    	for(int i=0;i<sides-1;i++)
    	{
    		turtle.turn(180-angle);
    		turtle.forward(sideLength);
    	}
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate   
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        //throw new RuntimeException("implement me!");
    	int x,y;
    	x = Math.abs(currentX - targetX);
    	y = Math.abs(currentY - targetY);
    	double length = Math.sqrt((x*x + y*y)*1.0);
    	double angle;
    	if(currentX <= targetX && currentY < targetY) //1象限
    		angle = Math.asin(x/length)/Math.PI*180;
    	else if(currentX > targetX && currentY <= targetY)//2象限
    		angle = Math.asin(y/length)/Math.PI*180 + 270;
    	else if(currentX >= targetX && currentY > targetY)//3象限
    		angle = Math.asin(x/length)/Math.PI*180 + 180;
    	else  //4象限
    		angle = Math.asin(y/length)/Math.PI*180 + 90;
    	if(angle >= currentHeading)
    		return angle - currentHeading;
    	else
    		return 360+angle-currentHeading;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	int length = xCoords.size()-1;
    	List<Double>array = new ArrayList<Double>();
    	double angle=0;
    	for(int i=0;i<length;i++)
    	{
    		angle = calculateHeadingToPoint(angle,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
    		array.add(angle);
    	}
    	return array;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    /**
     * 五角星
     * */
    public static void drawPersonalArt(Turtle turtle) {
        //throw new RuntimeException("implement me!");
    	turtle.turn(90);
    	turtle.forward(90);
    	for(int i=0;i<4;i++)
    	{
    		turtle.turn(144);
    		turtle.forward(90);
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        drawSquare(turtle, 40);
        //draw the window
        turtle.draw();
    }

}
