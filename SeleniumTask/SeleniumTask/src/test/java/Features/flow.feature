Feature: Test buying without purchase flow
  Scenario: Test buying without purchase flow
    Given User logged to the website
    When user select products to cart
    And user add product
    Then open cart to check order