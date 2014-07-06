package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.example.fw.GroupHelper.CREATION;
import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXMLFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> groupsFromFile() throws IOException {
		return wrapGroupsForDataProvider(loadGroupsFromXMLFile(new File("groups.xml"))).iterator();
	}


	@Test(dataProvider = "groupsFromFile")
	public void testGroupCreationWithValidData(GroupData group)
			throws Exception {

		// save old state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();

		// actions
		app.getGroupHelper().createGroup(group);

		// save new state
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
		GroupData newGroup = app.getGroupHelper()
				.transformGroupToVisibleOnGroupsPage(group, group, CREATION);

		// compare states
		assertThat(newList, equalTo(oldList.withAdded(newGroup)));

	}

}
