package _8_TreeDataStructures.Exercise.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        Map<Integer, Tree<Integer>> treeMap = new HashMap<>();

        for (int i = 0; i < nodes - 1; i++) {
            String[] edgeData = reader.readLine().split(" ");
            int from = Integer.parseInt(edgeData[0]);
            int to = Integer.parseInt(edgeData[1]);

            treeMap.putIfAbsent(from, new Tree<>(from));
            treeMap.putIfAbsent(to, new Tree<>(to));
            treeMap.get(from).addChild(treeMap.get(to));
        }

        // 1. Root Node
//        Tree<Integer> root = new Tree<>(0);
//        root = getRootNode(treeMap, root);
//        System.out.printf("Root node: %d\n", root.getValue());

        // 2. Print Tree
//        Tree<Integer> root = new Tree<>(0);
//        root = getRootNode(treeMap, root);
//        System.out.println(root.print(0, new StringBuilder()));

        // 3. Leaf Nodes
//        Set<Integer> leafsIncreasing = getLeafsIncreasing(treeMap);
//        System.out.printf("Leaf nodes: %s\n", leafsIncreasing.stream().map(Object::toString).collect(Collectors.joining(" ")));

        // 4. Middle Nodes
//        Set<Integer> middleNodes = getMiddleNodes(treeMap);
//        System.out.printf("Middle nodes: %s\n", middleNodes.stream().map(Object::toString).collect(Collectors.joining(" ")));

        // 5. Deepest Node
//        Tree<Integer> root = new Tree<>(0);
//        root = getRootNode(treeMap, root);

//        Map<Integer, Integer> nodeDepth = new LinkedHashMap<>();
//        DFS(root, nodeDepth, 0);

//        int deepestNode = Collections.max(nodeDepth.entrySet(), Map.Entry.comparingByValue()).getKey();
//        System.out.printf("Deepest node: %d\n", deepestNode);

        // 6. Longest Path
//        Tree<Integer> root = new Tree<>(0);
//        root = getRootNode(treeMap, root);
//
//        Map<Integer, Integer> nodeDepth = new LinkedHashMap<>();
//        DFS(root, nodeDepth, 0);
//        int deepestNode = Collections.max(nodeDepth.entrySet(), Map.Entry.comparingByValue()).getKey();
//
//        Deque<Integer> result = longestPath(treeMap, deepestNode);
//        System.out.printf("Longest path: %s\n", result.stream().map(Object::toString).collect(Collectors.joining(" ")));

        // 7. All Paths With a Given Sum
//        Tree<Integer> root = new Tree<>(0);
//        root = getRootNode(treeMap, root);
//
//        int sum = Integer.parseInt(reader.readLine());
//        List<Integer> paths = new ArrayList<>();
//        calculateSum(root, sum, 0, paths);
//        System.out.println(printPathWithGivenSum(paths, treeMap, sum));

        // 8. All Subtrees With a Given Sum
        Tree<Integer> root = new Tree<>(0);
        root = getRootNode(treeMap, root);

        int sum = Integer.parseInt(reader.readLine());

        List<Integer> subRoots = new ArrayList<>();
        System.out.println("Subtrees of sum " + sum + ": ");
        calculateSubtreeSum(root, sum, subRoots);
        for (Integer node : subRoots) {
            StringBuilder output = new StringBuilder();
            System.out.println(printSubtreeWithGivenSum(treeMap.get(node), output));
        }
    }

    // 1. Root Node
    private static Tree<Integer> getRootNode(Map<Integer, Tree<Integer>> treeMap, Tree<Integer> root) {
        for (Tree<Integer> tree : treeMap.values()) {
            if (tree.getParent() == null) {
                root = tree;
                break;
            }
        }
        return root;
    }

    // 3. Leaf Nodes
    private static Set<Integer> getLeafsIncreasing(Map<Integer, Tree<Integer>> treeMap) {
        Set<Integer> leafsIncreasing = new TreeSet<>();
        for (Tree<Integer> tree : treeMap.values()) {
            if (tree.getChildren().isEmpty()) {
                leafsIncreasing.add(tree.getValue());
            }
        }
        return leafsIncreasing;
    }

    // 4. Middle Nodes
    private static Set<Integer> getMiddleNodes(Map<Integer, Tree<Integer>> treeMap) {
        Set<Integer> middleNodes = new TreeSet<>();
        for (Tree<Integer> node : treeMap.values()) {
            if (node.getParent() != null && node.getChildren().size() > 0) {
                middleNodes.add(node.getValue());
            }
        }
        return middleNodes;
    }

    // 5. Deepest Node
    private static void DFS(Tree<Integer> parentNode, Map<Integer, Integer> nodeDepth, int depth) {
        for (Tree<Integer> child : parentNode.getChildren()) {
            DFS(child, nodeDepth, depth + 1);
        }
        nodeDepth.putIfAbsent(parentNode.getValue(), depth);
    }

    // 6. Longest Path
    private static Deque<Integer> longestPath(Map<Integer, Tree<Integer>> treeMap, int deepestNodeValue) {
        Tree<Integer> deepestNode = treeMap.get(deepestNodeValue);
        Deque<Integer> result = new ArrayDeque<>();

        while (deepestNode != null) {
            result.addFirst(deepestNode.getValue());
            deepestNode = deepestNode.getParent();
        }
        return result;
    }

    // 7. All Paths With a Given Sum
    private static void calculateSum(Tree<Integer> node, int sum, int currentSum, List<Integer> path) {
        currentSum += node.getValue();

        if (currentSum == sum && node.getChildren().isEmpty()) {
            path.add(node.getValue());
        }

        for (Tree<Integer> child : node.getChildren()) {
            calculateSum(child, sum, currentSum, path);
        }
    }

    private static StringBuilder printPathWithGivenSum(List<Integer> paths, Map<Integer, Tree<Integer>> treeMap, int sum) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Paths of sum %d:", sum)).append(System.lineSeparator());

        for (Integer value : paths) {
            Tree<Integer> currentNode = treeMap.get(value);
            Deque<Integer> path = new ArrayDeque<>();

            while (currentNode != null) {
                path.addFirst(currentNode.getValue());
                currentNode = currentNode.getParent();
            }

            output.append(path.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" ")))
                    .append(System.lineSeparator());
        }
        return output;
    }

    // 8. All Subtrees With a Given Sum
    private static int calculateSubtreeSum(Tree<Integer> node, int sum, List<Integer> roots) {
        if (node == null) {
            return 0;
        }
        int currentSum = node.getValue();

        for (Tree<Integer> child : node.getChildren()) {
            currentSum += calculateSubtreeSum(child, sum, roots);
        }

        if (currentSum == sum) {
            roots.add(node.getValue());
        }

        return currentSum;
    }

    private static String printSubtreeWithGivenSum(Tree<Integer> parentNode, StringBuilder builder) {
        if (parentNode != null) {
            builder.append(parentNode.getValue()).append(" ");

            for (Tree<Integer> child : parentNode.getChildren()) {
                printSubtreeWithGivenSum(child, builder);
            }
        }
        return builder.toString();
    }
}
