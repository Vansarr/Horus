package structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return Optional.ofNullable(findByPredicate(n -> color.equals(n.getColor())).get(0));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findByPredicate(n -> material.equals(n.getMaterial()));
    }

    private List<Block> findByPredicate(Predicate<Block> predicate){
        return blocks.stream()
                .flatMap(Block::toStream)
                .filter(predicate)
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
