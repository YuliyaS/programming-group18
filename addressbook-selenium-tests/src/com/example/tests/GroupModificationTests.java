package com.example.tests;

import static com.example.fw.GroupHelper.MODIFICATION;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

	@Test(dataProvider = "randomValidGroupGenerator")
	public void modifySomeGroup(GroupData group) {

		// save old state
		List<GroupData> oldList = app.getGroupHelper().getGroups();
		int index = getRandomIndexOfList(oldList.size());
		GroupData oldGroup = oldList.get(index);

		// actions
		app.getGroupHelper().modifyGroup(group, index);

		// save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();
		GroupData newGroup = app.getGroupHelper()
				.transformGroupToVisibleOnGroupsPage(group, oldGroup,
						MODIFICATION);

		// compare states
		oldList.remove(index);
		oldList.add(index, newGroup);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
