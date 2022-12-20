# eBookstore 

A Java database application for an ebookstore.

## Description

This program is for an ebookstore that allows a bookstore clerk to enter new books into a database, update book information, delete books from the database, and search the database to find a specific book.

The program connects a MySQL database with the JDBC API as the main driver and interface for the user.

**MySQL**

A MySQL client server is initiated in command-line for the user ``clerk``. An ``ebookstore`` database is created together with a ``books`` table that stores all the book's data. The data types, or properties for each book, in the table columns include:

* ``id``
* ``Title``
* ``Author``
* ``Qty``

The ``books`` table is populated with sample book records, including:

```text
(3001, 'A Tale of Two Cities', 'Charles Dickens', 30)
(3002, 'Harry Potter and the Philosopher''s Stone', 'J.K.Rowling', 40)
(3003, 'The Lion, the Witch and the Wardrobe', 'C. S. Lewis', 25)
(3004, 'The Lord of the Rings', 'J.R.R Tolkien', 37)
(3005, 'Alice in Wonderland', 'Lewis Carroll', 12)
```

**JDBC**

Once the client-server system is set up, the Java program allows for further accessing
and manipulating to be carried out on the data by the user. 

At program startup, a ``connection`` is established with the ``ebookstore`` database in the ``Main()`` class and the ``programMenu`` is presented to the bookstore clerk.

```java
// Program menu.
String programMenu = "\n--- Ebook Store Menu ---\n"
		+ "1. Enter book \n"
		+ "2. Update book \n"
		+ "3. Delete book \n"
		+ "4. Search books \n"
		+ "5. View all books \n"
		+ "0. Exit \n"
		+ "Enter slelection: ";
```

**If user selects ``1``:**

The ``enterBook()`` method is called which allows the bookstore clerk to create a new record of a book in the system. The program executes an ``INSERT INTO`` statement and adds the new book into the ``books`` table.

**If user selects ``2``:**

The ``updateBook()`` method is called where the user is prompted to enter the ID of the book to update and presented with the ``updateMenu`` submenu.

```java
String updateMenu = "\n--- Update Menu ---\n"
	+ "1. Update book ID \n"
	+ "2. Update book title \n"
	+ "3. Update book author \n"
	+ "4. Update book quantity \n"
	+ "Enter selection: ";
```
The program executes an ``UPDATE`` statement on the ``books`` table and ``SETS``'s the selected property ``WHERE id`` matches the ID of the book the user inputted.

**If user selects ``3``:**

The ``deleteBook()`` method is called where the user is prompted to enter the ID of the book to delete. The program executes a ``DELETE FROM`` statement and removes the book from the ``books`` table.

**If user selects ``4``:**

The ``searchBook()`` method is called where the user is prompted to enter the ID of the book to search for. The program executes a ``SELECT * FROM`` statement and returns the ``ResultSet`` of the book from the ``books`` table.

**If user selects ``5``:**

The ``viewBooks()`` method is called which executes a ``SELECT id, title, author, quantity FROM books`` statement and returns the ``ResultSet`` of all the books in the database.

## Functionality summary

* Add new books into the database.
* Update book details in the database.
* Delete books from the database
* Search the database to find a specific book.

## Programming principles

This program employs the concepts of JDBC database programming, get and set statements, and execution queries. Furthermore it employs fundamental programming techniques that include switch block handling, comparison operators and conditional logic.

## Dependencies

* import java.sql.Connection;
* import java.sql.DriverManager;
* import java.sql.ResultSet;
* import java.sql.SQLException;
* import java.sql.Statement;
* import java.util.Scanner;

## Running the program

* Follow the prompts as illustrated in the .png screenshots to set up the database in your ``command`` window. 
* Run the ``main.java`` file in any Java IDE.

## Code preview

```java
/** Method to UPDATE a book that matches the <code>id</code> inputted by the user.
* <code>updateMenu</code> offers user the choice to edit a books id, title, author or quantity.
* Returns the the number of records affected and prints the values of the selected row.
* 
* @param 	statement on an existing connection
* @param 	scanner to read users input
* @throws 	SQLException
*/
public static void updateBook(Statement statement, Scanner input) throws SQLException {
	
	System.out.println("\nEnter the ID of the book to update: ");
	int recordID = input.nextInt();
	
	String updateMenu = "\n--- Update Menu ---\n"
			+ "1. Update book ID \n"
			+ "2. Update book title \n"
			+ "3. Update book author \n"
			+ "4. Update book quantity \n"
			+ "Enter selection: ";
	
	System.out.println(updateMenu);
	int updateChoice = input.nextInt();	
	input.nextLine();
	
	int rowsUpdated;

	// Conditional logic to execute chosen update.
	// Update selected book ID in table.
	if (updateChoice == 1) {
		System.out.println("\nEnter the new ID: ");
		int updateID = input.nextInt();
		
		rowsUpdated = statement.executeUpdate(
				"UPDATE books SET id = " + updateID + " WHERE id = " + recordID);
    
		System.out.println("\nQuery complete, " + rowsUpdated + " rows updated.");
		viewBooks(statement);
	}
	// Update selected book title in table.
	else if (updateChoice == 2) {
		System.out.println("\nEnter the new title: ");
		String updateTitle = input.nextLine();
		
		rowsUpdated = statement.executeUpdate(
				"UPDATE books SET title = '" + updateTitle + "' WHERE id = " + recordID); 
    
		System.out.println("\nQuery complete, " + rowsUpdated + " rows updated.");
		viewBooks(statement);
	}
	// Update selected book author in table.
	else if (updateChoice == 3) {
		System.out.println("\nEnter the new author: ");
		String updateAuthor = input.nextLine();
		
		rowsUpdated = statement.executeUpdate(
				"UPDATE books SET author = '" + updateAuthor + "' WHERE id = " + recordID);
    
		System.out.println("\nQuery complete, " + rowsUpdated + " rows updated.");
		viewBooks(statement);
	}
	// Update selected book quantity in table.
	else if (updateChoice == 4) {
		System.out.println("\nEnter the new quantity: ");
		int updateQuantity = input.nextInt();
		
		rowsUpdated = statement.executeUpdate(
				"UPDATE books SET quantity = " + updateQuantity + " WHERE id = " + recordID);
    
		System.out.println("\nQuery complete, " + rowsUpdated + " rows updated.");
		viewBooks(statement);
	}
	else {
		System.out.println("\nOops! Invalid menu choice.");
	}
}
```

## Program output preview

```text
Enter the ID of the book to update: 3001

--- Update Menu ---

1. Update book ID
2. Update book title
3. Update book author
4. Update book quantity
Enter selection: 4

Enter the new quantity: 31

Query complete, 1 rows updated.

3001, 'A Tale of Two Cities', 'Charles Dickens', 31
3002, 'Harry Potter and the Philosopher''s Stone', 'J.K.Rowling', 40
3003, 'The Lion, the Witch and the Wardrobe', 'C. S. Lewis', 25
3004, 'The Lord of the Rings', 'J.R.R Tolkien', 37
3005, 'Alice in Wonderland', 'Lewis Carroll', 12
```

&nbsp;
***  
_In a good book, the best is between the lines._ ~ Swedish Proverb
***
&nbsp;

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE.

## Author

**Megan Bisschoff** 2022

*Project submitted for Software Engineering learnership Level 3 Task 7.2 at [HyperionDev](https://www.hyperiondev.com/)*

*[View](https://www.hyperiondev.com/portfolio/86596/) submission results.*