package vn.edu.iuh.fit.services;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import jakarta.ws.rs.PathParam;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.repositories.AccountRepositories;

@RequestScoped
public class AccountServices {
    @Inject
    private AccountRepositories repository;
    public List<Account> getAll(){
        return repository.getAll();
    }
    public Account getAccount(String name, String password){
        return repository.getAccount(name, password);
    }
    public GrantAccess getAccountRole(String username, String password) {
        return repository.getAccountRole(username, password);
    }
    public List<GrantAccess> getDsAccountRole() {
        return repository.getDsAccountRole();
    }
}