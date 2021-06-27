package ua.goit.jdbc.dto;

public class SkillDTO {
    private Integer skill_id;
    private String branch;
    private String stage;

    public SkillDTO(Integer skill_id, String branch, String stage) {
        this.skill_id = skill_id;
        this.branch = branch;
        this.stage = stage;
    }

    public SkillDTO() {
    }

    public Integer getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор умения: %s \n" +
                "Специализация: %s \n" +
                "Уровень умений: %s \n", skill_id, branch, stage);
    }
}
