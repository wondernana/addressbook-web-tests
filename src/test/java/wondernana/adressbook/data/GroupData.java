package wondernana.adressbook.data;

import java.util.Objects;

public class GroupData {
	private final String name;
	private final String header;
	private final String footer;
	private String parentGroup;

	public GroupData(String name, String header, String footer, String parentGroup) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		this.parentGroup = parentGroup;
	}

	public String getName() {
		return name;
	}

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	public String getParentGroup() {
		return parentGroup;
	}

	@Override
	public String toString() {
		return "GroupData{" +
				"name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GroupData groupData = (GroupData) o;
		return Objects.equals(name, groupData.name) && Objects.equals(parentGroup, groupData.parentGroup);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, parentGroup);
	}
}
