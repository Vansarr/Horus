package structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private final List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) throw new IllegalArgumentException("color is null");
        return blocks.stream()
                .flatMap(Block::toStream)
                .filter(n -> color.equals(n.getColor()))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) throw new IllegalArgumentException("material is null");
        return blocks.stream()
                .flatMap(Block::toStream)
                .filter(n -> material.equals(n.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) blocks.stream().flatMap(Block::toStream).count();
    }

    public void addBlock (Block block) {
        blocks.add(block);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return Objects.equals(blocks, wall.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blocks);
    }
}
