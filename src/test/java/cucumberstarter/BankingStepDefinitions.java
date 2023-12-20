package cucumberstarter;

import com.serenitydojo.bankingtutorial.Account;
import com.serenitydojo.bankingtutorial.InsufficientFundsException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class BankingStepDefinitions {

    Map<String, Account> accounts = new HashMap<>();

    @Given("{word} has a {word} account with ${int}")
    public void has_a_account_with_$(String customerName, String accountType, Integer amount) {
        accounts.put(accountType, new Account(amount));
    }

    @When("she transfers ${int} from her {word} account to her {word} account")
    public void she_transfers_from(Integer amountToTransfer, String fromAccount, String toAccount) {
        Account from = accounts.get(fromAccount);
        Account to = accounts.get(toAccount);

        from.transferTo(to, amountToTransfer);
    }

    Throwable insufficientFundsException;

    @When("she tries to transfer ${int} from her {word} account to her {word} account")
    public void sheTriesToTransfer(Integer amountToTransfer, String fromAccount, String toAccount) {
        Account from = accounts.get(fromAccount);
        Account to = accounts.get(toAccount);

        insufficientFundsException = catchThrowable(() -> from.transferTo(to, amountToTransfer));
    }

    @Then("the transfer should be rejected")
    public void theTransferShouldBeRejected() {
        assertThat(insufficientFundsException).isNotNull()
                .isInstanceOf(InsufficientFundsException.class)
                .hasMessageContaining("Insufficient funds");
    }

    @Then("her {word} account should have ${int}")
    public void her_account_should_have_$(String accountType, Integer expectedAmount) {
        Account account = accounts.get(accountType);
        assertThat(account.getBalance()).isEqualTo(expectedAmount);
    }
}
