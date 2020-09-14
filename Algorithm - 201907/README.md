# Algorithms
#### Summary
---
##### 1. Recursion
- Pre-actions (before calling the recursion)
- Recursive calls (step-in)
- Post-actions (after returning from recursion)
- bottom recursive calls stop

 Backtracking - finding all solutions to some combinatorial problem.
 
##### 2. Sorting
- ###### 2.1 Recursive / non-recursive
 - ###### 2.2 Stability
   - Stable - Maintain the order of equal elements
   - Unstable - Rearrange the equal elements in unpredictable order

How to choose the most appropriate algorithm?
##### 3. Searching Algorithms
 - Binary Search, Interpolation Search
##### 4. Combinatorial Algorithms
- Permutations - Ways to order n elements.
- Variations - Ways to order k of n elements
- Combinations - Ways to choose k of n elements
- N Choose K Count - Binomial Coefficients (Pascal's Triangle)
##### 5. Greedy Algorithms
- Brute-Force Algorithms - Trying All Solutions
- Greedy Algorithms - Picking Locally Optimal Solution. Can produce non-optimal  (incorrect) result
- Heuristic Algorithms - solve hard problems approximately
  - P - Category in which the algorithms are solvable in polynomial time
  - NP - Category in which the algorithms are verifiable in polynomial
 
##### 6. Dynamic Programming
Solve optimization problems by breaking down into overlapping sub-problems.
 - Top down approach - Solve recursively by breaking down the problem further and further
 - Bottom up approach - Solve iteratively by solving smaller problems and constructing the whole solution from the bottom up
 - Memoization - Save subproblem solutions for later use
##### 7. Graphs and Graph Algorithms
- ###### 7.1. Graph type:
  - Directed graph
  - Undirected graph
  - Weighted graph
- ###### 7.2. Graphs Traversals
  - ###### 7.2.1. Depth-First Search (DFS)
    - Visit node's successors first
    - Usually implemented by recursion
  - ###### 7.2.2. Breadth-First Search (BFS)
    - Nearest nodes visited first
    - Implemented with a queue
- ###### 7.3. Graph Connectivity
  - Connected component - A sub-graph in which any two nodes are connected to each other by paths
- ###### 7.4. Topological Sorting (ordering) of a directed graph
  - ###### 7.4.1 Rules
     - Undirected graphs cannot be sorted
     - Graphs with cycles cannot be sorted
     - Sorting is not unique
     - Various sorting algorithms exists and they give different results
  - Source removal top-sort algorithm
  - DFS Algorithm
 - ###### 7.5 Graph Algorithms
 - ##### Minimum Spanning Tree and Minimum Spanning Forest
 ---
   > Minimum Spanning Tree (MST) 
     - Subgraph without cycles (tree)
     - Connects all vertices together
   > Minimum Spanning Forest (MSF) - All undirected graphs have spanning forest
 - ###### 7.5.1. Kruskal's Algorithm
 - ###### 7.5.2. Prim's Algorithm
 - ##### Shortest paths in a graph
 ---
 - ###### 7.5.3. Dijkstra's Algorithm - finds the shortest path from given vertex to all other vertices in a directed / undirected weighted graph
   - Weights on edges are non-negative
   - Not all edges need to be reachable
   - Dijskstra's algorithm is similar to BFS and to Prim's algorithms. Use a priority queue instead of queue
   - Typical implementation (with array): O(|V|2)
   - With priority queue: O((|V|+|E|)*log(|V|))
 - ###### 7.5.4. Bellman-Ford - finds the shortest paths from a single source to all other vertices
   - Unlike Dijkstra's algorithm, Bellman-Ford works with negative weight edges
   - Can detect negative cycles and report them
   - Runs in time O(|V||E|)
 - ###### 7.5.5. Floyd-Warshall - computes the shortest paths between all pairs of vertices
   - Edge weights can be positive or negative
   - Can be extended to detect negative weight cycles
   - Has running time of O(|V|3)
 - ##### Strongly-Connected Components 
 ---
> Maximal strongly-connected subgraph (component with paths between any two nodes)
 - ###### 7.5.6. Kosaraju–Sharir Algorithm
 - ##### Bi-Connectivity
 ---
   - Articulation Points -In a connected undirected graph an articulation point is a node that when removed, splits the graph into several components
   - ###### 7.5.7. Hopcroft, Tarjan Algorithm
  - ##### Max Flow
 ---   
 > compute the maximum flow from node **s** to node **t**
 - ###### 7.5.8. Ford-Fulkerson Max-Flow Algorithm
 - ###### 7.5.9. Edmonds-Karp Max Flow Algorithm
 - ###### 7.5.10. Dinic/Dinitz Max-Flow Algorithm