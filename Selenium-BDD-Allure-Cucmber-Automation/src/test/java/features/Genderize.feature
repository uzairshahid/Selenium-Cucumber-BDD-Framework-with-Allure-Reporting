@check_gender
Feature: Search functionality

  As a user
  I want to be able to search for items or names
  So that I can see relevant results

  Background:
    Given I open the website

  Scenario Outline: Valid search returns related results
    When I enter "<searchTerm>" into the search box
    Then I click the search button
    Then I should see results related to Entered Name

    Examples:
      | searchTerm |
      | Uzair   |
      | Shahid  |
      | John    |
      | Smith   |
