package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ArrayWrapper;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.RanOutOfSpaceError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * FixedSizeList is a List with a maximum size.
 * @author jfoley
 *
 * @param <T>
 */
public class FixedSizeList<T> extends ListADT<T> {
	/**
	 * This is the array of fixed size.
	 */
	private ArrayWrapper<T> array;
	/**
	 * This keeps track of what we have used and what is left.
	 */
	private int fill;

	/**
	 * Construct a new FixedSizeList with a given maximum size.
	 * @param maximumSize - the size of the array to use.
	 */
	public FixedSizeList(int maximumSize) {
		this.array = new ArrayWrapper<>(maximumSize);
		this.fill = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	public int size() {
		return this.fill;
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		this.checkExclusiveIndex(index);
		this.array.setIndex(index, value);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		this.checkExclusiveIndex(index);
		return this.array.getIndex(index);
	}

	@Override
	public T getFront() {
		this.checkNotEmpty();
		return this.array.getIndex(0);
	}

	@Override
	public T getBack() {
		this.checkNotEmpty();
		return this.array.getIndex(fill-1);
	}

	@Override
	public void addIndex(int index, T value) {
	checkNotEmpty();
	for (int i = this.fill-1; i > index; i--) {
		this.array.setIndex(1, array.getIndex(i-1));
	}
	}

	@Override
	public void addFront(T value) {
		this.addIndex(0, value);
	}

	@Override
	public void addBack(T value) {
		if (fill < array.size()) {
			array.setIndex(fill++, value);
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public T removeIndex(int index) {
		// slide to the left
	T takeOut = this.array.getIndex(index);
	//take the index you want from the list and store it
	this.array.setIndex(index, this.array.getIndex(index+1));
	//store the value you took out as the next value you want
	for (int i = index; i < this.fill; i++) {
		this.array.setIndex(i, array.getIndex(i+1));
	}
	//create a for loop that slides down all the other values
	this.array.setIndex(fill, null);
	return takeOut;
	//erase the duplicate and return the value you stored before.
	}

	@Override
	public T removeBack() {
		throw new TODOErr();
	}

	@Override
	public T removeFront() {
		return removeIndex(0);
	}

	/**
	 * Is this data structure full? Used in challenge: {@linkplain ChunkyArrayList}.
	 * 
	 * @return if true this FixedSizeList is full.
	 */
	public boolean isFull() {
		return this.fill == this.array.size();
	}

}
