package com.example.tests;

import static com.example.fw.GroupHelper.MODIFICATION;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase {

	@Test(dataProvider = "randomValidGroupGenerator")
	public void modifySomeGroup(GroupData group) {

		// save old state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();
		int index = getRandomIndexOfList(oldList.size());
		GroupData oldGroup = oldList.get(index);

		// actions
		app.getGroupHelper().modifyGroup(group, index);

		// save new state
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();

		// compare states
		assertThat(
				newList,
				equalTo(oldList.without(index).withAdded(
						app.getGroupHelper()
								.transformGroupToVisibleOnGroupsPage(group,
										oldGroup, MODIFICATION))));

	}

}
