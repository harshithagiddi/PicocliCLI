package org.example;

import picocli.CommandLine;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Command(name = "todo", description = "A command-line To-Do List application")
public class Main implements Runnable {
    @Option(names = {"-a", "--add"}, description = "Add a task to the list")
    private String task;

    @Option(names = {"-l", "--list"}, description = "List all tasks")
    private boolean listTasks;

    @Parameters(index = "0..*", description = "Tasks to be added (use quotes for multi-word tasks)")
    private List<String> tasksToAdd = new ArrayList<>();

    private List<String> tasks = new ArrayList<>();

    @Override
    public void run() {
        if (listTasks) {
            listTasks();
        } else if (task != null) {
            addTask(task);
        } else if (!tasksToAdd.isEmpty()) {
            addTasks(tasksToAdd);
        } else {
            CommandLine.usage(this, System.out);
        }
    }

    private void addTask(String task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    private void addTasks(List<String> tasksToAdd) {
        tasks.addAll(tasksToAdd);
        System.out.println("Tasks added: " + tasksToAdd);
    }

    private void listTasks() {
        System.out.println("To-Do List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        CommandLine.run(new Main(), args);
        System.in.read();
    }
}
