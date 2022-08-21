# WEB_UI_AUTOMATION_SELENIUM_WEBDRIVER

#**Steps to run the test suite:**
1. Clone the repo
2. Go to directory "Web_UI_Automation_Selenium_POM"
3. Run mvn clean test

Note: You can use feature file tags (e.g. @Login) in mvn command to run specific testcases.

#**HTML Report:**

Once the test run completes, you can generate HTML Report by using executing below command on the root directory:

mvn cluecumber-report:reporting


Report will be generated at below location.
1. Navigate to folder: <Root Folder>/target/generated-report
2. Open index.html file in web browser.