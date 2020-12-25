package _14_AvlAndAaTreesRopeAndTrie_Exercise._3_TextEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ICommandParser parser = new CommandParser();
        TextEditor textEditor = new TextEditorImpl();

        String input = reader.readLine();
        while (!"end".equals(input)) {
            parser.parseCommand(textEditor, input);
            input = reader.readLine();
        }
    }
}
