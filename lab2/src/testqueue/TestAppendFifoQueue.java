package testqueue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	
	@Before
	public void setUp() throws Exception{
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}
	
	@After
	public void tearDown() {
		q1 = null;
		q2 = null;
	}
	
	@Test
	public void testAppendTwoEmpty() {
		q1.append(q2);
		assertEquals("Appended empty queues results in empty queue", q1.poll(), null);
		assertEquals("q2 is empty after append", q2.peek(), null);
	}
	
	@Test
	public void testAppendEmptyToNonEmpty() {
		for(int i = 1; i <= 5; i++) {
			q2.offer(i);
		}
		q1.append(q2);
		assertFalse("Appending empty and non-empty queues results in non-empty queue", q1.peek() == null);
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals("Poll returns incorrect element", i, k);
		}
		
		assertEquals("q2 is empty after append", q2.peek(), null);
	}
	
	@Test
	public void testAppendNonEmptyToEmpty() {
		for(int i = 1; i <= 5; i++) {
			q1.offer(i);
		}
		q1.append(q2);
		assertFalse("Appending non-empty and empty queues results in non-empty queue", q1.peek() == null);
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals("Poll returns incorrect element", i, k);
		}

		assertEquals("q2 is empty after append", q2.poll(), null);
	}
	
	@Test
	public void testAppendNonEmptyToNonEmpty() {
		for(int i = 1; i <= 5; i++) {
			q1.offer(i);
		}
		for(int i = 6; i <= 10; i++) {
			q2.offer(i);
		}
		q1.append(q2);
		assertFalse("Appending two non-empty queues results in non-empty queue", q1.peek() == null);
		for (int i = 1; i <= 10; i++) {
			int k = q1.poll();
			assertEquals("Poll returns incorrect element", i, k);
		}
		assertEquals("q2 is empty after append", q2.peek(), null);
	}
	
	@Test
	public void testAppendToSelf() {
		
		try {
			q1.append(q1);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// successful test
		}
	}

}
