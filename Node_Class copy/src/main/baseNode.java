package main;

/**
 * The baseNode class serves as an abstract base class for nodes in a quadtree structure.
 * It provides fundamental properties and methods for managing spatial data.
 */
public abstract class baseNode {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    /**
     * Constructs a baseNode with the specified position and dimensions.
     *
     * @param x The x-coordinate of the node's position.
     * @param y The y-coordinate of the node's position.
     * @param width The width of the node's region.
     * @param height The height of the node's region.
     */
    public baseNode(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Inserts a rectangle into the node's region. Implementation will vary depending on the type of node.
     *
     * @param rect The rectangle to insert into the node.
     */
    public abstract void insert(Rectangle rect);

    /**
     * Deletes a rectangle at the specified position within the node's region.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    public abstract void delete(double x, double y);

    /**
     * Finds and returns a rectangle located at the specified coordinates within the node.
     *
     * @param x The x-coordinate of the rectangle to find.
     * @param y The y-coordinate of the rectangle to find.
     * @return The rectangle at the specified coordinates, or null if not found.
     */
    public abstract Rectangle find(double x, double y);

    /**
     * Updates the dimensions of a rectangle located at the specified coordinates.
     * Deletes the existing rectangle, then inserts a new one with the updated dimensions.
     *
     * @param x The x-coordinate of the rectangle to update.
     * @param y The y-coordinate of the rectangle to update.
     * @param newWidth The new width of the rectangle.
     * @param newHeight The new height of the rectangle.
     */
    public abstract void update(double x, double y, double newWidth, double newHeight);

    /**
     * Dumps the node's information for debugging or visualization purposes.
     *
     * @param level The depth level of the node in the quadtree.
     */
    public abstract void dump(int level);

    /**
     * Checks if a point specified by its coordinates is within the boundaries of this node's region.
     *
     * @param px The x-coordinate of the point to check.
     * @param py The y-coordinate of the point to check.
     * @return true if the point is within this node's region, false otherwise.
     */
    public boolean contains(double px, double py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }
}
