package com.xspeedit.box;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Box {
	
	private int size;
	
	protected List<Integer> items;
	
	public Box(int size) {
		this.size = size;
		this.items = new ArrayList<>();
	}
	
	public Box(Box box) {
		this.size = box.getSize();
		this.items = new ArrayList<>(box.getItems());
	}
	
	public boolean acceptItem(Integer item) {
		return getItemsSize() + item <= size;
	}
	
	public boolean isFull() {
		return getItemsSize() == size;
	}
	
	public int getItemsCount() {
		return this.items.size();
	}
	
	public void addItem(Integer item) {
		if (!acceptItem(item)) {
			throw new IllegalArgumentException(String.format("Illegal item %d.", item));
		}
		items.add(item);
	}
	
	public int getItemsSize() {
		int itemsSize = 0;
		for (Integer item : items) {
			itemsSize += item;
		}
		return itemsSize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Integer item : this.items) {
			sb.append(item);
		}
		return sb.toString();
	}
	
	
	
}
