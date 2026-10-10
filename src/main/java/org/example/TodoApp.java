package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TodoList list = new TodoList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple Todo CLI. Commands: add <task>, remove <index>, done <index>, "
                + "search <text>, list, clear, exit");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
                case "add":
                    if (parts.length > 1) {
                        list.add(parts[1]);
                        System.out.println("Added.");
                    } else {
                        System.out.println("Usage: add <task>");
                    }
                    break;
                case "remove":
                    if (parts.length > 1) {
                        removeTask(list, parts[1]);
                    } else {
                        System.out.println("Usage: remove <index>");
                    }
                    break;
                case "done":
                    if (parts.length > 1) {
                        markTaskDone(list, parts[1]);
                    } else {
                        System.out.println("Usage: done <index>");
                    }
                    break;
                case "search":
                    if (parts.length > 1) {
                        printSearchResult(list.search(parts[1]));
                    } else {
                        System.out.println("Usage: search <text>");
                    }
                    break;
                case "list":
                    printTasks(list);
                    break;
                case "clear":
                    list.clear();
                    System.out.println("Cleared.");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command. Commands: add, remove, done, search, list, clear, exit");
            }
        }
    }

    private static void removeTask(TodoList list, String rawIndex) {
        try {
            int index = Integer.parseInt(rawIndex);
            if (list.remove(index)) {
                System.out.println("Removed.");
            } else {
                System.out.println("Index out of range.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index.");
        }
    }

    private static void markTaskDone(TodoList list, String rawIndex) {
        try {
            int index = Integer.parseInt(rawIndex);
            if (list.markDone(index)) {
                System.out.println("Done.");
            } else {
                System.out.println("Index out of range.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index.");
        }
    }

    private static void printTasks(TodoList list) {
        List<String> all = list.getAll();
        if (all.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (int i = 0; i < all.size(); i++) {
            String marker = list.isDone(i) ? "[x]" : "[ ]";
            System.out.printf("%d: %s %s%n", i, marker, all.get(i));
        }
    }

    private static void printSearchResult(List<String> found) {
        if (found.isEmpty()) {
            System.out.println("Nothing found.");
            return;
        }
        for (String item : found) {
            System.out.println(item);
        }
    }
}
