package com.xspeedit.robot;

import java.util.ArrayList;
import java.util.List;

import com.xspeedit.box.Box;
import com.xspeedit.box.BoxWorkingSet;
import com.xspeedit.item.ClassifiedItems;

import lombok.Getter;



/**
 * The Class SmartRobot that should pack the items in an optimized way.
 * 
 * @author bonnardg
 */
@Getter
public class SmartRobot implements Robot {
	
	/** The box size. */
	private int boxSize;
	
	/**
	 * Instantiates a new smart robot.
	 *
	 * @param boxSize the box size
	 */
	public SmartRobot(int boxSize) {
		this.boxSize = boxSize;
	}
	
	/**
	 * This method will pack the items in an optimized way:
	 *  - It classifies the items per value
	 *  - It loops over the items by descending order: 
	 *     It always take the highest item from the available ones
	 *     And it tries the combination with the other items starting with its highest available complement
	 *     And continuing combination with available complement in descending order until it finds a combination :
	 *  	- Filling totally the box
	 *      - Or, when no other combination is possible, the combination using the highest count of items 
	 *         If there are two of these combinations, it takes the one which is filling the more space in the box
	 * 
	 * @param items the items to store int the boxes.
	 * @return the box created in an optimized way.
	 */
	public synchronized List<Box> packItems(List<Integer> items) {
	
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Illegal empty or null items.");
		}
		
		List<Box> boxes = new ArrayList<>();
		
		// Classify the items by value
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		Integer currentLeadingItem;
		Box electedBox;
		BoxWorkingSet boxWorkingSet;
		Integer boxSpaceComplement;
		
		// While there is still item to classify
		while (classifiedItems.existItems()) {
			// Taking the highest item value still to classify
			currentLeadingItem = classifiedItems.highestItem();
			// Removing highest item from the classification
			classifiedItems.removeItem(currentLeadingItem);
			// Calculates the complement to have a full box with the highest item
			boxSpaceComplement = boxSize - currentLeadingItem;
			// If we find this complement, boxing the items 
			// -> we can't optimize more if we have a full box with the highest item value
			if (classifiedItems.existItem(boxSpaceComplement)) {
				// Creating the box
				electedBox = new Box(boxSize);
				electedBox.addItem(currentLeadingItem);
				electedBox.addItem(boxSpaceComplement);
				// Adding the box to the result
				boxes.add(electedBox);
				// Removing complement item from the classification
				classifiedItems.removeItem(boxSpaceComplement);
			} else {
				// If we can't create a full box, we create a box working set with the highest item
				boxWorkingSet = new BoxWorkingSet(currentLeadingItem, boxSize);
				// We're looking for the nearest complement of the highest item
				boxSpaceComplement = classifiedItems.findNearestLowerItem(boxSpaceComplement);
				// While we can find a complement to the highest item
				while (boxSpaceComplement != null) {
					// We're trying box combination with the complement
					boxWorkingSet.addItem(boxSpaceComplement);
					// If we have created a full box, we will use it
					// -> we can't optimize more if we have a full box 
					//    with the highest item value and its nearest highest complement
					if (boxWorkingSet.hasFullBox()) {
						break;
					}
					boxSpaceComplement = classifiedItems.findNearestLowerItem(boxSpaceComplement);
				}
				// Adding the elected box from the working set to the result
				electedBox = boxWorkingSet.getElectedBox();
				boxes.add(electedBox);
				// Removing the box complement items from the classification
				classifiedItems.removeItems(electedBox.getComplementItems());
			}
		}
		
		return boxes;
		
	}

	
}
