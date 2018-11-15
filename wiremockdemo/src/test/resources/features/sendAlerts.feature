Feature: Send alerts

Scenario: Successful send alert
Given that the alert server is on
And a system is authenticated
When a system sends an event
Then alert server should receive a post request

