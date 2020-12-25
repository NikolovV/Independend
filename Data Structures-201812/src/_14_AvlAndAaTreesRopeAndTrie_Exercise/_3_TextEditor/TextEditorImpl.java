package _14_AvlAndAaTreesRopeAndTrie_Exercise._3_TextEditor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TextEditorImpl implements TextEditor {
    private Set<String> loggedUsers;
    private Trie<Deque<StringBuilder>> dictionary;

    public TextEditorImpl() {
        this.loggedUsers = new TreeSet<>();
        this.dictionary = new Trie<>();
    }

    @Override
    public void login(String username) {
        this.loggedUsers.remove(username);
        this.loggedUsers.add(username);
        this.dictionary.insert(username, new ArrayDeque<>());
    }

    @Override
    public void logout(String username) {
        this.dictionary.insert(username, new ArrayDeque<>());
        this.loggedUsers.remove(username);
    }

    @Override
    public void prepend(String username, String string) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> userSb = this.dictionary.getValue(username);
            StringBuilder newSb = getFirstSb(userSb);
            newSb.insert(0, string);
            userSb.push(newSb);
        }
    }

    @Override
    public void insert(String username, int index, String string) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> userSb = this.dictionary.getValue(username);
            StringBuilder newSb = getFirstSb(userSb);
            newSb.insert(index, string);
            userSb.push(newSb);
        }
    }

    @Override
    public void substring(String username, int startIndex, int length) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> userSb = this.dictionary.getValue(username);
            StringBuilder sb = userSb.isEmpty() ? new StringBuilder() : userSb.peek();

            if (sb.length() > 0) {
                StringBuilder newSb = new StringBuilder(sb.substring(startIndex, startIndex + length));
                userSb.push(newSb);
            }
        }
    }

    @Override
    public void delete(String username, int startIndex, int length) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> userSb = this.dictionary.getValue(username);
            StringBuilder newSb = getFirstSb(userSb);
            newSb.delete(startIndex, startIndex + length);
            userSb.push(newSb);
        }
    }

    @Override
    public void clear(String username) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> userSb = this.dictionary.getValue(username);
            StringBuilder newSb = new StringBuilder();
            userSb.push(newSb);
        }
    }

    @Override
    public int length(String username) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> stack = this.dictionary.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();

            return sb.length();
        }

        return 0;
    }

    @Override
    public String print(String username) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> stack = this.dictionary.getValue(username);
            StringBuilder sb = stack.isEmpty() ? new StringBuilder() : stack.peek();

            return sb.toString();
        }

        return "";
    }

    @Override
    public void undo(String username) {
        if (this.isLogged(username)) {
            Deque<StringBuilder> userSb = this.dictionary.getValue(username);
            if (!userSb.isEmpty()) {
                userSb.pop();
            }
        }
    }

    @Override
    public Iterable<String> users(String prefix) {
        if ("".equals(prefix)) {
            return this.loggedUsers;
        }

        return this.loggedUsers.stream()
                .filter(user -> user.startsWith(prefix))
                .collect(Collectors.toList());
    }

    private boolean isLogged(String username) {
        return this.dictionary.contains(username);
    }

    private static StringBuilder getFirstSb(Deque<StringBuilder> userSb) {
        StringBuilder sb = userSb.isEmpty() ? new StringBuilder() : userSb.peek();
        return new StringBuilder(sb.toString());
    }
}
