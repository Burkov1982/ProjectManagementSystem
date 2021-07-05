package ua.goit.jdbc.dao.model;

public class Company{
    private Integer company_id;
    private String company_name;
    private String headquarters;

    public Company() {
    }

    public Company(Integer company_id, String company_name, String headquarters) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public Company setCompany_id(Integer company_id) {
        this.company_id = company_id;
        return this;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    @Override
    public String toString() {
        return  String.format("Идентификатор компании: %s \n" +
                              "Название компании: %s \n" +
                              "Главный офис  компании: %s \n", company_id, company_name, headquarters);
    }
}
