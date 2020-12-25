package _17_QuadKDIntervalTreesHashTables_Exercise._1_SweepAndPrune;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameArea {
    private final List<GameObject> gameObjectList;
    private final Map<String, GameObject> gameObjectMap;
    private int moves;
    private final StringBuilder consoleOutput;

    public GameArea() {
        this.gameObjectList = new ArrayList<>();
        this.gameObjectMap = new TreeMap<>();
        this.moves = 0;
        this.consoleOutput = new StringBuilder();
    }

    public void changeObjectPosition(GameObject gameObject) {
        this.gameObjectMap.get(gameObject.getName()).setObjectForm(gameObject.getObjectForm());
    }

    public void updateMoves() {
        this.moves++;
    }

    public void addGameObject(GameObject gameObject) {
        this.gameObjectList.add(gameObject);
        this.gameObjectMap.put(gameObject.getName(), gameObject);
    }

    public void sortObjects() {
        GameArea.insertionSort(this.gameObjectList);
    }

    public void sweep() {
        GameArea.insertionSort(this.gameObjectList);
        for (int i = 0; i < this.gameObjectList.size(); i++) {
            GameObject current = this.gameObjectList.get(i);

            for (int j = i + 1; j < this.gameObjectList.size(); j++) {
                GameObject collisionCandidates = this.gameObjectList.get(j);

                if (collisionCandidates.getObjectForm().getX1() > current.getObjectForm().getX2()) {
                    break;
                }

                if (current.getObjectForm().intersects(collisionCandidates.getObjectForm())) {
                    this.consoleOutput.append(String.format("(%d) %s collides with %s\n",
                            this.moves, current.getName(), collisionCandidates.getName()));
                }
            }
        }
    }

    public void showLog() {
        System.out.print(this.consoleOutput);
    }

    private static void insertionSort(List<GameObject> gameObjects) {
        for (int i = 1; i < gameObjects.size(); i++) {
            int j = i;

            while (j > 0 && gameObjects.get(j - 1).getObjectForm().getX1() > gameObjects.get(j).getObjectForm().getX1()) {
                GameObject temp = gameObjects.get(j);
                gameObjects.set(j, gameObjects.get(j - 1));
                gameObjects.set(j - 1, temp);
                j--;
            }
        }
    }
}
