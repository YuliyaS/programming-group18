package com.example.fw;

import java.util.List;

import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class ApplicationModel {
	
	private SortedListOf<GroupData> groups;
	
	public SortedListOf<GroupData> getGroups() {
		return new SortedListOf<GroupData>(groups);
	}
	
	public void setGroups(List<GroupData> groups) {
		this.groups = new SortedListOf<GroupData>(groups);
	}

	public ApplicationModel addGroup(GroupData group) {
		if (group.getName() == null) {
			group.setName("");
		}
		if (group.getFooter() == null) {
			group.setFooter("");
		}
		if (group.getHeader() == null) {
			group.setHeader("");
		}
		groups.add(group);
		return this;
	}

	public ApplicationModel removeGroup(int index) {
		groups.remove(index);
		return this;
	}

}
