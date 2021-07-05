package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.LinksDAO;
import ua.goit.jdbc.dao.model.Company;
import ua.goit.jdbc.dao.model.Link;
import ua.goit.jdbc.dto.LinkDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LinkService implements Service<LinkDTO> {
    private ConnectionManager connectionManager;
    private LinksDAO linksDAO;
    private ViewMessages viewMessages = new ViewMessages();

    public LinkService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        linksDAO = new LinksDAO(this.connectionManager);
    }

    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String getAll(LinkDTO entity) {
        return viewMessages.joinListLinks(linksDAO.getAll(toLink(entity)));
    }

    @Override
    public String getById(int id) {
        return null;
    }

    @Override
    public void create(LinkDTO entity) {
        linksDAO.create(toLink(entity));
    }

    @Override
    public void delete(LinkDTO entity) {
        linksDAO.delete(toLink(entity));
    }

    @Override
    public void update(LinkDTO entity) {
        linksDAO.update(toLink(entity));
    }

    public Link toLink(LinkDTO linkDTO){
        return new Link(linkDTO.getTable(), linkDTO.getProject_id(), linkDTO.getCustomer_id(),
                linkDTO.getDeveloper_id(), linkDTO.getCompany_id(), linkDTO.getSkill_id());
    }

    public LinkedList<Link> toLink(ResultSet resultSet, String table){
        try{
            LinkedList<Link> links = new LinkedList<>();
            if (table.toLowerCase().equals("customer_companies")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(null);
                    link.setSkill_id(null);
                    link.setCompany_id(resultSet.getInt("company_id"));
                    link.setCustomer_id(resultSet.getInt("customer_id"));
                    link.setProject_id(resultSet.getInt("project_id"));
                    links.addLast(link);
                }
            } else if (table.toLowerCase().equals("project_developers")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(null);
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(resultSet.getInt("project_id"));
                    links.addLast(link);
                }
            } else if (table.toLowerCase().equals("developer_skills")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(resultSet.getInt("skill_id"));
                    link.setCompany_id(resultSet.getInt(null));
                    link.setCustomer_id(resultSet.getInt(null));
                    link.setProject_id(resultSet.getInt(null));
                    links.addLast(link);
                }
            }
            return links;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public LinkDTO fromLink(Link link){
        return new LinkDTO(link.getTable(), link.getProject_id(), link.getCustomer_id(),
                link.getDeveloper_id(), link.getCompany_id(), link.getSkill_id());
    }
}
