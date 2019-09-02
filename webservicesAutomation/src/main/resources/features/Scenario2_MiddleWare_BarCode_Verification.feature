Feature: To verify MiddleWare Scanning BarCodes

  Scenario Outline: Verify MiddleWare Scanning EAN-13 BarCodes
    Given User Access import.CurrentBarcode DB view to fetch item id for <BarCode> EAN-13 BarCode
     
    Then user access export.DemandWareProductCurrent DB table to fetch Dataelements for EAN-13 Products BarCodes
    
    Given user access ScanBarcode JSON to fetch API response values for EAN-13 BarCodes
    
    Then verify the ScanBarCode JSON API response values for EAN 13 BarCodes
    
    Examples:
    |  BarCode    |
    |9312254110215|
    |0884420396079|
    |0884420396031|
    
  #Scenario: Verify MiddleWare Scanning Shelf Edge Label BarCodes
    #Given User Access import.CurrentBarcode DB view to fetch item id for Shelf Edge Label BarCode
    #Then user access export.DemandWareProductCurrent DB table to fetch Dataelements for Shelf Edge Label BarCode
    #Given user access ScanBarcode JSON to fetch API response values for Shelf Edge Label BarCodes
    #Then verify the ScanBarCode JSON API response values for Shelf Edge Label BarCodes

 # Scenario: Verify MiddleWare Scanning Display Products BarCodes
    #Given User Access import.CurrentBarcode DB view to fetch item id for Display Model BarCode
    #Then user access export.DemandWareProductCurrent DB table to fetch Dataelements for Display Models BarCodes
    #Given user access ScanBarcode JSON to fetch API response values for Display Models BarCodes
    #Then verify the ScanBarCode JSON API response values for Display Models BarCodes

  #Scenario: Verify MiddleWare Scanning Retruned Products BarCodes
    #Given User Access import.CurrentBarcode DB view to fetch item id for Returned Products BarCode
    #Then user access export.DemandWareProductCurrent DB table to fetch Dataelements for Retruned Products BarCodes
    #Given user access ScanBarcode JSON to fetch API response values for Retruned BarCodes
    #Then verify the ScanBarCode JSON API response values for Returned Products BarCodes
