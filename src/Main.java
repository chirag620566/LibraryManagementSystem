import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        library.loadBooks();

        boolean running = true;

        while (running) {

            System.out.println("\n=================================");
            System.out.println("    LIBRARY MANAGEMENT SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.print("Enter Book ID: ");

                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid Book ID! Please enter a number."
                        );
                        break;
                    }
if (id <= 0) {
    System.out.println("Book ID must be greater than 0!");
    break;
}
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();

                    if (title.trim().isEmpty()) {
                        System.out.println("Book title cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();

                    if (author.trim().isEmpty()) {
                        System.out.println("Author name cannot be empty!");
                        break;
                    }

                    Book book = new Book(id, title, author);

                    library.addBook(book);

                    break;

                case 2:

                    library.displayBooks();

                    break;

                case 3:

    System.out.println("\n========== SEARCH BOOK ==========");
    System.out.println("1. Search by ID");
    System.out.println("2. Search by Title");

    System.out.print("Enter your choice: ");

    String searchChoice = scanner.nextLine();

    switch (searchChoice) {

        case "1":

            System.out.print("Enter Book ID to search: ");

            int searchId;

            try {
                searchId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid Book ID! Please enter a number."
                );
                break;
            }

            library.searchBook(searchId);

            break;

        case "2":

            System.out.print("Enter Book Title to search: ");

            String searchTitle = scanner.nextLine();

            if (searchTitle.trim().isEmpty()) {
                System.out.println("Search title cannot be empty!");
                break;
            }

            library.searchBookByTitle(searchTitle);

            break;

        default:

            System.out.println("Invalid search option!");
    }

    break;
                case 4:

                    System.out.print("Enter Book ID to remove: ");

                    int removeId;

                    try {
                        removeId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid Book ID! Please enter a number."
                        );
                        break;
                    }

                    library.removeBook(removeId);

                    break;

                case 5:

                    System.out.print("Enter Book ID to issue: ");

                    int issueId;

                    try {
                        issueId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid Book ID! Please enter a number."
                        );
                        break;
                    }

                    library.issueBook(issueId);

                    break;

                case 6:

                    System.out.print("Enter Book ID to return: ");

                    int returnId;

                    try {
                        returnId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid Book ID! Please enter a number."
                        );
                        break;
                    }

                    library.returnBook(returnId);

                    break;

                case 7:

                    library.saveBooks();

                    running = false;

                    System.out.println(
                            "Thank you for using Library Management System!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice! Please select 1 to 7."
                    );
            }
        }

        scanner.close();
    }
}