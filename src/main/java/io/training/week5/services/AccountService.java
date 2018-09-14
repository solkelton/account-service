package io.training.week5.services;

import io.training.week5.model.Account;
import io.training.week5.repo.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account retrieveAccount(long id) {
    return accountRepository.retrieveAccount(id);
  }

}
