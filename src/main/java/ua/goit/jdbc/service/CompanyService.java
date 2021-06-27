package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.CompanyDAO;
import ua.goit.jdbc.dao.model.Company;
import ua.goit.jdbc.dto.CompanyDTO;
import ua.goit.jdbc.view.ViewMessages;

public class CompanyService implements Service<CompanyDTO> {
    private ConnectionManager connectionManager;
    private CompanyDAO companyDAO;
    private ViewMessages viewMessages = new ViewMessages();

    public CompanyService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        companyDAO = new CompanyDAO(this.connectionManager);
    }

    @Override
    public void create(CompanyDTO companyDTO){
        Company company = Converter.toCompany(companyDTO);
        companyDAO.create(company);
    }

    @Override
    public void delete(int id){
        companyDAO.delete(id);
    }

    @Override
    public void update(CompanyDTO companyDTO){
        Company company = Converter.toCompany(companyDTO);
        companyDAO.update(company);
    }

    @Override
    public String getById(int id){
        return companyDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListCompanies(companyDAO.getAll());
    }
}
