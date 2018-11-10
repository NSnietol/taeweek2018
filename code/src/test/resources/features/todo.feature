Feature: Todo List

  Scenario Outline: Create new tasks
   Given the user is in the application
   When the user create new "<task>"
   Then validate if task "<task>", was created.
  
   Examples: 
     | task             |
     | test demo task 1 |
     | test demo task 2 |

  Scenario Outline: Delete completed tasks
    Given the user is in the application
    When the user delete "<task>" completed
    Then validate if task "<task>", was deleted.

    Examples: 
      | task             |
      | test demo task 1 |
