package Application.BLL;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BE.Group;
import Application.DAL.CitizenDAO;
import Application.DAL.GroupDAO;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.SessionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class StudentDataManager {

    public StudentDataManager() {

    }

    public List<Group> getAssignedGroups(int studentID)
    {
        GroupDAO dao = new GroupDAO();

        return dao.readAll().stream().filter(group -> group.getMembers().contains(studentID)).toList();
    }


    // update assigned citizen
        // add observation
            // journal entry!!! CRUD

    public List<Citizen> getAssignedCitizens(int studentID)
    {
        var groups = getAssignedGroups(studentID);

        CitizenDAO dao = new CitizenDAO();

        List<Citizen> citizens = new ArrayList<>();

        for (var group : groups)
        {
            citizens.add(dao.read(group.getAssignedCitizen().getId()));
        }

        return citizens;
    }

    public static void main(String[] args) {
        StudentDataManager manager = new StudentDataManager();

        manager.getAssignedCitizens(1);

    }
}
