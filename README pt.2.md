## Data Structures Programming Assignment 5
### 
In this project, you will implement merge sort on a doubly-linked list. Although merge sort is most commonly done on arrays, the same complexity of O(n log n) is still possible on a linked list. In fact, it is arguably more efficient, since you no longer need the O(n) memory cost of having an extra array as you move things around.

There is only one function you need to write for this project: the `mergesort()` function in `StringLinkedList.java`. All the other necessary linked list functions have been written for you, including `add()`, `get()`, and `print()`. Merge sort for linked lists works in much the same as it does for arrays, with the exception that you will need to return the head of a sublist so that merging can occur (as opposed to relying in indices for arrays). Otherwise, the same divide and conquer strategy applies.

Note that there are two constraints for this project: First, your implementation should *not* use arrays in any way, and second, your implementation must be O(n log n).
