package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {

	@Test
	public void deleteSomeGroup() {

		// save old state
		List<GroupData> oldList = app.getGroupHelper().getGroups();
		int index = getRandomIndexOfList(oldList.size());

		// actions
		app.getGroupHelper().deleteGroup(index);
		
		// save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();

		// compare states
		oldList.remove(index);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}
}
