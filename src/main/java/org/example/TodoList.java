package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TodoList {
    private final List<Task> tasks = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            String normalized = item.trim();
            if (!normalized.isEmpty()) {
                tasks.add(new Task(normalized));
            }
        }
    }

    public boolean remove(int index) {
        if (isValidIndex(index)) {
            tasks.remove(index);
            return true;
        }
        return false;
    }

    public boolean markDone(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).setDone(true);
            return true;
        }
        return false;
    }

    public boolean isDone(int index) {
        return isValidIndex(index) && tasks.get(index).isDone();
    }

    public void clear() {
        tasks.clear();
    }

    public List<String> search(String query) {
        List<String> found = new ArrayList<>();
        if (query == null) {
            return found;
        }
        String needle = query.trim().toLowerCase(Locale.ROOT);
        if (needle.isEmpty()) {
            return found;
        }
        for (Task task : tasks) {
            if (task.getText().toLowerCase(Locale.ROOT).contains(needle)) {
                found.add(task.getText());
            }
        }
        return found;
    }

    public List<String> getAll() {
        List<String> items = new ArrayList<>();
        for (Task task : tasks) {
            items.add(task.getText());
        }
        return items;
    }

    public int size() {
        return tasks.size();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    private static final class Task {
        private final String text;
        private boolean done;

        private Task(String text) {
            this.text = text;
        }

        private String getText() {
            return text;
        }

        private boolean isDone() {
            return done;
        }

        private void setDone(boolean done) {
            this.done = done;
        }
    }
}
