import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class p02_secCover {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(in.nextLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = in.nextLine().split(", ");
            int[] currentSet = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                currentSet[j] = Integer.parseInt(setElements[j]);
            }
            sets.add(currentSet);
        }

//        sets.sort(Comparator.comparingInt(a -> a.length));
//        Collections.reverse(sets);

        List<int[]> selectedSets = chooseSets(sets, universe);
        print(selectedSets);
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        List<int[]> tmpSets = new ArrayList<>(sets);
        List<Integer> universeList = Arrays.stream(universe).boxed().collect(Collectors.toList());
        List<int[]> matchedSets = new ArrayList<>();

        while (!universeList.isEmpty() && !tmpSets.isEmpty()) {
            int maxMatchIndex = getMaxMatchIndex(tmpSets, universeList);

            matchedSets.add(tmpSets.get(maxMatchIndex));

            int[] currentMaxSet = tmpSets.get(maxMatchIndex);

            for (int value : currentMaxSet) {
                universeList.remove((Integer) value);
            }
            tmpSets.remove(maxMatchIndex);
        }

        return matchedSets;
    }

    public static int getMaxMatchIndex(List<int[]> set, List<Integer> target) {
        int matchedCount = 0;
        int maxMatchCount = -1;
        int maxMatchIndex = 0;
        for (int i = 0; i < set.size(); i++) {
            int[] current = set.get(i);
            for (int j = 0; j < current.length; j++) {
                if (target.contains(current[j])) {
                    matchedCount++;
                }
            }
            if (matchedCount > maxMatchCount) {
                maxMatchCount = matchedCount;
                maxMatchIndex = i;
            }
            matchedCount = 0;
        }
        return maxMatchIndex;
    }

    public static void print(List<int[]> list) {
        System.out.printf("Sets to take (%d)\n", list.size());
        for (int[] el : list) {
            System.out.println("{" + Arrays.stream(el).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + "}");
        }
    }
}
