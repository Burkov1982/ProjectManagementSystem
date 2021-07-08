package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.SkillDAO;
import ua.goit.jdbc.dao.model.Project;
import ua.goit.jdbc.dao.model.Skill;
import ua.goit.jdbc.dto.ProjectDTO;
import ua.goit.jdbc.dto.SkillDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SkillService implements Service<SkillDTO>{
    private final ViewMessages viewMessages = new ViewMessages();
    private final SkillDAO skillDAO;

    public SkillService(ConnectionManager connectionManager) {
        skillDAO = new SkillDAO(connectionManager);
    }

    @Override
    public String getAll(){
        return viewMessages.joinListSkills(skillDAO.getAll());
    }

    @Override
    public String getAll(SkillDTO entity) {
        return null;
    }

    @Override
    public void create(SkillDTO skillDTO) throws SQLException {
        Skill skill = toSkill(skillDTO);
        skillDAO.create(skill);
    }

    @Override
    public void delete(SkillDTO skillDTO) throws SQLException {
        skillDAO.delete(toSkill(skillDTO));
    }

    @Override
    public String getById(int id) throws SQLException {
        return skillDAO.findById(id).toString();
    }

    @Override
    public void update(SkillDTO skillDTO) throws SQLException {
        Skill skill = toSkill(skillDTO);
        skillDAO.update(skill);
    }

    public LinkedList<Skill> getAllInList(){return skillDAO.getAll();}

    public static Skill toSkill(SkillDTO skillDTO){
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage());
    }

    public static SkillDTO fromSkill (Skill skill){
        return new SkillDTO(skill.getSkill_id(), skill.getBranch(), skill.getStage());
    }

    public static LinkedList<Skill> toSkill (ResultSet resultSet) {
        try{
            LinkedList<Skill> skills  = new LinkedList<>();
            while (resultSet.next()){
                Skill skill = new Skill();
                skill.setSkill_id(resultSet.getInt("skill_id"));
                skill.setBranch(resultSet.getString("branch"));
                skill.setStage(resultSet.getString("stage"));
                skills.addLast(skill);
            }
            return skills;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

}
