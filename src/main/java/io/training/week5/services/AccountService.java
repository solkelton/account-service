package io.training.week5.services;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.repo.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account retrieveAccount(long id) {
    Account account = accountRepository.getAccountById(id);
    if(validateAccount(account)) {
      logger.info("Valid Account Found at retrieveAccount");
      return account;
    }
    logger.debug("Invalid Account Id at retrieveAccount");
    logger.debug("Account Id: {}", id);
    return new Account();
  }

  public Account addAccount(Account account) {
    if(validateAccount(account)) {
      logger.info("Valid Account Added");
      return accountRepository.save(account);
    }
    logger.debug("Invalid Account Attempted to Add");
    logger.debug("Account: {}", account.toString());
    return new Account();
  }

  public Account updateAccount(long id, Account updatedAccount) {
    Account originalAccount = retrieveAccount(id);
    if(validateAccount(originalAccount)) {
      logger.info("Valid Account Updated");
      Account newAccount = update(originalAccount, updatedAccount);
      return accountRepository.save(newAccount);
    }
    logger.debug("Invalid Account Attempted to Update at Id {}", id);
    logger.debug("Account: {}", updatedAccount.toString());
    return new Account();
  }

  public boolean removeAccount(long id) {
    Account account = accountRepository.getAccountById(id);
    if(validateAccount(account)) {
      logger.info("Valid Account Removed");
      accountRepository.deleteAccountById(id);
      return true;
    }
    logger.debug("Invalid Account Attempted to remove at Id {}", id);
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
