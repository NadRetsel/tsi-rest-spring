Feature: Testing user idk

  Scenario: User 1 is ROBERT
    Given userID is 1
    When I ask what user's first name is
    Then I should be told "ROBERT"


  Scenario: User 2 is BOB
    Given userID is 2
    When I ask what user's first name is
    Then I should be told "BOB"


  Scenario: client makes call to GET /user/1
    When the client calls /user/1
    Then the client receives status code of 200
    And the client receives server version 1.0