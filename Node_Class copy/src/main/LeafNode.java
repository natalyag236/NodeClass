package main;
import java.util.ArrayList;
import java.util.List;

/**
 * The LeafNode class represents a leaf node in a quadtree structure.
 * Leaf nodes store a list of rectangles within their defined region.
 * If the number of rectangles exceeds a certain limit, the node may split and convert to an InternalNode.
 */
public class LeafNode extends baseNode {
    /** List of rectangles contained within this leaf node's region. */
    private List<Rectangle> rectangles;

    /**
     * Constructs a LeafNode with the specified position and dimensions.
     * Initializes an empty list of rectangles within the node.
     *
     * @param x The x-coordinate of the leaf node's position.
     * @param y The y-coordinate of the leaf node's position.
     * @param width The width of the leaf node's region.
     * @param height The height of the leaf node's region.
     */
    public LeafNode(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.rectangles = new ArrayList<>();
    }

    /**
     * Checks if a point is within the boundaries of this leaf node's region.
     *
     * @param x The x-coordinate of the point to check.
     * @param y The y-coordinate of the point to check.
     * @return true if the point is within the node's region, false otherwise.
     */
    @Override
    public boolean contains(double x, double y) {
        return x >= this.x && x < this.x + width &&
               y >= this.y && y < this.y + height;
    }

    /**
     * Inserts a rectangle into this leaf node.
     * If the number of rectangles exceeds a threshold, the leaf node may split into an InternalNode.
     *
     * @param rect The rectangle to insert.
     */
    @Override
    public void insert(Rectangle rect) {
        if (rectangles.size() >= 5) {
            // Code to handle splitting of the leaf node.
            // This would likely involve creating an InternalNode to replace this LeafNode.
        } else {
            rectangles.add(rect);
        }
    }

    /**
     * Searches for a rectangle at the specified coordinates within this leaf node.
     *
     * @param x The x-coordinate of the rectangle to find.
     * @param y The y-coordinate of the rectangle to find.
     * @return The rectangle if found, or null if not found.
     */
    @Override
    public Rectangle find(double x, double y) {
        for (Rectangle rect : rectangles) {
            if (rect.contains(x, y)) {
                return rect;
            }
        }
        System.out.println("Nothing is at (" + x + ", " + y + ").");
        return null;
    }

    /**
     * Deletes a rectangle at the specified coordinates within this leaf node.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    @Override
    public void delete(double x, double y) {
        rectangles.removeIf(rect -> rect.contains(x, y));
    }

    /**
     * Updates the dimensions of a rectangle at the specified coordinates.
     * Deletes the existing rectangle, then inserts a new one with updated dimensions.
     *
     * @param x The x-coordinate of the rectangle to update.
     * @param y The y-coordinate of the rectangle to update.
     * @param newWidth The new width of the rectangle.
     * @param newHeight The new height of the rectangle.
     */
    @Override
    public void update(double x, double y, double newWidth, double newHeight) {
        Rectangle rect = find(x, y);
        if (rect != null) {
            rectangles.remove(rect);
            rectangles.add(new Rectangle(x, y, newWidth, newHeight));
        }
    }

    /**
     * Dumps the leaf node's structure for debugging or visualization, showing its level in the quadtree
     * and the list of rectangles within it.
     *
     * @param level The depth level of the node in the quadtree, used for indentation.
     */
    @Override
    public void dump(int level) {
        String indent = " ".repeat(level * 4);
        System.out.println(indent + "Leaf Node - " + rectangles);
    }
}
