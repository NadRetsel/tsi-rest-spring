Feature: Actors

  Scenario Outline: Creating an Actor
    Given first name is set to <firstName>
      And last name is set to <lastName>
    When I create a new Actor
    Then the Actor names should be filled
      And first name should be <expectedFirstName>
      And last name should be <expectedLastName>

  Examples:
    | firstName | lastName  | expectedFirstName | expectedLastName |
    | "BOB"     | "BUILDER" | "BOB"             | "BUILDER"        |
    | "hello"   | "world"   | "HELLO"           | "WORLD"          |


  Scenario Outline: Updating an Actor's name with an ActorDTO
    Given an Actor called "BOB" "BUILDER"
    When I update Actor's name to <firstName> <lastName>
    Then the Actor names should be filled
      And first name should be <expectedFirstName>
      And last name should be <expectedLastName>

    Examples:
      | firstName   | lastName      | expectedFirstName | expectedLastName |
      | "SPONGEBOB" | "SQUAREPANTS" | "SPONGEBOB"       | "SQUAREPANTS"    |
      | "hello"     | "world"       | "HELLO"           | "WORLD"          |


  Scenario: Adding Films to Actor
    Given the following Films exist:
      | filmId | actorIds |
      | 1      | 1,2,3    |
      | 2      | 2,3,4    |
      | 3      | 3,4,5    |
      And the following Actors exist:
        | actorId | filmIds |
        | 1      | 1        |
        | 2      | 1,2      |
        | 3      | 1,2,3    |
        | 4      | 2,3      |
        | 5      | 3        |
    When I add Film 2 to Actor 1
    Then Actor 1 should be in Film 1
      And Actor 1 should be in Film 2
      But Actor 1 should not be in Film 3







