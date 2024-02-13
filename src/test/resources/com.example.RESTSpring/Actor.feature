Feature: Actors

  Scenario: Creating an Actor
    Given first name is set to "BOB"
      And last name is set to "BUILDER"
    When I create a new Actor
    Then the Actor names should be filled
      And first name should be "BOB"
      And last name should be "BUILDER"



