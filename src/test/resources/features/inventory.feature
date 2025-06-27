Feature: Inventory

  @2_001
  Scenario: Filter by Name ASC
    Given the standard user is logged in and is on the inventory page
    Then the filter should be preselected with alphabetical order "Name (A to Z)"
    And the items should be sorted in ascending alphabetical order


  @2_002
  Scenario: Filter by Name DESC
    Given the standard user is logged in and is on the inventory page
    When the user selects "Name (Z to A)" in the filter
    Then the items should be sorted in descending alphabetical order


  @2_003
  Scenario: Filter by Price ASC
    Given the standard user is logged in and is on the inventory page
    When the user selects "Price (low to high)" in the filter
    Then the items should be sorted in ascending price order


  @2_004
  Scenario: Filter by Price DESC
    Given the standard user is logged in and is on the inventory page
    When the user selects "Price (high to low)" in the filter
    Then the items should be sorted in descending price order

  # Expected : FAIL
  @2_005
  Scenario: Verify product title is displayed
    Given the standard user is logged in and is on the inventory page
    Then all items should have a title
    And every title starts with "Sauce Labs"


  @2_006
  Scenario: Verify product description is displayed
    Given the standard user is logged in and is on the inventory page
    Then all items should have a description


  @2_007
  Scenario: Verify product image is displayed
    Given the standard user is logged in and is on the inventory page
    Then all items should have an image
    And the image is not broken


