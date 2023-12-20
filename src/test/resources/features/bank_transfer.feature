Feature: Bank transfer

  Scenario: Transfer money to a savings account
    Given Amy has a savings account with $500
    And Amy has a current account with $200
    When she transfers $100 from her current account to her savings account
    Then her savings account should have $600
    And her current account should have $100

  Scenario: Transfers with insufficient funds should be rejected
    Given Amy has a current account with $50
    And Amy has a savings account with $1000
    When she tries to transfer $100 from her current account to her savings account
    Then the transfer should be rejected
    And her current account should have $50
    And her savings account should have $1000

