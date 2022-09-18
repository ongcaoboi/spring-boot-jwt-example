package yurinori.cs.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yurinori.cs.demo.common.AccountLogin;
import yurinori.cs.demo.model.Account;
import yurinori.cs.demo.repository.AccountRepository;
import yurinori.cs.demo.services.AccountService;
import yurinori.cs.demo.services.JwtService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	private AccountService accountService = new AccountService();

	private JwtService jwtService = new JwtService();

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(required = false) String name) {
		try {
			List<Account> accounts = new ArrayList<Account>();

			if (name == null) {
				accountRepository.findAll().forEach(accounts::add);
			} else {
				accountRepository.findByUserNameContaining(name).forEach(accounts::add);
			}

			if (accounts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(accounts, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AccountLogin accountLogin) {
		Account account = null;
		for(Account t: accountRepository.findAll()) {
			if (t.getUserName().equals(accountLogin.getUserName()) && t.getPassword().equals(accountLogin.getPassword())) {
				account = t;
			}
		}
		
		System.out.println(account);
//		Account account = accountService.getAccountByUserNameAndPassword(accountLogin.getUserName(), accountLogin.getPassword());

		if (account != null) {
			String token = jwtService.generateTokenFromUserName(account.getUserName());

			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("UserName or Password not found!", HttpStatus.BAD_REQUEST);
		}

	}

}
