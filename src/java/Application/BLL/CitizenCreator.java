package Application.BLL;

import Application.BE.Citizen;
import Application.BE.CitizenContentBinding;
import Application.BE.ContentEntry;
import Application.DAL.CitizenContentLinkDAO;
import Application.DAL.CitizenDAO;
import Application.DAL.ContentDAO;

import java.util.List;
import java.util.stream.Collectors;

public class CitizenCreator
{
    ContentDAO contentDAO = new ContentDAO();
    CitizenDAO citizenDAO = new CitizenDAO();

    CitizenContentLinkDAO binder = new CitizenContentLinkDAO();


    public CitizenCreator()
    {

    }

    public Citizen create (Citizen citizen, List<ContentEntry> entries)
    {
        var qualified = citizenDAO.create(citizen);
        var content = contentDAO.create(entries);

        var binding = new CitizenContentBinding();

        binding.citizenID = qualified.getId();
        binding.contentIDs = content.stream().map(ContentEntry::getId).collect(Collectors.toList());

        binder.create(binding);

        qualified.setContent(content);

        return qualified;
    }

}
