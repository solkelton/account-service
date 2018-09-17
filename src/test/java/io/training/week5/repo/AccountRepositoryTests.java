package io.training.week5.repo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.training.week5.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {

  @Autowired private TestEntityManager entityManager;
  @Autowired private AccountRepository accountRepository;

  @Test
  public void testRetrieveAccount_validInput_shouldReturnAccountEntry() throws Exception {
//    Account account = new Account("Nick", "Kelton", "nick@gmail.com");
//    account.setId(1);
//    entityManager.persist(account);
//    entityManager.flush();
//
//    Account result = accountRepository.findById(1);
//    assertThat(result.getFirstName()).isEqualTo("Nick");
//    assertThat(result.getLastName()).isEqualTo("Kelton");
  }

}
