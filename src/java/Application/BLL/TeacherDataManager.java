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