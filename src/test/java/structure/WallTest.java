package structure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private static final Brick brick1 = new Brick("red", "clay");
    private static final Brick brick2 = new Brick("black", "clay");
    private static final Brick brick3 = new Brick("white", "stone");
    private static final Brick brick4 = new Brick("blue", "stone");
    private static final CompositeBrick compBrick1 = new CompositeBrick("red", "composite");
    private static final CompositeBrick compBrick2 = new CompositeBrick("blue", "composite");

    private Wall wall = new Wall();

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
        Optional<Block> foundByColor = wall.findBlockByColor("red");

        //Then
        assertTrue(foundByColor.isPresent());
        assertEquals(brick1, foundByColor.get());
    }

    @Test
    void findBlocksByMaterial() {
        //When
        List<Block> foundByMaterial = wall.findBlocksByMaterial("clay");

        //Then
        assertEquals(2, foundByMaterial.size());
    }

    @Test
    void count() {
        //When
        int blockCount = wall.count();

        //Then
        assertEquals(6, blockCount);
    }
}