package yurinori.cs.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import yurinori.cs.demo.model.Account;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Integer> {
	List<Account> findByUserNameContaining(String userName);
	
	List<Account> findByUserNameAndPassword(String userName, String password);
}
