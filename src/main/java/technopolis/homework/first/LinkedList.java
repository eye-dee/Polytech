package technopolis.homework.first;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */
public class LinkedList<T> implements List<T>, Iterable<T>{

    /**
     * N - количество элементов в списке
     * инвариант:
     * size - количество элементов между head и tail включительно
     * если size == 0, то head == tail == null и наоборот
     * если head != null, то рано или поздно head.next...next == tail
     */

    public class Node {
        private T information;
        private Node next;
        private Node previous;

        public Node(final T information) {
            this.information = information;
            this.next = null;
            this.previous = null;
        }

        public Node(final T information, final Node next, final Node previous) {
            this.information = information;
            this.next = next;
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public T getInformation() {
            return information;
        }

        public void setInformation(final T information) {
            this.information = information;
        }

        public void setNext(final Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(final Node previous) {
            this.previous = previous;
        }
    }

    private Node head;
    private Node tail;

    //LinkedList<T> reverseList;

    private int size;

    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    //O(1)
    //пред условие - size >= 0
    //пост условие - size == size + 1
    @Override
    public boolean add(final T t) {
        if (head == null) {
            tail = head = new Node(t);
        } else {
            final Node newElement = new Node(t, null, tail);
            tail.setNext(newElement);
            tail = newElement;
        }
        ++size;

        //reverseList.add(0,t);

        return true;
    }

    @Override
    //todo add can return false or not?
    //O(collection.size())
    //пред условие - size >= 0
    //пост условие - size == size + collection.size()
    public boolean addAll(final Collection<? extends T> collection) {
        return collection.stream().allMatch(this::add);
        //todo is it the same?
        //collection.forEach(t -> add(t));
        //return true;
    }

    @Override
    //todo the same like at addAll(Collection collection)
    //O(collection.size()*N)
    //пред условие - size >= 0
    //пост условие - size == size + collection.size()
    public boolean addAll(final int i, final Collection<? extends T> collection) {
        final List<T> list = collection.stream().collect(Collectors.toList());
        Collections.
                reverse(list);

        list.forEach(value -> add(i,value));

        return true;
    }

    @Override
    //O(1)
    //пред условие любое
    //пост лист останется неизменным
    public int size() {
        return size;
    }

    public class LinkedListIterator implements ListIterator<T> {
        private Node current;
        private int i;

        //todo
        //don't use it
        LinkedListIterator(final Node current) {
            this.current = current;
            i = 0;
        }

        LinkedListIterator(final Node current, final int i) {
            this.current = current;
            this.i = i;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public T next() {
            final T value = current.getInformation();
            current = current.getNext();
            ++i;
            return value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Iterator)) return false;

            final LinkedListIterator that = (LinkedListIterator) o;

            return current != null ? current.equals(that.current) : that.current == null;
        }

        @Override
        public int hashCode() {
            return current != null ? current.hashCode() : 0;
        }

        @Override
        public boolean hasPrevious() {
            return (current != null);
        }

        @Override
        public T previous() {
            final T value = current.getInformation();
            current = current.getPrevious();
            --i;
            return value;
        }

        @Override
        public int nextIndex() {
            return i+1;
        }

        @Override
        public int previousIndex() {
            return i-1;
        }

        @Override
        public void remove() {
            final Node afterRemoving = current.getNext();
            final Node beforeRemoving = current.getPrevious();

            if (beforeRemoving != null) {
                beforeRemoving.setNext(afterRemoving);
            } else {
                head = head.getNext();
            }
            if (afterRemoving != null) {
                afterRemoving.setPrevious(beforeRemoving);
            } else {
                tail = tail.getPrevious();
            }
        }

        @Override
        public void set(final T t) {
            current.setInformation(t);
        }

        @Override
        public void add(final T t) {
            final Node after = current.getNext();

            final Node newNode = new Node(t,after,current);
            current.setNext(newNode);
            after.setPrevious(newNode);
        }
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        private Node current;

        LinkedListReverseIterator(final Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public T next() {
            final T value = current.getInformation();
            current = current.getPrevious();
            return value;
        }

        @Override
        public void remove() {
            final Node afterRemoving = current.getNext();
            final Node beforeRemoving = current.getPrevious();

            if (beforeRemoving != null) {
                beforeRemoving.setNext(afterRemoving);
            } else {
                head = head.getNext();
            }
            if (afterRemoving != null) {
                afterRemoving.setPrevious(beforeRemoving);
            } else {
                tail = tail.getPrevious();
            }
        }
    }

    @Override
    //O(1)
    public Iterator<T> iterator() {
        return new LinkedListIterator(head);
    }

    @Override
    //O(N)
    public Object[] toArray() {
        final Object[] resultArray = new Object[size];

        //todo iterating or foreach or hardcode handle ?
        final Iterator iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            resultArray[i++] = iterator.next();
        }
        return resultArray;
    }

    //todo what is it?
    @Override
    public Object[] toArray(final Object[] objects) {
        return new Object[0];
    }

    //todo what is it?
    @Override
    public boolean retainAll(final Collection collection) {
        return false;
    }

    @Override
    //O(1)
    //пред условие - size >= 0
    //пост условие - size == 0 + head == null + tail == null
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    //O(N)
    //пред условие i >= 0 i < size
    //пост лист останется неизменным
    public T get(final int i) {
        return find(i).getInformation();
    }

    @Override
    //O(N)
    //пред i >= 0 i < size
    //пост условие iый элемент == t
    public T set(final int i, final T t) {
        final Node temp = find(i);
        temp.setInformation(t);
        return temp.getInformation();
    }

