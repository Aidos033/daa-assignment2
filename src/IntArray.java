public class IntArray {
    private static final int DEFAULT_CAPACITY = 8;

    private int[] values;
    private int size;

    public IntArray() {
        values = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        values[size++] = value;
    }

    public void add(int index, int value) {
        checkPositionIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    public int get(int index) {
        checkElementIndex(index);
        return values[index];
    }

    public void set(int index, int value) {
        checkElementIndex(index);
        values[index] = value;
    }

    public int remove(int index) {
        checkElementIndex(index);
        int removed = values[index];
        int moved = size - index - 1;
        if (moved > 0) {
            System.arraycopy(values, index + 1, values, index, moved);
        }
        values[--size] = 0;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int[] additions) {
        if (additions == null) {
            throw new NullPointerException("values must not be null");
        }
        if (additions.length == 0) {
            return;
        }
        ensureCapacity(size + additions.length);
        System.arraycopy(additions, 0, values, size, additions.length);
        size += additions.length;
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity <= values.length) {
            return;
        }
        int newCapacity = Math.max(values.length * 2, minimumCapacity);
        int[] expanded = new int[newCapacity];
        System.arraycopy(values, 0, expanded, 0, size);
        values = expanded;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(values[i]);
        }
        return result.append(']').toString();
    }
}
