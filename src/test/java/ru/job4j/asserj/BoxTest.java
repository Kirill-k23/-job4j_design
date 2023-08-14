package ru.job4j.asserj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void getNumberOfVerticesIsNegative() {
        Box box = new Box(-8, 10);
        int n = box.getNumberOfVertices();
        assertThat(n).isNegative();
    }

    @Test
    void getNumberOfVerticesIsLessThanFour() {
        Box box = new Box(3, 10);
        int n = box.getNumberOfVertices();
        assertThat(n).isLessThan(4);
    }

    @Test
    void isExistIsNotEqualToFour() {
        Box box = new Box(3, 10);
        boolean b = box.isExist();
        assertThat(b).isNotEqualTo(3);
    }

    @Test
    void isExistIsNotNull() {
        Box box = new Box(2, 10);
        boolean b = box.isExist();
        assertThat(b).isNotNull();
    }

    @Test
    void getAreaIsLessThan() {
        Box box = new Box(3, 4);
        double d = box.getArea();
        assertThat(d).isLessThan(1);
    }

    @Test
    void getAreaIsNotZero() {
        Box box = new Box(0, 10);
        double d = box.getArea();
        assertThat(d).isNotZero();
    }
}