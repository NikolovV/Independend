# Data Structures
#### Summary
---
##### 1. Linear Data Structures
- Arrays, Resizable Lists, LinkedLists, Stacks And Queues
##### 2. Tree Data Structures
>Binary Trees and Traversals:
>Pre-order (node, left, right)
>In-order (left, node, right)
>Post-order (left, right, node)

>Tree Traversals algorithm - DFS / BFS

- **Trees**: binary, balanced, ordered
- **Graphs**: directed / undirected, weighted
- **Networks**: graphs with particular attributes
- ##### 2.1. Binary Search Trees
  >Insert, Search, Delete - O(n)
##### 3. Heaps and Priority Queues
- ##### 3.1. Priority queue
Unsorted Array - Insert / Pull / Peek - O(1) / O(n) / O(n)
Sorted Array - Insert / Pull / Peek - O(n) / O(1) / O(1)
Applications: Data Compression, Graph Shortest Path / GPS, Pathfinding / AI
- ##### 3.2. Heaps
  >Heaps are used to implement priority queues
  - ##### 3.2.1. BinaryHeap
  - Insert - O(logN)
  - Deletion - O(logN)
  - Pull - O(logN)
  - Sort - O(NlogN)
  - Peek - O(1)
  > Other Heap Data Structures: Binomial, Fibonacci, Pairing, Treap, Skew, Soft e.t.c
- ##### 3.3. A* (A Star)
  > The A* pathfinding algorithm is a modification of Dijkstra's
  >shortest path algorithm. At each step it uses a heuristic function to guide its search.
  >FCost = GCost + HCost
  >GCost – the distance from current to the start
  >HCost – the distance from current to the destination
##### 4. B-Trees And Red-Black Trees
- ##### 4.1. 2-3 Tree
  > Search / Insert / Delete - O(c lg N)
- ##### 4.2. Red-Black Tree
  - ##### 4.2.1. Properties:
    - All leaves are black
    - The root is black
    - No node has two red links connected to it
    - Every path from a given node to its descendant leaf nodes
    - contains the same number of black nodes
    - Red links lean left
- ##### 4.2.2. Rebalancing Trees (Rotations)
  > Left / Right Rotation
  > Search / Insert / Delete - O(2 lg N) worst / O(lgN) average
##### 5. AA-Trees And AVL Trees
- ##### 5.1. AVL Trees
  > AVL tree is a self-balancing binary-search tree.
  > Height of two subtrees can differ by at most 1
  > Height difference is measured by a balance factor (BF)
  > Left, Right, Double Left, Double Right Rotation
  > Search / Insert / Delete - O(1.44 lg N) worst / O(lgN) average
- ##### 5.2. AA-Trees
  > The level of every leaf node is one
  > Every left child has level one less than its parent
  > Every right child has level equal to or one less than its parent
  > Right grandchildren have levels less than their grandparents
  > Every node of level greater than one has two children
  > Right horizontal links are possible
  > Left horizontal links are not allowed
- Skew operation is a single right rotation
- Split operation is a single left rotation
> **AVL/AA** trees tend to be flatter than **Red-Black** Trees
> **AVL/AA** trees perform more rotations for insertion and deletion
> **Red-Black** trees have faster insertion/deletion
> **AVL/AA** trees have faster searching
> **AA** have simpler implementation
##### 6. Rope And Trie
- ##### 6.1. Rope
  > Balanced tree for indexed items with fast insert / delete
  > Allows fast string edit operations on very long strings (length > 10 000 000)
  > insert / delete operations at random position – O(logN)
- ##### 6.2. Trie
  > Ordered tree data structure
  > Special tree structure used for fast multi-pattern matching
  > Used to store a dynamic set where the keys are usually strings
##### 7. Quad Trees, K-D Trees, Interval Trees
- ##### 7.1. Interval Trees
  > Modified BST that stores intervals
  > Provide efficient search for overlapping intervals
  > Search / Insert / Delete - O(log N + M) / O(log N) / O(log N)
- ##### 7.2. Quad Trees
  > 2D space partitioning trees
  > Recursively divide space into quads (quadrants)
  > Each node has 0 or 4 children
  > Provide efficient search for intersecting objects
  > Offer efficient bounds check for collision detection
- ##### 7.3. K-D Trees
  > Space-partitioning data structure
  > Still a BST (every node has 2 children)
  > Еvery node splits its own plane in two parts
  > Provide fast retrieval of multidimensional data
##### 8. Hash Tables - Sets And Dictionaries
- ##### 8.1. Hash Table
  > Аrray that holds a set of {key, value} pairs.
  > The process of mapping a key to a position in a table is called **hashing**
  > A collision comes when different keys have the same hash value
  
- Several collisions resolution strategies exist
- Chaining collided keys (+ values) in a list
 - Using other slots in the table (open addressing)
 - Cuckoo hashing
Chaining: Search / Insert / Delete - O(log N) / O(log N) / O(log N)
- ##### 8.2. Set and Bag ADTs
  > Кeeps a set of elements with no duplicates
  > Sets with duplicates are also known as ADT "bag"
- Set specific operations:
  - UnionWith(set)
  - IntersectWith(set)
  - ExceptWith(set)
  - SymmetricExceptWith(set)
- ##### 8.3. Dictionary (Map) ADT
  > The abstract data type (ADT) "dictionary" maps key to values
  > Many implementations: Hash table, balanced tree, list, array, ...
 - HashSet: Add/Update/Delete - O(1)
 - SortedSe: Add/Update/Delete - O(log N)
