package Application.BLL;

import Application.BE.*;
import Application.DAL.*;

import java.util.ArrayList;
import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO inquiryDAO;
    TemplatePatternDAO caseDAO;
    TemplatePatternDAO healthCategoryDAO;
    TemplatePatternDAO functionalAbilityDAO;
    TemplatePatternDAO citizenDAO;
    TemplatePatternDAO citizenTemplateDAO;
    TemplatePatternDAO categoryDAO;
    TemplatePatternDAO generalInfoDAO;

    public TeacherDataManager()
    {
        citizenDAO = new CitizenDAO();
        categoryDAO = new CategoryDAO();
    }






    // Create/Read/Update/Delete - student (AccountDAO)
        // single / all

    // Create/Read/Update/Delete - citizen (template) (CitizenDAO)
        // single / all

    // copy citizen (clone template - new ID)

    // Create/Read/Update/Delete - group (GroupDAO)
        // [delete/archive citizen]
        // single / all

    // get student(s) in a group (members)

    // get all groups that contains a specific student

    public void assignToGroup(Citizen citizen, Group group)
    {
    // assign citizen to group (GroupDAO)
        // create clone of template (CitizenDAO)
    }

    public void assignToGroup(Account student, Group group)
    {
       // assign student to group (GroupDAO)
    }

    public void unassignFromGroup(Account student, Group group)
    {
       // unassign student to group (GroupDAO)
    }


    // utility:
        // generate random names / data

    












    public List getAllInquiries()
    {
        return inquiryDAO.readAll();
    }


    public void updateCitizenEntity(Citizen citizen) {
        citizenDAO.update(citizen);
    }

    public void deleteCitizenEntity(Citizen citizen) {
        citizenDAO.delete(citizen.getId());
    }

    public List getAllCitizens() {
        return citizenDAO.readAll();
    }




}