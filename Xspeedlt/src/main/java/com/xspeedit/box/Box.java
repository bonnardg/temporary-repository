package com.xspeedit.box;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Box {
	
	private int size;
	
	private List<Integer> items;
	
	private int itemsSize;
	

	public Box(int size) {
		this.size = size;
		this.items = new ArrayList<>();
		this.itemsSize = 0;
	}
	
	public void addItem(Integer item) {
		if (!acceptItem(item)) {
			throw new IllegalArgumentException(String.format("Illegal item %d.", item));
		}
		items.add(item);
		itemsSize += item;
	}
	
	public boolean acceptItem(Integer item) {
		return itemsSize + item <= size;
	}
	
	public boolean isFull() {
		return itemsSize == size;
	}
	
	public int getItemsCount() {
		return this.items.size();
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
