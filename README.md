# CyberMart - JavaFX E-commerce Application

## Introduction
CyberMart is a JavaFX-based e-commerce application with MySQL integration, providing a graphical user interface for users to browse, shop, and manage their online shopping experience.

## Folder Structure

- **build**: Contains build-related files and artifacts.
- **dist**: The distribution folder where compiled and packaged application files are stored.
- **lib**: Libraries and dependencies required for the project.
- **nbproject**: NetBeans project-related files.
- **src**: Source code for the CyberMart application.
  - **cybermart**: Contains Java classes related to the CyberMart application.
  - **images**: Placeholder for general images used in the application.
  - **productimages**: Placeholder for product images.
  - **shopimages**: Placeholder for shop images.

## Technologies Used
- JavaFX: The graphical user interface framework.
  - Version: 17
- MySQL: Database management system for storing and retrieving data.
- ControlsFX: Additional UI controls library.
  - Version: 11.1.1
- JFoenix: JavaFX material design library.
  - Version: 8.0.7 and 9.0.10

## Development Environment
- JDK Version: 17
- JavaFX Version: 17
- Scene Builder Version: 18.0.0


## Getting Started

# Open in NetBeans:

1. Launch NetBeans IDE.
2. Choose "Open Project" and select the CyberMart project folder.

# Database Setup:

1. Create a MySQL database named `cybermart`.
2. Import the provided SQL script in the `table` folder to set up necessary tables.

# Add Required Libraries:

1. Download the ControlsFX library (controlsfx-11.1.1.jar) 
2. Download the JFoenix library (jfoenix-8.0.7.jar and jfoenix-9.0.10.jar)
3. Copy the downloaded JAR files into the `lib` folder of your CyberMart project.


# Run the Application:

1. Right-click on the project in NetBeans.
2. Select "Run" to launch the CyberMart application.

## Project Details

### CyberMart Package

- `DashboardPage.fxml`: Dashboard page FXML file.
- `DashboardPageController.java`: Controller for the Dashboard page.
- `DatabaseConnection.java`: Database connection utility class.
- `FrontPageController.java`: Controller for the front page.
- `HomepageController.java`: Controller for the homepage.
- `LoginController.java`: Controller for the login page.
- `MyListener.java`: Custom listener interface.
- `PaymentPageController.java`: Controller for the payment page.
- `ProductDetailsController.java`: Controller for the product details page.
- `SearchPageController.java`: Controller for the search page.
- `ShoppingCartController.java`: Controller for the shopping cart page.
- `SignupController.java`: Controller for the signup page.
- `UpdateProductController.java`: Controller for updating product information.
- `VendorPageController.java`: Controller for the vendor page.
- `VendorProductsController.java`: Controller for the vendor products page.
- `cardController.java`: Controller for the card view.
- `cardObject.java`: Class representing a card object.
- `dashboardObject.java`: Class representing a dashboard object.
- `dashboardpage.css`: CSS styling for the dashboard page.
- `frontPage.fxml`: Front page FXML file.
- `frontpage.css`: CSS styling for the front page.
- `fxmlLoaderHome.java`: FXML loader utility class for the homepage.
- `homepage.css`: CSS styling for the homepage.
- `homepage.fxml`: Homepage FXML file.
- `login.css`: CSS styling for the login page.
- `login.fxml`: Login page FXML file.
- `main.java`: Main class to launch the application.
- `paymentPage.fxml`: Payment page FXML file.
- `paymentpage.css`: CSS styling for the payment page.
- `pleaseLogin.fxml`: FXML file for the "please login" message.
- `productCard.fxml`: FXML file for the product card.
- `productDetails.fxml`: FXML file for the product details page.
- `productSearchModel.java`: Model class for product search.
- `productcard.css`: CSS styling for the product card.
- `productdetails.css`: CSS styling for the product details page.
- `searchPage.fxml`: Search page FXML file.
- `searchpage.css`: CSS styling for the search page.
- `shoppingCart.fxml`: Shopping cart FXML file.
- `shoppingCartModel.java`: Model class for the shopping cart.
- `shoppingcart.css`: CSS styling for the shopping cart.
- `signup.css`: CSS styling for the signup page.
- `signup.fxml`: Signup page FXML file.
- `updateProduct.fxml`: FXML file for updating product information.
- `updateproduct.css`: CSS styling for updating product information.
- `vendorPage.fxml`: Vendor page FXML file.
- `vendorProducts.fxml`: Vendor products FXML file.
- `vendorpage.css`: CSS styling for the vendor page.
- `vendorproducts.css`: CSS styling for the vendor products page.

### Additional Resources

- `LICENSE.md`: License information for the project.
- `README.md`: General information and instructions for the project.

## SQL Table Creation Instructions

For detailed instructions on creating the required database tables, refer to the following files in the `table` folder:

- `Instructions.txt`
- `c_user.txt`
- `current_vendor.txt`
- `customer_info.txt`
- `dashboard.txt`
- `product_info.txt`
- `row_table.txt`
- `search_info.txt`
- `shopping_cart.txt`
- `vendor_table.txt`



## License

This project is licensed under the terms of the [MIT License](LICENSE.md).





