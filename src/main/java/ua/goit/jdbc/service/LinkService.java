package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.LinksDAO;
import ua.goit.jdbc.dao.model.Link;
import ua.goit.jdbc.dto.LinkDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LinkService implements Service<LinkDTO> {
    private LinksDAO linksDAO;
    private final ViewMessages viewMessages = new ViewMessages();

    public LinkService() {
    }

    public LinkService(ConnectionManager cm) {
        linksDAO = new LinksDAO(cm);
    }

    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String getAll(LinkDTO entity) throws SQLException {
        Link link = toLink(entity);
        ResultSet resultSet = linksDAO.getAll(link);
        LinkedList<Link> links = toLink(resultSet, entity.getTable());
        return viewMessages.joinListLinks(links);
    }

    @Override
    public String getById(int id) {
        return null;
    }

    @Override
    public void create(LinkDTO entity) throws SQLException {
        linksDAO.create(toLink(entity));
    }

    @Override
    public void delete(LinkDTO entity) throws SQLException {
        linksDAO.delete(toLink(entity));
    }

    public void update(LinkDTO linkDTO, LinkDTO oldLinkDTO) throws SQLException {
        Link newLink = toLink(linkDTO);
        Link oldLind = toLink(oldLinkDTO);
        linksDAO.update(newLink, oldLind);
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
            if (table.equalsIgnoreCase("customers_companies")){
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
            }
            if (table.equalsIgnoreCase("project_developers")){
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
            }
            if (table.equalsIgnoreCase("developer_skills")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(resultSet.getInt("skill_id"));
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(null);
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
