package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.LeafNode;
import main.Rectangle;
import main.baseNode;

/**
 * Test class for the quadtree data structure, covering insertions, finds, updates,
 * splits, and deletions of rectangles in leaf and internal nodes.
 */
class Quadtree {

    /** The root node of the quadtree, initially set as a LeafNode. */
    private baseNode root;

    /**
     * Sets up a fresh quadtree before each test.
     * Initializes the root as a LeafNode spanning a region from (-50, -50) to (50, 50).
     */
    @BeforeEach
    public void setup() {
        root = new LeafNode(-50, -50, 100, 100);
    }

    /**
     * Tests insertion of a rectangle and verifies it can be found at the specified coordinates.
     */
    @Test
    public void testInsertAndFind() {
        Rectangle rect = new Rectangle(-20, -20, 15, 15);  // Insert at a different position
        root.insert(rect);

        Rectangle found = root.find(-20, -20);
        assertNotNull(found, "Rectangle should be found at (-20, -20)");
        assertEquals(rect, found, "Found rectangle should match the inserted rectangle");
    }

    /**
     * Tests that the quadtree splits when enough rectangles are inserted to exceed the capacity.
     * Verifies the split by examining the printed structure.
     */
    @Test
    public void testSplitAndDump() {
        // Insert various rectangles to fill up and trigger a split in the quadtree
        root.insert(new Rectangle(-40, -40, 10, 10));
        root.insert(new Rectangle(20, 20, 8, 8));
        root.insert(new Rectangle(-10, 30, 12, 12));
        root.insert(new Rectangle(45, 45, 6, 6));
        root.insert(new Rectangle(30, -25, 5, 5));
        root.insert(new Rectangle(-30, -10, 7, 7)); // Should trigger a split here

        // Check the quadtree structure after split; output can be reviewed for correct structure
        root.dump(0);
    }

    /**
     * Tests updating a rectangle's dimensions and verifies that the update was successful.
     * Confirms that the modified rectangle can still be found at the same coordinates.
     */
    @Test
    public void testUpdate() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        root.insert(rect);

        root.update(0, 0, 20, 20);
        Rectangle updatedRect = root.find(0, 0);
        assertNotNull(updatedRect, "Updated rectangle should be found at (0, 0)");
        assertEquals(20, updatedRect.getWidth(), "Width should be updated to 20");
        assertEquals(20, updatedRect.getHeight(), "Height should be updated to 20");
    }

    /**
     * Tests deletion of a rectangle and verifies that it is no longer retrievable
     * from the quadtree at the specified coordinates.
     */
    @Test
    public void testDelete() {
        Rectangle rect = new Rectangle(-15, -15, 8, 8);
        root.insert(rect);

        root.delete(-15, -15);
        Rectangle deletedRect = root.find(-15, -15);
        assertNull(deletedRect, "Rectangle should be deleted from (-15, -15)");
    }

    /**
     * Tests complex operations by inserting multiple rectangles, performing updates,
     * deletions, and finding multiple shapes to ensure quadtree integrity.
     */
    @Test
    public void testComplexOperations() {
        // Insert a variety of rectangles
        Rectangle rect1 = new Rectangle(-25, -25, 10, 10);
        Rectangle rect2 = new Rectangle(15, 15, 8, 8);
        Rectangle rect3 = new Rectangle(30, 30, 5, 5);
        Rectangle rect4 = new Rectangle(5, 5, 12, 12);

        root.insert(rect1);
        root.insert(rect2);
        root.insert(rect3);
        root.insert(rect4);

        // Update rect1's dimensions
        root.update(-25, -25, 20, 20);
        Rectangle updatedRect1 = root.find(-25, -25);
        assertEquals(20, updatedRect1.getWidth(), "rect1's width should be updated to 20");

        // Delete rect3
        root.delete(30, 30);
        Rectangle deletedRect3 = root.find(30, 30);
        assertNull(deletedRect3, "rect3 should be deleted from (30, 30)");

        // Check if remaining rectangles are found correctly
        assertNotNull(root.find(15, 15), "rect2 should be found at (15, 15)");
        assertNotNull(root.find(5, 5), "rect4 should be found at (5, 5)");
    }
}
