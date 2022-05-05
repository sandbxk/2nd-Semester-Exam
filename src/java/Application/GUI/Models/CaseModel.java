package Application.GUI.Models;

import Application.BLL.TeacherDataManager;
import Application.DAL.InquiryDAO;
import Application.DAL.TemplatePatternDAO;

import java.util.List;

public class CaseModel {

    TeacherDataManager teacherBLL;

    public CaseModel()
    {
        teacherBLL = new TeacherDataManager();
    }

    public List getAllInquries()
    {
        return teacherBLL.getAllInquries();
    }
}
