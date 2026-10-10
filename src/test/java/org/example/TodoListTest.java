package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoListTest {

    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add("  task1  ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().getFirst());
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add("   ");
        assertEquals(0, t.size());
    }

    @Test
    void markDone() {
        TodoList t = new TodoList();
        t.add("a");
        assertFalse(t.isDone(0));
        assertTrue(t.markDone(0));
        assertTrue(t.isDone(0));
        assertFalse(t.markDone(5));
    }

    @Test
    void clear() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        t.clear();
        assertEquals(0, t.size());
    }

    @Test
    void search() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("Buy bread");
        t.add("Call mom");
        assertEquals(List.of("Buy milk", "Buy bread"), t.search("buy"));
        assertEquals(List.of("Call mom"), t.search("MOM"));
        assertTrue(t.search("xyz").isEmpty());
        assertTrue(t.search("   ").isEmpty());
    }
}
