package io.training.week5.services;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.repo.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account retrieveAccount(long id) {
    return accountRepository.getAccountById(id);
  }

  public Account addAccount(Account account) {
    if(validateAccount(account)) {
      return accountRepository.save(account);
    }
    return new Account();
  }

  public Account updateAccount(long id, Account updatedAccount) {
    Account originalAccount = retrieveAccount(id);
    if(validateAccount(originalAccount)) {
      Account newAccount = update(originalAccount, updatedAccount);
      return accountRepository.save(newAccount);
    }
    return new Account();
  }

  public boolean removeAccount(long id) {
    Account account = accountRepository.getAccountById(id);
    if(validateAccount(account)) {
      accountRepository.deleteAccountById(id);
      return true;
    }
    return false;
  }

  public boolean validateAccount(Account account) {
    if(account == null) return false;
    if(account.getFirstName() == null) return false;
    if(account.getLastName() == null) return false;
    if(account.getEmailAddress() == null) return false;
    return true;
  }

  private Account update(Account original, Account updated) {
    Account newAccount = new Account();
    newAccount.setId(original.getId());

    if(updated == null) return original;

    if(updated.getFirstName() == null) newAccount.setFirstName(original.getFirstName());
    else newAccount.setFirstName(updated.getFirstName());

    if(updated.getLastName() == null) newAccount.setLastName(original.getLastName());
    else newAccount.setLastName(updated.getLastName());

    if(updated.getEmailAddress() == null) newAccount.setEmailAddress(original.getEmailAddress());
    else newAccount.setEmailAddress(updated.getEmailAddress());

    return newAccount;
  }
}
