## Data Structures Programming Assignment 4
###

As you might imagine, the code for a linear probing hash set and a quadratic probing hash set are extremely similar. In this question, you will implement a hash set where the user can determine the probing strategy at the time of construction. This means that the same code will work for both linear and quadratic probing, or perhaps even more exotic probing strategies.

The way we do this is by creating a `ProbeStrategy` interface, that defines a `probe` method. The `HashSet` will then take an instance of a class that implements `ProbeStrategy`, and use its `probe` method to determine what indices to use next.

You are provided with four files for this assignment:

* The `ProbeStrategy` interface, which defines the `int probe(int arraySize, int originalHash, int attempt)`. This method will be used whenever a hash collision occurs. You do not need to modify this file.

* The `HashSet` class, which contains the actual hash table. This hash set only contains non-negative numbers, and so `-1` will be used to indicate that an index is empty.

* The `LinearProbeStrategy` class, which implements the `ProbeStrategy` interface. This class implements the `probe` function by adding 1 to the index on each additional probe, modulo the size of the array. So if the array size is 7, and the original hash is 5, the probe sequence would be:

    * (5 + 0) % 7 = 5
    * (5 + 1) % 7 = 6
    * (5 + 2) % 7 = 0
    * (5 + 3) % 7 = 1
    * and so on

* The `QuadraticProbeStrategy` class, which implements the `ProbeStrategy` interface. This class implements the `probe` function by adding adding the square of the probe attempt number, modulo the size of the array. So if the array size is 7, and the original hash is 5, the probe sequence would be:

    * (5 + 0 * 0) % 7 = 5
    * (5 + 1 * 1) % 7 = 6
    * (5 + 2 * 2) % 7 = 2
    * (5 + 3 * 3) % 7 = 0
    * and so on

The `HashSet` class has the following methods:

* `HashSet(int initArrayLength, int ratio, ProbeStrategy strategy)` - The constructor, which takes three arguments. `initArrayLength` is the length of the array at construction time; `ratio` determines maximum allowed elements before resizing (see below); and `ProbeStrategy` determines the probing strategy.

* `int size()` - returns the number of elements in the hash set.

* `boolean add(int value)` - adds a value to the set. `value` must be a non-negative integer. This function should return `true` if the value is not already in the set, and return `false` otherwise. This function will also resize the underlying array as necessary; see below.

* `boolean contains(int value)` - checks if a value is in the set. This function should return `true` if the value is in the set, and return `false` otherwise.

* `boolean remove(int value)` - removes a value from the set. This function should return `true` if the value is successfully removed, and return `false` if the value was not in the set.

* `int[] toArray()` - copies the underlying array and returns it. This function is only used to check the correctness of your code. Do not modify this function.

To ensure that the insertion, removal and lookup times of the `HashSet` is constant, the underlying array should be resized whenever `length / size <= ratio`, or equivalently, `length <= ratio * size`. We use the second comparison to avoid dealing with non-integer numbers. For the purpose of this assignment, the array will be resized to `2 * originalSize + 1`. Whenever an array is resized, the existing elements should be re-hashed and added to the new array by the order of their index in the old array.

To illustrate these details, consider a `HashSet` where the initial array length is 7, and has a ratio of 3. We are trying to insert the numbers 20, 5, 10, 9 using a `LinearProbeStrategy` strategy. When we initialize the `HashSet`, all positions of the array are set to `-1`:

```
HashSet set = new HashSet(7, 3, new LinearProbeStrategy()); // array is {-1, -1, -1, -1, -1, -1, -1}
```

When we add `20`, it is stored in the appropriate index (`20 % 7 = 6`):

```
set.add(20); // array is {-1, -1, -1, -1, -1, -1, 20}
```

At this point, the condition `length <= ratio * size` is `7 <= 3 * 1`, which is false, so no resizing occurs. We then add `5`:

```
set.add(5); // array is {-1, -1, -1, -1, -1, 5, 20}
```

At this point, the condition is now `7 <= 3 * 2`, which is still false. We then add `10`:

```
set.add(10);
```

After adding `10`, the condition becomes `7 <= 3 * 3`, which is now true. So the array is resized to `2 * originalSize + 1` = `2 * 7 + 1` = `15`. Before resizing, the array is `{-1, -1, -1, 10, -1, 5, 20}`; we resize the array to length 15, and re-hash 10, 5, 20 in that order (since that is the order in which the numbers exist in the old array). This will give us the array:

```
{-1, -1, -1, -1, -1, 5, 20, -1, -1, -1, 10, -1, -1, -1, -1}
```

Notice that because `5 % 15 = 5` and `20 % 15 = 5`, there is now a hash collision. Since we are using the `LinearProbeStrategy` strategy, `20` is instead stored at index 6.

Finally, we add the last element, `9`:

```
set.add(9); // array is {-1, -1, -1, -1, -1, 20, 5, -1, -1, 9, 10, -1, -1, -1, -1}
```

The `main()` in `HashSet.java` encodes this example.
