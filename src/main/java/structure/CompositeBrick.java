package structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CompositeBrick extends Brick implements CompositeBlock{
    private List<Block> blocks = new ArrayList<>();

    public CompositeBrick(String color, String material) {
        super(color, material);
    }

    @Override
    public List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public Stream<Block> toStream() {
        return Stream.concat(
                super.toStream(),
                blocks.stream().flatMap(Block::toStream)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeBrick that = (CompositeBrick) o;
        return Objects.equals(blocks, that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), blocks);
    }
}
