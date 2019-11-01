package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Doubly-Linked List is a list based on nodes that know of their successor
 * and predecessor.
 * 
 * @author jfoley
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends ListADT<T> {
	/**
	 * This is a reference to the first node in this list.
	 */
	private Node<T> start;
	/**
	 * This is a reference to the last node in this list.
	 */
	private Node<T> end;

	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}

	@Override
	public T removeFront() {
		checkNotEmpty();
		T firstValue = start.value;
		this.start = start.after;
		if(start == null) {
			end = null;
	}else {
		start.before = null;
	}
		return firstValue;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if (start.after == null) {
			return removeFront();
		}
		T removedValue = end.value;
		end = end.before;
		end.after = null;
		return removedValue;

	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				n.after = n.after.after;
				n.after.before = n;
				return n.value;
			}

		}
		throw new BadIndexError(index);
	}

	@Override
	public void addFront(T item) {
		if (start == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> addSecond = start;
			addSecond = new Node<T>(item);
			start.after = addSecond;
			addSecond.before = start;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		checkNotEmpty();
		if (index < 0 || index > size()) {
			throw new BadIndexError(index);
		}
		if (index == 0) {
			addFront(item);
		}
		if (index == size()) {
			addBack(item);
		} else {
			int at = 0;
			for (Node<T> n = this.start; n != null; n = n.after) {
				if (at++ == index) {
					Node<T> left = n.before;
					Node<T>  add = new Node<T>(item);
					left.after = add;
					n.before = add;
					add.before = left;
					add.after = n;
					return;
				}
				at++;
			}
		}
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		T t = start.value;
		for (Node<T> n = this.start; n.after != null; n = n.after) {
			t = n.after.value;
		}
		return t;
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	public void setIndex(int index, T value) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				n.value = value;
				return;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public int size() {
		int Count = 0;
		;
		for (Node<T> n = this.start; n != null; n = n.after) {
			Count++;
		}
		return Count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null && this.end == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of DoublyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
