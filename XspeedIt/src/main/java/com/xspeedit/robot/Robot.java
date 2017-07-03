package com.xspeedit.robot;

import java.util.List;

import com.xspeedit.box.Box;

/**
 * The Interface Robot.
 */
public interface Robot {

	/**
	 * Pack items.
	 *
	 * @param items the items to pack
	 * @return the list of boxes containing the items
	 */
	public List<Box> packItems(List<Integer> items);
	
}
