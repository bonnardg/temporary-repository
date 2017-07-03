package com.xspeedit.box;

import java.util.List;

import lombok.Builder;

public class FakeBox extends Box {

	@Builder
	public FakeBox(int size, List<Integer> items) {
		super(size);
		this.items = items;
	}
	
}
