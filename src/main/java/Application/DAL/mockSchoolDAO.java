package Application.DAL;

public class mockSchoolDAO {

    public String getSchoolName()
    {
        return "easv";
    }

    public int getSchoolzip()
    {
        return 6715;
    }

    public String getSchoolCity()
    {
        return "Esbjerg";
    }

    public void createSchool(String name, String zipCode)
    {
        System.out.println("school name: "+name + "\n postal code: "+ zipCode);
    }
}
