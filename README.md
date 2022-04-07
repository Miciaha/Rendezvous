# InventoryManagementApp
Inventory management application created using JavaFX.
- [x] Create Project README
- [x] Add Project Requirements to README
- [ ] Include screenshots in README

## Part 1. User Interface

### A. Create a JavaFX application based on the Software 1 GUI mock-up.

|    | Task  | Notes |
|----|-------|-------|
|:heavy_check_mark:| Create main form | |
|:heavy_check_mark:| Create add part form | |
|:heavy_check_mark:| Create modify part form | |
|:heavy_check_mark:| Create add product form | |
|:heavy_check_mark:| Create modify product form | |

### B. Provide Javadoc comments for each class member throughout the code. Include errors encountered and ehancements to be added

## Part 2. Application

### C. Create classes with data and logic that map to the UML class diagram provided and include the supplied Part class.

#### UML Classes
|    | Task  | Notes |
|----|-------|-------|
|:heavy_check_mark:| Include Part.java | |
|:heavy_check_mark:| Create Inventory class based on UML | |
|:heavy_check_mark:| Create Part class based on UML | |
|:heavy_check_mark:| Create Product class based on UML | |
|:heavy_check_mark:| Create InHouse class based on UML | |
|:heavy_check_mark:| Create Outsourced class based on UML | |

#### Demonstrate Concepts
|    | Concept  | Notes |
|----|-------|-------|
|:heavy_check_mark:| Inheritance | |
|:heavy_check_mark:| Abstract classes | |
|:heavy_check_mark:| Concrete classes | |
|:heavy_check_mark:| Instance variables | |
|:heavy_check_mark:| Static variables | |
|| Instance methods | |
|:heavy_check_mark:| Static methods | |

### D. Add functionality to the main form

#### Parts Pane
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Add button opens the Add Part form | |
|:heavy_check_mark:| Modify button opens the Modify Part form | |
|:heavy_check_mark:| Delete button deletes the selected part from the PartsTableView | |
|:heavy_check_mark:| Delete button creates a descriptive error message if a part is not deleted | |
|:heavy_check_mark:| Search field above the Parts TableView allows user to search by ID or name | |
|:heavy_check_mark:| Search field creates a descriptive error message if a part is not found | |
|:heavy_check_mark:| If search field is empty, display all parts | |

#### Products Pane
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Add button opens the Add Product form | |
|:heavy_check_mark:| Modify button opens the Modify Product form | |
|:heavy_check_mark:| Delete button deletes the selected product from the ProductsTableView | |
|:heavy_check_mark:| Delete button creates a descriptive error message if a product is not deleted | |
|:heavy_check_mark:| Search field above the Products TableView allows user to search by ID or name | |
|:heavy_check_mark:| Search field creates a descriptive error message if a product is not found | |
|:heavy_check_mark:| If search field is empty, display all products | |

#### Exit Button
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Exit button closes the application | |

### E. Add Functionality to Parts Forms

#### Add Part Form
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| In-House and Outsourced radio buttons switch the MachineID/CompanyName label to the correct value | |
|:heavy_check_mark:| Application auto-generates a unique part ID ||
|:heavy_check_mark:| Part ID text field must be disabled ||
|:heavy_check_mark:| User should be able to enter data into active text fields ||
|:heavy_check_mark:| After saving, users are redirected to the Main form ||
|:heavy_check_mark:| Canceling or exiting form redirects users to the Main form ||

#### Modify Part Form
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Text fields populate with the data from the chosen part | |
|:heavy_check_mark:| In-House and Outsourced radio buttons switch the label to the correct value. ||
|:heavy_check_mark:| Part ID is retained after the save button is clicked and a new object is created ||
|:heavy_check_mark:| User can modify data values in the text fields except for part ID ||
|:heavy_check_mark:| After saving, users are redirected to the Main form ||
|:heavy_check_mark:| Canceling or exiting form redirects users to the Main form ||


### F. Add Functionality to Products Forms

#### Add Product Form
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Application auto-generates a unique product ID ||
|:heavy_check_mark:| Product ID text field must be disabled ||
|:heavy_check_mark:| User should be able to enter data into active text fields ||
|:heavy_check_mark:| User can search for parts by ID or name in the top table ||
|:heavy_check_mark:| Error message is displayed if the part or parts are not found ||
|:heavy_check_mark:| If search field is empty, display all parts ||
|:heavy_check_mark:| Top table is identical to Parts TableView in the Main form ||
|:heavy_check_mark:| User can add parts to bottom table by selecting parts from top table and clicking add ||
|:heavy_check_mark:| Remove Associated Part button removes selected part from bottom table ||
|:heavy_check_mark:| After saving, users are redirected to the Main form ||
|:heavy_check_mark:| Canceling or exiting form redirects users to the Main form ||


#### Modify Product Form
#### Add Product Form
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Text fields and TableView populate with the data from the chosen product ||
|:heavy_check_mark:| User can search for parts by ID or name in the top table ||
|:heavy_check_mark:| Error message is displayed if the part or parts are not found ||
|:heavy_check_mark:| If search field is empty, display all parts ||
|:heavy_check_mark:| User should be able to enter data into active text fields ||
|:heavy_check_mark:| Top table is identical to Parts TableView in the Main form ||
|:heavy_check_mark:| User can add parts to bottom table by selecting parts from top table and clicking add ||
|:heavy_check_mark:| Remove Associated Part button removes selected part from bottom table ||
|:heavy_check_mark:| After saving, users are redirected to the Main form ||
|:heavy_check_mark:| Canceling or exiting form redirects users to the Main form ||

### G. Implement input validation and logical error checks
|    | Task  | Notes | 
|----|-------|-------|
|:heavy_check_mark:| Check Min <= Inventory <= Max ||
|| Part with Product association cannot be deleted ||
|:heavy_check_mark:| Confirm Delete and Remove actions ||
|:heavy_check_mark:| Return error messages instead of crashing app on bad entry data ||

### H. Provide a folder containing Javadoc files generated. Mention location in main header declaration.
