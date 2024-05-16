import java.util.Arrays;
import java.util.TreeSet;


public class HashSet {


    private int[] values;
    private int size; 
    private final double loadFactor; 
    private final ProbeStrategy strategy; 

    public HashSet(int initArrayLength, double ratio, ProbeStrategy strategy) {
        this.values = new int[initArrayLength];
        this.size = 0;
        this.loadFactor = ratio;
        this.strategy = strategy;
        Arrays.fill(values, -1);
    }

    public boolean add(int value) {
        if (shouldResize()) {
            resize();
        }

        int index = value % values.length;

        while (values[index] != -1) {
            if (values[index] == value) {
                return false; 
            }
            index = strategy.probe(values.length, index, size);
        }

        values[index] = value;
        size++;
        return true;
    }

    public boolean contains(int value) {
        int index = value % values.length;

        while (values[index] != -1) {
            if (values[index] == value) {
                return true; 
            }
            index = strategy.probe(values.length, index, size);
        }

        return false;
    }

    public boolean remove(int value) {
        int index = value % values.length;

        while (values[index] != -1) {
            if (values[index] == value) {
                values[index] = -1;
                size--;

                int nextIndex = strategy.probe(values.length, index, size);
                while (values[nextIndex] != -1) {
                    int temp = values[nextIndex];
                    values[nextIndex] = -1;
                    add(temp); 
                    nextIndex = strategy.probe(values.length, nextIndex, size);
                }
                return true;
            }
            index = strategy.probe(values.length, index, size);
        }

        return false;
    }

    private boolean shouldResize() {
        return size >= loadFactor * values.length;
    }

    private void resize() {
        int newSize = 2 * values.length + 1;
        int[] temp = new int[newSize];
        for (int i = 0; i < values.length; i++) {
            if (values[i] != -1) {
                add(values[i]); // Re-insert using add to handle collisions
            }
        }
        values = temp;
    }

    public int[] toArray() {
        int[] result = new int[size];
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != -1) { 
                result[count++] = values[i]; 
            }
        }
        return result;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ProbeStrategy linear = new LinearProbeStrategy();
        HashSet set = new HashSet(7, 3, linear);

        int[] numbers = {20, 5, 3, 9};
        for (int i = 0; i < numbers.length; i++) {
            set.add(numbers[i]);

            int[] array = set.toArray();
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j] + ", ");
            }
            System.out.println();

        }

    }
}
