package vn.edu.iuh.fit.services;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.repositories.AccountRepositories;

import java.util.List;

@RequestScoped
public class AccountServices {
    @Inject
    private AccountRepositories repository;
    public AccountServices(){
        repository = new AccountRepositories();
    }

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
    public Account accountLogin(String name, String pass){
        return repository.accountLogin(name,pass);
    }
    public boolean accountLogout(String userName) {
        return true;
    }
}
