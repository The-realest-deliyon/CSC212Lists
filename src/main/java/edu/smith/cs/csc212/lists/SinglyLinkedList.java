package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Singly-Linked List is a list that has only knowledge of its very first
 * element. Elements after that are chained, ending with a null node.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();
		T firstValue = this.start.value;
		this.start = this.start.next;
		return firstValue;
	}

	@Override
	public T removeBack() {
		// find a way to call ( 2 nodes ) before null
		// create a for loop that removes an element
		checkNotEmpty();
		if (size() == 1) {
			T value = this.start.value;
			this.start = null;
			return value;
		}
		Node<T> secondLast = null;
		int i = 0;
		for (Node<T> n = this.start; n.next != null; n = n.next) {
			secondLast = n;
			if (i++ > 1000) {
				throw new AssertionError("blash");
			}
		}
		T value = secondLast.next.value;
		secondLast.next = null;
		return value;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		if (index < 0 || index >= size()) {
			throw new BadIndexError(index);
		}
		if (index == 0) {
			T deleteThis = start.value;
			start = start.next;
			return deleteThis;
		} else {
			T deleteThis = null;
			int at = 0;
			for (Node<T> n = this.start; n != null; n = n.next) {
				if (at == index - 1) {
					deleteThis = n.next.value;
					n.next = n.next.next;
					break;
				}
			}
			return deleteThis;
		}
	}

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		if (this.start == null) {
			addFront(item);
			return;
		}

		Node<T> last = null;
		for (Node<T> current = this.start; current != null; current = current.next) {
			last = current;
		}
		assert (last.next == null);
		last.next = new Node<T>(item, null);
	}

	@Override
	public void addIndex(int index, T item) {
		if (index < 0 || index >= size()) {
			throw new BadIndexError(index);
		}
		int at = 0;
		Node<T> addIndex = null;
		if (index == 0) {
			addFront(item);
		}

		else if (index == size()) {
			addBack(item);
		} else {
			for (Node<T> n = this.start; n != null; n = n.next) {
				if (at == index - 1) {
					addIndex = new Node<T>(item, n.next);
					n.next = addIndex;
					break;
				}
				at++;
			}
		}
		
	}
	// store all three values
	// create a node that stores the value you want to add and the value after
	// create a node.previous connecting to the new node you want to store in the
	// list
	// for (int i = this.fill-1; i > index; i--) {this.array.setIndex(1,
	// array.getIndex(i-1));}
	// ^use this for loop, but redefine fill.

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		T t = start.value;
		for (Node<T> n = this.start; n.next != null; n = n.next) {
			t = n.next.value;
		}
		return t;
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				n.value = value;
				return;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 * @param next  - the successor to this node.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

}
