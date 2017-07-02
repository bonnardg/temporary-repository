package com.xspeedit.robot;

import java.util.ArrayList;
import java.util.List;

import com.xspeedit.box.Box;
import com.xspeedit.box.BoxWorkingSet;
import com.xspeedit.item.ClassifiedItems;

import lombok.Getter;

@Getter
public class SmartRobot implements Robot {
	
	private int boxSize;
	
	public SmartRobot(int boxSize) {
		this.boxSize = boxSize;
	}
	
	public synchronized List<Box> packItems(List<Integer> items) {
	
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Illegal empty or null items.");
		}
		
		List<Box> boxes = new ArrayList<Box>();
		
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		Integer currentLeadingItem;
		Box electedBox;
		BoxWorkingSet boxWorkingSet;
		Integer boxSpaceComplement;
		
		while (classifiedItems.existItems()) {
			currentLeadingItem = classifiedItems.highestItem();
			boxSpaceComplement = boxSize - currentLeadingItem;
			if (classifiedItems.existItem(boxSpaceComplement)) {
				electedBox = new Box(boxSize);
				electedBox.addItem(currentLeadingItem);
				electedBox.addItem(boxSpaceComplement);
				boxes.add(electedBox);
				classifiedItems.removeBoxItems(electedBox);
			} else {
				boxWorkingSet = new BoxWorkingSet(currentLeadingItem, boxSize);
				boxSpaceComplement = classifiedItems.findNearestLowerItem(boxSpaceComplement);
				while (boxSpaceComplement != null) {
					boxWorkingSet.addItem(boxSpaceComplement);
					if (boxWorkingSet.hasFullBox()) {
						break;
					}
					boxSpaceComplement = classifiedItems.findNearestLowerItem(boxSpaceComplement);
				}
				electedBox = boxWorkingSet.getElectedBox();
				boxes.add(electedBox);
				classifiedItems.removeBoxItems(electedBox);
			}
		}
		
		return boxes;
		
	}

	
}
