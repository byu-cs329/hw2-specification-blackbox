package misc;

public class Queue {
  Object[] arr;
  int size;
  int first;
  int last;

  /**
   * Creates a queue with a bounded size.
   * 
   * @param max   the size of the queue
   */
  public Queue(int max) {
    arr = new Object[max];
    size = 0;
    first = 0;
    last = 0;
  }

  public int size() {
    return size;
  }

  public int max() {
    return arr.length;
  }

  /**
   * Adds a new object into the queue.
   * 
   * @param x the object to add to the queue
   */
  public void enqueue(Object x) {
    arr[last] = x;
    last++;
    if (last == arr.length) {
      last = 0;
    }
    size++;
  }

  /**
   * Removes an object from the queue.
   * 
   * @return the removed object
   */
  public Object dequeue() {
    if (size == 0) {
      return null;
    }
    final Object x = arr[first];
    first++;
    if (first == arr.length) {
      first = 0;
    }
    size--;
    return x;
  }
}
