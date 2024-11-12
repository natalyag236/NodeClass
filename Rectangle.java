package main;
/**
 * The Rectangle class represents a rectangle defined by its position and dimensions.
 * It includes methods to check if a point is contained within the rectangle
 * and to detect overlap with another rectangle.
 */
public class Rectangle {
    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * Constructs a Rectangle with the specified position and dimensions.
     *
     * @param x The x-coordinate of the rectangle's top-left corner.
     * @param y The y-coordinate of the rectangle's top-left corner.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

 

    /**
     * @return The x-coordinate of the rectangle's top-left corner.
     */
    public double getX() { return x; }

    /**
     * @return The y-coordinate of the rectangle's top-left corner.
     */
    public double getY() { return y; }

    /**
     * @return The width of the rectangle.
     */
    public double getWidth() { return width; }

    /**
     * @return The height of the rectangle.
     */
    public double getHeight() { return height; }

    /**
     * Checks if a point is within the boundaries of the rectangle.
     *
     * @param px The x-coordinate of the point to check.
     * @param py The y-coordinate of the point to check.
     * @return true if the point is within the rectangle, false otherwise.
     */
    public boolean contains(double px, double py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    /**
     * Checks if this rectangle overlaps with another rectangle.
     *
     * @param other The other rectangle to check for overlap.
     * @return true if the rectangles overlap, false otherwise.
     */
    public boolean overlaps(Rectangle other) {
        return !(x + width < other.getX() || x > other.getX() + other.getWidth() ||
                 y + height < other.getY() || y > other.getY() + other.getHeight());
    }

    /**
     * Provides a string representation of the rectangle, including its position and dimensions.
     *
     * @return A string representing the rectangle.
     */
    @Override
    public String toString() {
        return String.format("Rectangle at (%.2f, %.2f): %.2fx%.2f", x, y, width, height);
    }
}
