package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import static com.example.fw.GroupHelper.CREATION;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

	@Test(dataProvider = "randomValidGroupGenerator")
	public void testGroupCreationWithValidData(GroupData group)
			throws Exception {
		

		// save old state
		List<GroupData> oldList = app.getGroupHelper().getGroups();

		// actions
		app.getGroupHelper().createGroup(group);

		// save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();

		// compare states
		GroupData newGroup = app.getGroupHelper().transformGroupToVisibleOnGroupsPage(group, group, CREATION);
		oldList.add(newGroup);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}





}
