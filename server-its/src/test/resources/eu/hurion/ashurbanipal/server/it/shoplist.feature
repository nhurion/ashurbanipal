Feature: Shopping list
  provide a shopping list with the missing books of the followed series.

  Scenario: Missing books in a followed series
    Given I follow the series "Harry Potter"
    And the series "Harry Potter" contains
      | Harry Potter and the Philosopher Stone    |
      | Harry Potter and the Chamber of Secrets   |
      | Harry Potter and the Prisoner of Azkaban  |
      | Harry Potter and the Goblet of Fire       |
      | Harry Potter and the Order of the Phoenix |
      | Harry Potter and the Half-blood Prince    |
      | Harry Potter and the Deathly Hallows      |
    And A library with these titles:
      | title                                  | series       |
      | Harry Potter and the Philosopher Stone | Harry Potter |
      | Harry Potter and the Order of the Phoenix | Harry Potter |
    Then The shopping list should contain
      | title                                     | series       |
      | Harry Potter and the Chamber of Secrets   | Harry Potter |
      | Harry Potter and the Prisoner of Azkaban  | Harry Potter |
      | Harry Potter and the Goblet of Fire       | Harry Potter |
      | Harry Potter and the Half-blood Prince    | Harry Potter |
      | Harry Potter and the Deathly Hallows      | Harry Potter |
