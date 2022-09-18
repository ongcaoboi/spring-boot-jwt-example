package yurinori.cs.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yurinori.cs.demo.model.Account;
import yurinori.cs.demo.repository.AccountRepository;

public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	public Account getAccountByUserName(String userName) {
		List<Account> accounts = new ArrayList<>();
		accountRepository.findByUserNameContaining(userName).forEach(accounts::add);
		if (accounts.size() == 1) {
			return accounts.get(0);
		} else {
			return null;
		}
	}

	public Account getAccountByUserNameAndPassword(String userName, String password) {
		for(Account account: accountRepository.findAll()) {
			if (account.getUserName().equals(userName) && account.getPassword().equals(password)) {
				return account;
			}
		}
		return null;
	}
}
