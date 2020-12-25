package _17_QuadKDIntervalTreesHashTables_Exercise._1_SweepAndPrune;

public class CommandParser {

    public static void executeCommand(GameArea gameArea, String commandString) {
        String[] inputData = commandString.split(" ");
        String command = inputData[0];

        switch (command) {
            case "add":
                String name = inputData[1];
                int x1 = Integer.parseInt(inputData[2]);
                int y1 = Integer.parseInt(inputData[3]);
                GameObject gameObject = new GameObject(x1, y1, name);

                gameArea.addGameObject(gameObject);
                break;
            case "start":
                gameArea.sortObjects();
                break;
            case "tick":
                gameArea.updateMoves();
                gameArea.sweep();
                break;
            case "move":
                name = inputData[1];
                x1 = Integer.parseInt(inputData[2]);
                y1 = Integer.parseInt(inputData[3]);

                GameObject newObject = new GameObject(x1, y1, name);
                gameArea.changeObjectPosition(newObject);
                gameArea.updateMoves();
                gameArea.sweep();
                break;
        }
    }
}
