import java.util.ArrayList;
import java.io.*;

public class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Add a book
    public void addBook(Book book) {

        for (Book existingBook : books) {

            if (existingBook.getId() == book.getId()) {
                System.out.println("Book ID already exists!");
                return;
            }
        }

        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Display all books
    public void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\n========== BOOK LIST ==========");

        for (Book book : books) {

            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Status: " + book.getStatus());
            System.out.println("-------------------------------");
        }
    }

    // Find book by ID
    private Book findBookById(int id) {

        for (Book book : books) {

            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    // Search book by ID
    public void searchBook(int id) {

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("\n========== BOOK FOUND ==========");
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Status: " + book.getStatus());
    }

    // Search book by title
    public void searchBookByTitle(String title) {

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {

                System.out.println("\n========== BOOK FOUND ==========");
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Status: " + book.getStatus());
                System.out.println("-------------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // Remove book by ID
    public void removeBook(int id) {

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println(
                    "Cannot remove book because it is currently issued!"
            );
            return;
        }

        books.remove(book);

        System.out.println("Book removed successfully!");
    }

    // Issue book by ID
    public void issueBook(int id) {

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isAvailable()) {

            book.setAvailable(false);
            System.out.println("Book issued successfully!");

        } else {

            System.out.println("Book is already issued!");
        }
    }

    // Return book by ID
    public void returnBook(int id) {

        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!book.isAvailable()) {

            book.setAvailable(true);
            System.out.println("Book returned successfully!");

        } else {

            System.out.println("Book is already available!");
        }
    }

    // Save books to file
    public void saveBooks() {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("books.txt"))) {

            for (Book book : books) {

                writer.write(
                        book.getId() + "|" +
                        book.getTitle() + "|" +
                        book.getAuthor() + "|" +
                        book.isAvailable()
                );

                writer.newLine();
            }

            System.out.println("Books saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving books.");
        }
    }

    // Load books from file
    public void loadBooks() {

        File file = new File("books.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 4) {

                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String author = data[2];
                    boolean available = Boolean.parseBoolean(data[3]);

                    Book book = new Book(id, title, author);
                    book.setAvailable(available);

                    books.add(book);
                }
            }

            System.out.println("Books loaded successfully!");

        } catch (IOException | NumberFormatException e) {

            System.out.println("Error while loading books.");
        }
    }
}