package Application.BE;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    private Integer id;
    private String groupName;
    private List<Integer> members;
    private Citizen assignedCitizen;

    public Group()
    {
        this.id = -1;
        this.assignedCitizen = null;
        this.groupName = "Unknown";
        this.members = new ArrayList<>();
    }

    public Group(Integer id, String groupName, List<Integer> members, Citizen assignedCitizen) {
        this.id = id;
        this.groupName = groupName;
        this.members = members;
        this.assignedCitizen = assignedCitizen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public Citizen getAssignedCitizen() {
        return assignedCitizen;
    }

    public void setAssignedCitizen(Citizen assignedCitizen) {
        this.assignedCitizen = assignedCitizen;
    }
}
