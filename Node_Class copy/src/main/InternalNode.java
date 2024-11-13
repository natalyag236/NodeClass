package main;

/**
 * The InternalNode class represents an internal node in a quadtree.
 * Internal nodes have four child nodes, representing the four quadrants of their region.
 * Each child node is initialized as a LeafNode, which can further subdivide into internal nodes as needed.
 */
public class InternalNode extends baseNode {
    /** Array of child nodes for each quadrant of the internal node's region. */
    private baseNode[] children;

    /**
     * Constructs an InternalNode with the specified position and dimensions.
     * Initializes four child nodes, each representing one of the four quadrants of this node's region.
     *
     * @param x The x-coordinate of the internal node's position.
     * @param y The y-coordinate of the internal node's position.
     * @param width The width of the internal node's region.
     * @param height The height of the internal node's region.
     */
    public InternalNode(double x, double y, double width, double height) {
        super(x, y, width, height);
        double halfWidth = width / 2;
        double halfHeight = height / 2;

      
        children = new baseNode[4];
        children[0] = new LeafNode(x, y + halfHeight, halfWidth, halfHeight); // Top-left
        children[1] = new LeafNode(x + halfWidth, y + halfHeight, halfWidth, halfHeight); // Top-right
        children[2] = new LeafNode(x, y, halfWidth, halfHeight); // Bottom-left
        children[3] = new LeafNode(x + halfWidth, y, halfWidth, halfHeight); // Bottom-right
    }

    /**
     * Checks if a point is within the boundaries of this internal node's region.
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
     * Inserts a rectangle into the appropriate child node based on its coordinates.
     *
     * @param rect The rectangle to insert.
     */
    @Override
    public void insert(Rectangle rect) {
        for (baseNode child : children) {
            if (child.contains(rect.getX(), rect.getY())) {
                child.insert(rect);
                return;
            }
        }
    }

    /**
     * Searches for a rectangle at the specified coordinates within this node or its children.
     *
     * @param x The x-coordinate of the rectangle to find.
     * @param y The y-coordinate of the rectangle to find.
     * @return The rectangle if found, or null if not found.
     */
    @Override
    public Rectangle find(double x, double y) {
        for (baseNode child : children) {
            if (child.contains(x, y)) {
                return child.find(x, y);
            }
        }
        System.out.println("Nothing is at (" + x + ", " + y + ").");
        return null;
    }

    /**
     * Deletes a rectangle at the specified coordinates within this node or its children.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    @Override
    public void delete(double x, double y) {
        for (baseNode child : children) {
            child.delete(x, y);
        }
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
            delete(x, y);
            insert(new Rectangle(x, y, newWidth, newHeight));
        }
    }

    /**
     * Dumps the internal node's structure for debugging or visualization, 
     * showing its level in the quadtree and each child node.
     *
     * @param level The depth level of the node in the quadtree, used for indentation.
     */
    @Override
    public void dump(int level) {
        String indent = " ".repeat(level * 4);
        System.out.println(indent + "Internal Node");
        for (baseNode child : children) {
            child.dump(level + 1);
        }
    }
}
