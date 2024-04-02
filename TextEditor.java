package Huyen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextEditor {
    private List<String> lines;
    private File file;

    public TextEditor(String filePath) {
        this.file = new File(filePath);
        this.lines = new ArrayList<>();
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            lines.forEach(writer::println);
            System.out.println("Content saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = reader.lines().collect(Collectors.toList());
            System.out.println("Content loaded from file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void removeLine(int index) {
        if (index >= 0 && index < lines.size()) {
            lines.remove(index);
        } else {
            System.out.println("Invalid line index.");
        }
    }

    public void displayContent() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Line " + (i + 1) + ": " + lines.get(i));
        }
    }

    public void recursiveSearchFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        recursiveSearchFiles(file);
                    } else {
                        System.out.println("File: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor("path/to/your/file.txt");

        textEditor.addLine("Hello, World!");
        textEditor.addLine("This is a sample text.");
        textEditor.addLine("Welcome to the text editor.");

        textEditor.saveToFile();

        textEditor.loadFromFile();
        textEditor.displayContent();

        File directory = new File("path/to/your/directory");
        textEditor.recursiveSearchFiles(directory);
    }
}