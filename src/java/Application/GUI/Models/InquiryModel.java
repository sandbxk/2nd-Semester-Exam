package Application.GUI.Models;

import Application.BE.Inquiry;
import Application.BLL.TeacherDataManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InquiryModel
{
    TeacherDataManager teacherDataManager = new TeacherDataManager();

    private IntegerProperty id;
    private StringProperty inquiryName;

    public InquiryModel(Inquiry inquiry)
    {
        this.id = new SimpleIntegerProperty();
        this.inquiryName = new SimpleStringProperty();

        this.id.set(inquiry.getId());
        this.inquiryName.setValue(inquiry.getInquiry());
    }

    public StringProperty getInquiryName()
    {
        return inquiryName;
    }

    public void setInquiryName(String inquiryName) {
        this.inquiryName.set(inquiryName);
    }
}
