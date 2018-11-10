Feature: Automated the facebook page 
	Description: The purpose of this feature is to put into practice what was learned during the taeweek2018

Scenario: Post an entry on the facebook wall, related to the event 
	Given The user is on login page 
	When The user logs in using the following credentials, and get into their profile 
		| email             | pass |
		| test@hotmail.com  | test |
	Then Post an entry in the wall of your profile 
