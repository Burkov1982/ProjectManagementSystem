package ua.goit.jdbc.dto;

public class CompanyDTO {
    private Integer company_id;
    private String company_name;
    private String headquarters;

    public CompanyDTO() {
    }

    public CompanyDTO(Integer company_id, String company_name, String headquarters) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
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
    public String toString(){
        return "Company id: " + String.valueOf(company_id)
                + "Company name: " + company_name
                + "Company headquarters:" + headquarters;
    }
}
