package com.example.tests;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

import static com.example.fw.GroupHelper.CREATION;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupCreationTests extends TestBase {

	@Test(dataProvider = "randomValidGroupGenerator")
	public void testGroupCreationWithValidData(GroupData group)
			throws Exception {
		

		// save old state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();

		// actions
		app.getGroupHelper().createGroup(group);

		// save new state
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();

		// compare states
		assertThat(newList, equalTo(oldList.withAdded(app.getGroupHelper().transformGroupToVisibleOnGroupsPage(group, group, CREATION))));

	}





}
