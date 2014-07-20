package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

import static com.example.tests.RandomDataGenerator.getRandomIndexOfList;

public class GroupRemovalTests extends TestBase {

	@Test
	public void deleteSomeGroup() {

		// save old state
		SortedListOf<GroupData> oldList = app.getModel().getGroups();

		// actions
		int index = getRandomIndexOfList(oldList.size());
		app.getGroupHelper().deleteGroup(index);

		// save new state
		SortedListOf<GroupData> newList = app.getModel().getGroups();

		// compare states
		assertThat(newList, equalTo(oldList.without(index)));
		assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper()
				.listGroups()));
		assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper()
				.getUiGroups()));

	}
}
