package com.xspeedit.item;

import java.util.List;
import java.util.SortedMap;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FakeClassifiedItems extends ClassifiedItems {

	@Builder
	public FakeClassifiedItems(List<Integer> items) {
		super(items);
	}
	
	public SortedMap<Integer, Integer> getCountPerItem() {
		return countPerItem;
	}
	
}
