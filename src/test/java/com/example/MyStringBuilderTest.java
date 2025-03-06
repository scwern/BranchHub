package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyStringBuilderTest {

    private MyStringBuilder sb;

    @BeforeEach
    public void setUp() {
        sb = new MyStringBuilder();
    }

    @Test
    public void testAppend() {
        sb.append("Hello");
        assertEquals("Hello", sb.toString());

        sb.append(" World");
        assertEquals("Hello World", sb.toString());
    }

    @Test
    public void testInsert() {
        sb.append("Hello");
        sb.insert(5, " Beautiful");
        assertEquals("Hello Beautiful", sb.toString());

        assertThrows(IllegalArgumentException.class, () -> sb.insert(20, "Oops"));
    }

    @Test
    public void testDelete() {
        sb.append("Hello World");
        sb.delete(5, 11);
        assertEquals("Hello", sb.toString());

        assertThrows(IllegalArgumentException.class, () -> sb.delete(11, 5));
        assertThrows(IllegalArgumentException.class, () -> sb.delete(-1, 5));
    }

    @Test
    public void testUndo() {
        sb.append("Hello");
        sb.append(" World");

        sb.undo();
        assertEquals("Hello", sb.toString());

        sb.undo();
        assertEquals("", sb.toString());
    }


    @Test
    public void testUndoAfterInsert() {
        sb.append("Hello");
        sb.save();

        sb.insert(5, " World");
        assertEquals("Hello World", sb.toString());

        sb.undo();
        assertEquals("Hello", sb.toString());
    }

    @Test
    public void testUndoAfterDelete() {
        sb.append("Hello World");
        sb.save();

        sb.delete(5, 11);
        assertEquals("Hello", sb.toString());

        sb.undo();
        assertEquals("Hello World", sb.toString());
    }
}
