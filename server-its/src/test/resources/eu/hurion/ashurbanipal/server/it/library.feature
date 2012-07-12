Feature: Library

  Scenario: Add a book to an empty library
    Given I have an empty library
    When I add a book with title "Children of Dune"
    Then The book should be in the library

  Scenario: Add a book to an existing library
    Given A library with these titles:
      | title                                  | series       |
      | Neuromancer                            |              |
      | Necronomicon                           |              |
      | Dune                                   |              |
      | Harry Potter and the Philosopher Stone | Harry Potter |
    When I add a book with title "Children of Dune"
    Then The book should be in the library

  Scenario: Add a book belonging to a series
    Given I have an empty library
    When I add a book with title "Harry Potter and the Philosopher Stone" in series "Harry Potter"
    Then The book should be in the library
    And  The series should be in the followed series
