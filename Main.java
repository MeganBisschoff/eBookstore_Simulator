//package ebookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * The main driver of a program that can be used by a bookstore clerk. 
 * The program connects with a database and presents the user with a menu that allows the clerk to 
 * enter new books into the database, update book information, delete books from the database, 
 * and search the database to find a specific book.
 * 
 * @author Megan Bisschoff
 */
public class Main {

    /**
    * Method to INSERT the values of a book inputted by the user.
    * Returns the the number of records affected and prints the values of the inserted row.
    * 
    * @param 	statement on an existing connection
    * @param 	scanner to read users input
    * @throws 	SQLException
    */
	public static void enterBook(Statement statement, Scanner input) throws SQLException {
		
		System.out.println("\nEnter the books id: ");
		int recordID = input.nextInt();
		input.nextLine();
		System.out.println("\nEnter the books title: ");
		String recordTitle = input.nextLine();
		System.out.println("\nEnter the books author: ");
		String recordAuthor = input.nextLine();
		System.out.println("\nEnter the books quantity: ");
		int recordQuantity = input.nextInt();
		
		// Insert new book values into table.
        int rowsInserted = statement.executeUpdate("INSERT INTO books VALUES (" + recordID + ", '" +
        		recordTitle + "', '" + recordAuthor + "', " + recordQuantity + ")");
        
        System.out.println("\nQuery complete, " + rowsInserted + " rows added.");
        viewBooks(statement);
	}
	
    /**
    * Method to UPDATE a book that matches the <code>id</code> inputted by the user.
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

    /**
    * Method to DELETE a book that matches the <code>id</code> inputted by the user.
    * Returns the the number of records affected and prints the values of the selected row.
    * 
    * @param 	statement on an existing connection
    * @param 	scanner to read users input
    * @throws 	SQLException
    */
	public static void deleteBook(Statement statement, Scanner input) throws SQLException {
	
		System.out.println("\nEnter the ID of the book to delete: ");
		int recordID = input.nextInt();
		
		// Delete selected book from table.
        int rowsDeleted = statement.executeUpdate("DELETE FROM books WHERE id = " + recordID);
        
        System.out.println("\nQuery complete, " + rowsDeleted + " rows removed.");
        viewBooks(statement);
	}

    /**
    * Method to SELECT and search for a book that matches the <code>id</code> inputted by the user.
    * Returns the query object and prints all values of the selected row.
    * 
    * @param 	statement on an existing connection
    * @param 	scanner to read users input
    * @throws 	SQLException
    */
	public static void searchBook(Statement statement, Scanner input) 
			throws SQLException {
	
		System.out.println("\nEnter the ID of the book you'd like to search: ");
		int searchID = input.nextInt();
		
		// Search selected book from table.
		ResultSet resultSearch = statement.executeQuery("SELECT * FROM books WHERE id = " + searchID);
        
        // Loop over the results, printing them all.
        while (resultSearch.next()) {
            System.out.println("\n--- Search Result ---\n"
            		+ "ID: " + resultSearch.getInt("id") 
            		+ "\nTitle: " + resultSearch.getString("title")
            		+ "\nAuthor: " + resultSearch.getString("author")
            		+ "\nStock: " + resultSearch.getInt("quantity"));
        }
	}
	
    /**
    * Method to view all books in the books table.
    * Returns the query result by looping through the table and printing all values in all rows.
    * 
    * @param	statement on an existing connection
    * @throws 	SQLException
    */
    public static void viewBooks(Statement statement) throws SQLException {
        
        ResultSet resultValues = statement.executeQuery(
        		"SELECT id, title, author, quantity FROM books");
        
        System.out.println("\n--- Ebook Store ---");
        while (resultValues.next()) {
            System.out.println(resultValues.getInt("id") + ", "
                    + resultValues.getString("title") + ", "
                    + resultValues.getString("author") + ", "        
                    + resultValues.getInt("quantity"));
        }
    }
    
    /**
    * Main method to establish a connection with the <code>ebookstore</code> database and perform
    * various operations on the objects in the <code>books</code> table.
    * The program menu is diplayed and a switch block handles the menu options selcted by the user.
    * 
    * @param 	args
    */
	public static void main(String[] args) {

		try {
			// Connect to the ebookstore database and allocate a Statement object in the Connection.
			Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "clerk", "b00ks");
            Statement statement = connection.createStatement();
            
            // Open scanner to read clerks input.
	        Scanner input = new Scanner(System.in);
            
	        // Program menu.
            String programMenu = "\n--- Ebook Store Menu ---\n"
            		+ "1. Enter book \n"
            		+ "2. Update book \n"
            		+ "3. Delete book \n"
            		+ "4. Search books \n"
            		+ "5. View all books \n"
            		+ "0. Exit \n"
            		+ "Enter slelection: ";
	        
            int menuChoice;
            showMenu: do {
	            System.out.println(programMenu);
	            menuChoice = input.nextInt();

	            switch (menuChoice) {
		            case 1: enterBook(statement, input);
		            	break;
		            case 2: updateBook(statement, input);
		            	break;
		            case 3: deleteBook(statement, input);
	            		break;
		            case 4: searchBook(statement, input);
	            		break;
		            case 5: viewBooks(statement);
	            		break;
		            case 0: 
		            	System.out.println("\nThank you, good bye!");
		            	break showMenu;
		            default: System.out.println("\nInvalid menu choice, please try again.\n");
	            }
            } while (true);
            
	         // Close connections and objects.
            input.close();
            statement.close();
            connection.close();

        } 
		catch (SQLException e) {
        	e.printStackTrace();
        } 
	}
	
}

