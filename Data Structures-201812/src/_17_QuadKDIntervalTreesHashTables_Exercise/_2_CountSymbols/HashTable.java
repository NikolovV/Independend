package _17_QuadKDIntervalTreesHashTables_Exercise._2_CountSymbols;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<TKey extends Comparable<TKey>, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private int size;
    private int capacity;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private LinkedList<KeyValue<TKey, TValue>>[] slots;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.slots = new LinkedList[capacity];
        this.size = 0;
    }

    public void add(TKey key, TValue value) {
        this.growIfNeeded();
        int slotNumber = this.findSlotNumber(key);

        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists: " + key);
            }
        }

        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.size++;
    }

    private int findSlotNumber(TKey key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    private void growIfNeeded() {
        if (this.size() + 1 > this.capacity() * LOAD_FACTOR) {
            this.resize();
        }
    }

    private void resize() {
        HashTable<TKey, TValue> newTable = new HashTable<>(this.capacity * 2);
        this.capacity *= 2;

        for (KeyValue<TKey, TValue> element : this) {
            newTable.add(element.getKey(), element.getValue());
        }

        this.slots = newTable.slots;
        this.size = newTable.size;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        this.growIfNeeded();
        int slotNumber = this.findSlotNumber(key);

        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                element.setValue(value);
                return false;
            }
        }

        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.size++;
        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> element = this.find(key);
        if (element == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return element.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        return this.find(key) != null;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];
        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    return element;
                }
            }
        }
        return null;
    }

    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    public boolean remove(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];

        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    elements.remove(element);
                    this.size--;

                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        this.setSize(0);
        this.slots = new LinkedList[INITIAL_CAPACITY];
    }

    public Iterable<TKey> keys() {
        LinkedList<TKey> keys = new LinkedList<>();

        for (LinkedList<KeyValue<TKey, TValue>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<TKey, TValue> pair : slot) {
                    keys.add(pair.getKey());
                }
            }
        }

        return keys;
    }

    public Iterable<TValue> values() {
        LinkedList<TValue> values = new LinkedList<>();

        for (LinkedList<KeyValue<TKey, TValue>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<TKey, TValue> pair : slot) {
                    values.add(pair.getValue());
                }
            }
        }

        return values;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        LinkedList<KeyValue<TKey, TValue>> elements = new LinkedList<>();

        for (LinkedList<KeyValue<TKey, TValue>> element : this.slots) {
            if (element != null) {
                elements.addAll(element);
            }
        }

        return elements.iterator();
    }

    public Iterable<KeyValue<TKey, TValue>> sortPairs() {
        LinkedList<KeyValue<TKey, TValue>> result = new LinkedList<>();

        for (LinkedList<KeyValue<TKey, TValue>> slot : this.slots) {
            if (slot != null) {
                result.addAll(slot);
            }
        }

        result.sort(Comparator.comparing(KeyValue::getKey));

        return result;
    }

}