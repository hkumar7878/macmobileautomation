Feature: To fetch and verify product categories

  Scenario: Verify product categories
    Given user access DB and fetch category data
    |SELECT * FROM [import].[EP_Category] where ParentCategoryPath is null and Name not in ('Clearance Centre','Gift Card')|
    Given user access Category API and fetch category data
    |Ocp-Apim-Subscription-Key|c3fe54cc0f0c437b981d4859f62d99e3|https://twg.azure-api.net/twlYourWarehouseTest/Category.json?CategoryId=root&IncludeNonBrowse=N|
    
    Given user launches the app
    Then user clicks on the Products tab
    Then verify Products tab is displayed
    Then all categories on Product tab is fetched
    Then verify the parent categories on the app with the response
