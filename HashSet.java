public class HashSet {

    private int[] values;
    // FIXME more member variables here

    public HashSet(int initArrayLength, int ratio, ProbeStrategy strategy) {
        // FIXME to be written
    }

    public boolean add(int value) {
        // FIXME to be written
        return false; // FIXME remove when ready
    }

    public boolean contains(int value) {
        // FIXME to be written
        return false; // FIXME remove when ready
    }

    public boolean remove(int value) {
        // FIXME to be written
        return false; // FIXME remove when ready
    }

    public int[] toArray() {
        int[] result = new int[this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            result[i] = this.values[i];
        }
        return result;
    }

    public static void main(String[] args) {
        ProbeStrategy linear = new SimpleLinearProbe();
        HashSet set = new HashSet(7, 3, linear);

        int[] numbers = {20, 5, 3, 9};
        for (int i = 0; i < numbers.length; i++) {
            // add the number
            set.add(numbers[i]);

            // print out the array
            int[] array = set.toArray();
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j] + ", ");
            }
            System.out.println();
        }
    }
}
