package _14_AvlAndAaTreesRopeAndTrie_Exercise._3_TextEditor;

public class CommandParser implements ICommandParser {
    @Override
    public void parseCommand(TextEditor textEditor, String command) {
        String[] inputData = command.split(" ");

        switch (inputData[0]) {
            case "login":
                textEditor.login(inputData[1]);
                break;
            case "users":
                if (inputData.length > 1) {
                    for (String user : textEditor.users(inputData[1])) {
                        System.out.println(user);
                    }
                } else {
                    for (String user : textEditor.users("")) {
                        System.out.println(user);
                    }
                }
                break;
            case "logout":
                textEditor.logout(inputData[1]);
                break;
            default:
                String userName = inputData[0];

                switch (inputData[1]) {
                    case "insert":
                        int index = Integer.parseInt(inputData[2]);
                        String str = command.split("\"")[1];
                        textEditor.insert(userName, index, str);
                        break;
                    case "prepend":
                        str = command.split("\"")[1];
                        textEditor.prepend(userName, str);
                        break;
                    case "substring":
                        int startIndex = Integer.parseInt(inputData[2]);
                        int length = Integer.parseInt(inputData[3]);
                        textEditor.substring(userName, startIndex, length);
                        break;
                    case "delete":
                        startIndex = Integer.parseInt(inputData[2]);
                        length = Integer.parseInt(inputData[3]);
                        textEditor.delete(userName, startIndex, length);
                        break;
                    case "clear":
                        textEditor.clear(userName);
                        break;
                    case "length":
                        System.out.println(textEditor.length(userName));
                        break;
                    case "print":
                        System.out.println(textEditor.print(userName));
                        break;
                    case "undo":
                        textEditor.undo(userName);
                        break;
                }
        }
    }
}