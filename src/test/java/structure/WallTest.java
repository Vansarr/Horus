package structure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private static final Brick brick1 = new Brick("color1", "material1");
    private static final Brick brick2 = new Brick("color2", "material2");
    private static final Brick brick3 = new Brick("color3", "material3");
    private static final Brick brick4 = new Brick("color4", "material4");
    private static final CompositeBrick compBrick1 = new CompositeBrick("color1", "material1");
    private static final CompositeBrick compBrick2 = new CompositeBrick("color2", "material2");

    private Wall wall = new Wall();
    private Wall emptyWall = new Wall();

    @BeforeAll
    static void setupComposites() {
        compBrick1.addBlock(brick3);
        compBrick1.addBlock(compBrick2);

        compBrick2.addBlock(brick4);
    }

    @BeforeEach
    void setup() {
        wall.addBlock(brick1);
        wall.addBlock(brick2);

        wall.addBlock(compBrick1);
    }

    @Test
    void findBlockByColor() {
        //When
        Optional<Block> foundByColor = wall.findBlockByColor("color1");
        Optional<Block> foundNestedByColor = wall.findBlockByColor("color3");

        Optional<Block> notFoundByColor = emptyWall.findBlockByColor("color1");

        //Then
        assertTrue(foundByColor.isPresent());
        assertEquals(brick1, foundByColor.get());

        assertTrue(foundNestedByColor.isPresent());
        assertEquals(brick3, foundNestedByColor.get());

        assertFalse(notFoundByColor.isPresent());

        assertThrows(IllegalArgumentException.class, () -> wall.findBlockByColor(null));
    }

    @Test
    void findBlocksByMaterial() {
        //When
        List<Block> foundByMaterial = wall.findBlocksByMaterial("material2");
        List<Block> notFoundByMaterial = emptyWall.findBlocksByMaterial("material2");

        //Then
        assertEquals(2, foundByMaterial.size());
        assertEquals(0, notFoundByMaterial.size());

        assertThrows(IllegalArgumentException.class, () -> wall.findBlocksByMaterial(null));
    }

    @Test
    void count() {
        //When
        int blockCount = wall.count();
        int noBlockCount = emptyWall.count();

        //Then
        assertEquals(6, blockCount);
        assertEquals(0, noBlockCount);
    }
}