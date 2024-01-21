Feature: Test Background Feature
Description: The purpose of this feature is to test the Background keyword

Background:
    Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com/"
#https://example.com
   @ValidCredentials
   Scenario: Login with valid credentials

    When User enters username as "Admin" and password as "admin123"
    Then User should be able to login successfully and new page open

   @InvalidCredentials
   Scenario Outline: Login with invalid credentials
      
    When User enters username as "<username>" and password as "<password>"
    Then User should be able to see error message "<errorMessage>"

 Examples:
   | username   | password  | errorMessage                      |
   | Admin      | admin12$$ | Invalid credentials               |
   | admin$$    | admin123  | Invalid credentials               |
   | abc123     | xyz$$     | Invalid credentials               |


#   @MissingUsername
#   Scenario Outline: Login with blank username

#    When User enters username as " " and password as "admin123"
#    Then User should be able to see a message "Required" below Username

#   @FaceBookLink @FailedTest
#   Scenario: Verify FaceBook Icon on Login Page

#    Then User should be able to see FaceBook Icon

#   @LinkedInLink
#  Scenario: Verify LinkedIn Icon on Login Page

#    Then User should be able to see LinkedIn Icon

#  @ForgetPasswordLink @HarryTest
#   Scenario: Verify ForgetPassword link on Login Page

#    When User clicks on Forgot your Password Link
#    Then User should navigate to a new page


