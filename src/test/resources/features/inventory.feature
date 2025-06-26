Feature: Inventory

  @2_001
  Scenario: Filter by Name ASC
    Given the standard user is logged in and is on the inventory page
    Then the filter should be preselected with alphabetical order "Name (A to Z)"
    And the items should be sorted in ascending alphabetical order


  @2_002
  Scenario: Filter by Name des
    Given the standard user is logged in and is on the inventory page
    When the user selects "Name (Z to A)" in the filter
    Then the items should be sorted in descending alphabetical order
