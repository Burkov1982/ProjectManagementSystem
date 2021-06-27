package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.SkillDAO;
import ua.goit.jdbc.dao.model.Skill;
import ua.goit.jdbc.dto.SkillDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.util.LinkedList;

public class SkillService implements Service<SkillDTO>{
    private ViewMessages viewMessages = new ViewMessages();
    private ConnectionManager connectionManager;
    private SkillDAO skillDAO;

    public SkillService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        skillDAO = new SkillDAO(connectionManager);
    }

    @Override
    public String getAll(){
        return viewMessages.joinListSkills(skillDAO.getAll());
    }

    @Override
    public void create(SkillDTO skillDTO){
        Skill skill = Converter.toSkill(skillDTO);
        skillDAO.create(skill);
    }

    @Override
    public void delete(int id){
        skillDAO.delete(id);
    }

    @Override
    public String getById(int id){
        return skillDAO.findById(id).toString();
    }

    @Override
    public void update(SkillDTO skillDTO){
        Skill skill = Converter.toSkill(skillDTO);
        skillDAO.update(skill);
    }

    public LinkedList<Skill> getAllInList(){return skillDAO.getAll();}
}
