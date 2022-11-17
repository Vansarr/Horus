package structure;

import java.util.Objects;
import java.util.stream.Stream;


public class Brick implements Block {
private final String color;
private final String material;

    public Brick(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public Stream<Block> toStream() {
        return Stream.of(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brick brick = (Brick) o;
        return Objects.equals(color, brick.color) && Objects.equals(material, brick.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, material);
    }
}
