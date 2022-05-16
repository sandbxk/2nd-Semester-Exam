package Application.BLL;

import Application.BE.Case;
import Application.BE.CategoryEntry;
import Application.BE.CitizenTemplate;
import Application.BE.Inquiry;
import Application.DAL.*;

import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO inquiryDAO;
    TemplatePatternDAO caseDAO;
    TemplatePatternDAO healthCategoryDAO;
    TemplatePatternDAO functionalAbilityDAO;
    TemplatePatternDAO citizenDAO;
    TemplatePatternDAO citizenTemplateDAO;
    TemplatePatternDAO generalInfoDAO;

    public TeacherDataManager()
    {
        inquiryDAO = new InquiryDAO();
        caseDAO = new CasesDAO();
        citizenTemplateDAO = new CitizenTemplateDAO();
        generalInfoDAO = new GeneralInfoCitizenTemplateDAO();
    }

    public Case createCase(int id, String inquiryReason, String medicalDiagnose, Inquiry inquiry)
    {
        return (Case) caseDAO.create(new Case(id, inquiryReason, medicalDiagnose, inquiry));
    }

    public List getAllInquiries()
    {
        return inquiryDAO.readAll();
    }

    public List getAllCitizenTemplates() {
        List<CitizenTemplate> citizenTemplates = citizenTemplateDAO.readAll();


        return null;
    }

    public CitizenTemplate newCitizenTemplate() {
        //Template
        CitizenTemplate newTemplate = (CitizenTemplate) citizenTemplateDAO.create(new CitizenTemplate());

        //General Info


        //Set categories

        return newTemplate;
    }

    public void deleteCitizenTemplate(CitizenTemplate template) {
    }

    public void copyCitizenTemplate(CitizenTemplate template) {
    }

    public void updateCitizenTemplate(CitizenTemplate template, List<CategoryEntry> beHealthConditions, List<CategoryEntry> beFunctionalAbilities) {
    }
}