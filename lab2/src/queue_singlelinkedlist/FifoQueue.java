package queue_singlelinkedlist;

import java.util.*;

import queue_singlelinkedlist.FifoQueue.QueueNode;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		if (size <= 0) {
			last = new QueueNode<E>(e);
			last.next = last;
		} else {
			QueueNode<E> temp = last.next;
			last.next = new QueueNode<E>(e);
			last = last.next;
			last.next = temp;
		}
		// last = new QueueNode<E>(e);
		// if (last.next == null) {
		// last.next = last;
		// } else {
		// last.next = last.next.next;
		// }
		if (last.element.equals(e)) {
			this.size++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size <= 0) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {

		if (size <= 0) {
			return null;
		} else if (last.next.equals(last)) {
			E polled = last.element;
			last = null;
			size--;

			return polled;
		} else {
			E polled = last.next.element;
			last.next = last.next.next;
			size--;

			return polled;
		}
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is empty
	 * after the call.
	 * 
	 * @param q
	 *            the queue to append
	 * @throws IllegalArgumentException
	 *             if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
//		if (!this.equals(q)) {
//			if(this.size <= 0 && q.size > 0) {
//				this.size = q.size;
//				this.last = q.last;
//			}
//			else if (q.size <= 0 && this.size > 0) {
//				//do nothing
//			} else if (q.size == 0 && this.size == 0) {
//				//do nothing
//			} else {
//			size += q.size;
//			q.last.next = this.last.next;
//			this.last.next = q.last.next;
//			}
//		} else {
//			throw new IllegalArgumentException();
//		}
		if (this.equals(q)) {
			throw new IllegalArgumentException();
		}
		if (this.size > 0 && q.size > 0) {
			
			QueueNode<E> temp = q.last.next;
			q.last.next = this.last.next;
			this.last.next = temp;
			this.size = this.size + q.size;
			this.last = q.last;
			q.last = null;
			q.size = 0;
			
		} else if (this.size > 0 && q.size == 0) {
			//do nothing
			
		} else if (this.size == 0 && q.size > 0) {
			this.size = q.size;
			this.last = q.last;
			q.last = null;
			q.size = 0;
			
		}
		
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int itSize;

		/* Konstruktor */
		private QueueIterator() {
			pos = last;
			itSize = size;
		}

		public boolean hasNext() {
			if (itSize == 0) {
				return false;
			} else {
				return true;
			}

		}

		public E next() {
			if (itSize > 0) {
				E re = pos.next.element;
				pos = pos.next;
				itSize--;
				return re;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
