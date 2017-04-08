package technopolis.homework.first;

import org.junit.Test;

import java.sql.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by igor on 10.03.17.
 */
public class LinkedListTest {
    public LinkedList<Integer> integerLinkedList = new LinkedList<>();

    @Test
    public void testSize() {
        final int MAX_SIZE = 30;
        assertEquals(0,integerLinkedList.size());

        for (int i = 0; i < MAX_SIZE; ++i) {
            integerLinkedList.add(i);
            assertEquals(i+1,integerLinkedList.size());
        }

        for (int i = 1; i <= MAX_SIZE; ++i) {
            final int first = 0;
            assertEquals(i-1,integerLinkedList.remove(first).intValue());
            assertEquals(MAX_SIZE - i,integerLinkedList.size());
        }
    }

    @Test
    public void testClear() {
        final int randomValue = 454;
        integerLinkedList.add(randomValue);
        assertFalse(integerLinkedList.isEmpty());

        integerLinkedList.clear();

        assertTrue(integerLinkedList.isEmpty());
        assertTrue(integerLinkedList.size() == 0);

        final Random random = new Random();

        final int MAX_SIZE = 10000;
        for (int i = 0; i < MAX_SIZE; ++i) {
            integerLinkedList.add(random.nextInt());
        }

        assertFalse(integerLinkedList.isEmpty());

        integerLinkedList.clear();

        assertTrue(integerLinkedList.isEmpty());
        assertTrue(integerLinkedList.size() == 0);
    }

    @Test
    public void testAddingAtEnd() {
        final int MAX_SIZE = 10000;

        for (int i = 0; i < MAX_SIZE; ++i) {
            integerLinkedList.add(i);
        }

        for (int i = 0; i < MAX_SIZE; ++i) {
            assertEquals(integerLinkedList.get(i).intValue(), i);
        }
    }

    @Test
    public void testAddingAtCenter() {
        final int ITERATION_AMOUNT = 10000;
        final int START_SIZE = 100;

        for (int i = 0; i < START_SIZE; ++i) {
            integerLinkedList.add(0);
        }

        final Random random = new Random();
        for (int i = 0; i < ITERATION_AMOUNT; ++i) {
            final int addingValue = random.nextInt();
            final int position = random.nextInt(integerLinkedList.size());

            integerLinkedList.add(position,addingValue);

            assertEquals(integerLinkedList.get(position).intValue(), addingValue);
        }
    }

    @Test
    public void testContains() {
        final int MAX_SIZE = 1000;
        final int MAX_VALUE = 1000000;

        final Random random = new Random();
        for (int i = 0; i < MAX_SIZE; ++i) {
            final int addingValue = random.nextInt(MAX_VALUE);
            integerLinkedList.add(addingValue);

            assertTrue(integerLinkedList.contains(addingValue));
        }

        //something that bigger than MAX_VALUE isn't contains
        for (int i = 0; i < MAX_SIZE; ++i) {
            final int nonContainingValue = MAX_VALUE + random.nextInt(MAX_VALUE);
            assertFalse(integerLinkedList.contains(nonContainingValue));
        }
    }

    @Test
    public void testIndexOf() {
        final int ITERATION_AMOUNT = 10000;
        final int START_SIZE = 100;

        for (int i = 0; i < START_SIZE; ++i) {
            integerLinkedList.add(0);
        }

        final Random random = new Random();
        for (int i = 0; i < ITERATION_AMOUNT; ++i) {
            final int addingValue = random.nextInt();

            integerLinkedList.add(addingValue);
            assertEquals(integerLinkedList.lastIndexOf(addingValue), integerLinkedList.size() - 1);
        }
        integerLinkedList.clear();

        for (int i = 0; i < ITERATION_AMOUNT; ++i) {
            integerLinkedList.add(i);
        }

        for (int i = 0; i < ITERATION_AMOUNT; ++i) {
            assertEquals(i,integerLinkedList.lastIndexOf(i));
        }
    }

    @Test
    public void addAllTest() {
        final List<Collection<Integer>> collection = new ArrayList<>();

        collection.add(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
        collection.add(new java.util.LinkedList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
        collection.add(new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
        collection.add(new ArrayDeque<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));

        collection.stream().forEach(this::testAddAllCollection);
    }

    private void testAddAllCollection(final Collection collection) {
        integerLinkedList.addAll(collection);

        assertEquals(collection.size(), integerLinkedList.size());

        for (int i = 0; i < collection.size(); ++i) {
            assertEquals(i,integerLinkedList.get(i).intValue());
        }

        final int POS = 3;
        integerLinkedList.addAll(POS,collection);

        assertEquals(collection.size()*2,integerLinkedList.size());

        for (int i = POS; i < POS + collection.size(); ++i) {
            assertEquals(i-POS,integerLinkedList.get(i).intValue());
        }

        integerLinkedList.clear();
        assertTrue(integerLinkedList.isEmpty());
    }

    @Test
    public void toArrayTest() {
        integerLinkedList.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

        assertEquals("[0,1,2,3,4,5,6,7,8,9]", Arrays.toString(integerLinkedList.toArray()).replace(" ", ""));
        assertEquals("[9,8,7,6,5,4,3,2,1,0]",Arrays.toString(integerLinkedList.reverse().toArray()).replace(" ", ""));
    }

    @Test
    public void removeTest() {
        final int MAX_VALUE = 100;
        final List<Integer> values = new ArrayList<>(MAX_VALUE);
        for (int i = 0; i < MAX_VALUE; ++i) {
            values.add(i);
        }

        integerLinkedList.addAll(values);

        final Random random = new Random();
        for (int i = 0; i < MAX_VALUE; ++i) {
            final int removingIndex = random.nextInt(values.size());

            assertEquals(values.remove(removingIndex),
                    integerLinkedList.remove(removingIndex));

            assertEquals(Arrays.toString(values.toArray()),
                    Arrays.toString(integerLinkedList.toArray()));
        }
    }

    @Test
    public void testEquals() {
        final LinkedList<Integer> second = new LinkedList<>();
        final LinkedList<Integer> first = new LinkedList<>();

        final Random random = new Random();
        final int MAX = 10;
        for (int i = 0; i < MAX; ++i) {
            final int value = random.nextInt();
            second.add(value);
            integerLinkedList.add(value);
        }

        assertTrue(integerLinkedList.equals(second));
        assertFalse(integerLinkedList.equals(first));
    }
}