    //O(N)
    //пред условие i >= 0 i < size
    //пост лист останется неизменным
    private Node find(final int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index :" + i + " size : " + size);
        }

        Node node = head;

        for (int j = 0; j < i; ++j) {
            node = node.getNext();
        }

        return node;
    }

    @Override
    //O(N)
    //пред условие i >= 0 i < size
    //пост лист останется size = size + 1
    public void add(final int i, final T t) {
        if (i == 0) {
            final Node newNode = new Node(t,head,null);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            final Node temp = find(i);
            final Node beforeTemp = temp.getPrevious();
            final Node newNode = new Node(t,temp,beforeTemp);
            temp.setPrevious(newNode);
            if (beforeTemp != null) {
                beforeTemp.setNext(newNode);
            }
        }
        //reverseList.add(size - i - 1, t);
        ++size;
    }

    @Override
    //O(N)
    //пред условие size >= 0 i >= 0 i < size
    //пост лист size = size - 1
    public T remove(final int i) {
        //reverseList.remove(size - i - 1);
        final Node removing = find(i);
        final Node afterRemoving = removing.getNext();
        final Node beforeRemoving = removing.getPrevious();

        if (beforeRemoving != null) {
            beforeRemoving.setNext(afterRemoving);
        } else {
            head = head.getNext();
        }
        if (afterRemoving != null) {
            afterRemoving.setPrevious(beforeRemoving);
        } else {
            tail = tail.getPrevious();
        }

        --size;
        return removing.getInformation();
    }

    @Override
    //O(N)
    //пред условие любое
    //пост лист останется неизменным
    public int indexOf(final Object o) {
        final Iterator<T> iterator = iterator();

        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.next().equals(o)) {
                return i;
            }
            ++i;
        }

        return -1;
    }

    @Override
    //O(N)
    //пред условие любое
    //пост лист останется неизменным
    public int lastIndexOf(final Object o) {
        final Iterator<T> reverseIterator = new LinkedListReverseIterator(tail);

        int i = size;
        while (reverseIterator.hasNext()) {
            --i;
            if(reverseIterator.next().equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return new LinkedListIterator(head,0);
    }

    @Override
    public ListIterator listIterator(final int i) {
        return new LinkedListIterator(find(i),i);
    }

    @Override
    //O(N)
    //пред условие i и i1 >= 0 < size i < i1
    //пост лист останется неизменным
    //возвратим [i,i1]
    public List<T> subList(final int i, final int i1) {
        if (i > i1) {
            throw new IllegalArgumentException("Left edge bigger than right (" + i + ":" + i1 + ")");
        }

        final Iterator<T> start = new LinkedListIterator(find(i));
        final Iterator<T> end = new LinkedListIterator(find(i1));

        final List<T> resultList = new LinkedList<>();
        while (!start.equals(end)) {
            resultList.add(start.next());
        }
        return resultList;
    }

    @Override
    //O(N)
    //пред условие любое
    //пост лист останется неизменным
    public boolean contains(final Object o) {
        final Iterator<T> iterator = new LinkedListIterator(head);

        while (iterator.hasNext()) {
            final T value = iterator.next();
            if (value.equals(o)){
                return true;
            }
        }

        return false;
    }

    @Override
    //O(N*collection.size())
    //пред условие любое
    //пост лист останется неизменным
    public boolean containsAll(final Collection collection) {
        return collection.stream().allMatch(this::contains);
    }

    @Override
    //O(1)
    //пред условие любое
    //пост лист останется неизменным
    public boolean isEmpty() {
        //todo or head == null
        return (size == 0);
    }

    @Override
    //O(N)
    //пред условие size > 0 list contains o
    //пост лист size = size - 1
    public boolean remove(final Object o) {
        final Iterator<T> iterator = new LinkedListIterator(head);
        Iterator<T> prev = new LinkedListIterator(null);

        boolean contains = false;
        int i = 0;
        while (iterator.hasNext()) {
            prev = iterator;
            final T value = iterator.next();
            i++;
            if (value.equals(o)){
                contains = true;
                break;
            }
        }

        if (contains) {
            prev.remove();
            //reverseList.remove(size - i);
            return true;
        } else {
            return false;
        }
    }

    @Override
    //O(N*collection.size())
    //пред условие любое
    //пост лист size == 0 next == tail == null
    public boolean removeAll(final Collection<?> collection) {
        return collection.stream().allMatch(this::remove);
        //todo is it the same?
        //collection.forEach(o -> remove(o));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LinkedList<?> that = (LinkedList<?>) o;

        if (size != that.size) return false;

        final Iterator iterator = that.iterator();
        final Iterator iterator1 = iterator();

        while (iterator.hasNext()) {
            if (!iterator.next().equals(iterator1.next())){
                return false;
            }
        }

        return true;
    }

    @Override
    //пред условие любое
    //пост лист останется неизменным
    public int hashCode() {
        return stream().mapToInt(value-> value.hashCode()).sum();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stream().forEach(
                value->stringBuilder.append(value.toString())
        );

        return stringBuilder.toString();
    }

    //пред условие любое
    //пост лист останется неизменным
    public List<T> reverse() {
        //return reverseList;

        final List<T> res = new LinkedList<>();
        final Iterator iterator = new LinkedListReverseIterator(tail);

        while (iterator.hasNext()) {
            res.add((T)iterator.next());
        }

        return res;
    }
}
