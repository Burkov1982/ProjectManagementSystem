package ua.goit.jdbc.dao.model;

public class Link {
    private String table;
    private Integer project_id;
    private Integer customer_id;
    private Integer developer_id;
    private Integer company_id;
    private Integer skill_id;

    public Link() {
    }

    public Link(String table, Integer project_id, Integer customer_id, Integer developer_id, Integer company_id, Integer skill_id) {
        this.table = table;
        this.project_id = project_id;
        this.customer_id = customer_id;
        this.developer_id = developer_id;
        this.company_id = company_id;
        this.skill_id = skill_id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }

    @Override
    public String toString(){
        return String.format("Table: %s \n " +
                        "Project id: %s \n " +
                       "Customer id: %s \n " +
                      "Developer id: %s \n " +
                        "Company id: %s \n " +
                          "Skill id: %s \n", table, project_id, customer_id, developer_id, company_id, skill_id);
    }
}
