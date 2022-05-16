package Application.BLL;

import Application.BE.Case;
import Application.BE.CategoryEntry;
import Application.BE.CitizenTemplate;
import Application.BE.Inquiry;
import Application.DAL.CasesDAO;
import Application.DAL.InquiryDAO;
import Application.DAL.TemplatePatternDAO;

import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO inquiryDAO;
    TemplatePatternDAO caseDAO;
    TemplatePatternDAO healthCategoryDAO;
    TemplatePatternDAO functionalAbilityDAO;

    public TeacherDataManager()
    {
        inquiryDAO = new InquiryDAO();
        caseDAO = new CasesDAO();

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
        return null;
    }

    public void newCitizenTemplate(CitizenTemplate template) {
        //set categories

    }

    public void deleteCitizenTemplate(CitizenTemplate template) {
    }

    public void copyCitizenTemplate(CitizenTemplate template) {
    }

    public void updateCitizenTemplate(CitizenTemplate template, List<CategoryEntry> beHealthConditions, List<CategoryEntry> beFunctionalAbilities) {
    }
}