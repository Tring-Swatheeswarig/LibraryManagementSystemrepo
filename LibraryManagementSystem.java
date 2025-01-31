import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ")";
    }
}

class User {
    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return name + " (User ID: " + userId + ")";
    }
}

class Library {
    private List<Book> books;
    private Map<String, String> issuedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.issuedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        issuedBooks.remove(isbn);
        System.out.println("Book with ISBN " + isbn + " removed.");
    }

    public void issueBook(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isIssued()) {
                book.issueBook();
                issuedBooks.put(isbn, user.getUserId());
                System.out.println(user.getName() + " issued " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not available for issue.");
    }

    public void returnBook(String isbn, User user) {
        if (issuedBooks.containsKey(isbn) && issuedBooks.get(isbn).equals(user.getUserId())) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    book.returnBook();
                    issuedBooks.remove(isbn);
                    System.out.println(user.getName() + " returned " + book.getTitle());
                    return;
                }
            }
        }
        System.out.println("Invalid return attempt.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books available in the library:");
            for (Book book : books) {
                System.out.println(book + (book.isIssued() ? " [Issued]" : " [Available]"));
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        User user1 = new User("Swathi", "U001");
        User user2 = new User("Kaviya", "U002");

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    Book newBook = new Book(title, author, isbn);
                    library.addBook(newBook);
                    break;

                case 2:
                    System.out.print("Enter ISBN of book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;

                case 3:
                    System.out.print("Enter ISBN of book to issue: ");
                    String issueIsbn = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    if (userId.equals(user1.getUserId())) {
                        library.issueBook(issueIsbn, user1);
                    } else if (userId.equals(user2.getUserId())) {
                        library.issueBook(issueIsbn, user2);
                    } else {
                        System.out.println("Invalid user ID");
                    }
                    break;

                case 4:
                    System.out.print("Enter ISBN of book to return: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String returnUserId = scanner.nextLine();
                    if (returnUserId.equals(user1.getUserId())) {
                        library.returnBook(returnIsbn, user1);
                    } else if (returnUserId.equals(user2.getUserId())) {
                        library.returnBook(returnIsbn, user2);
                    } else {
                        System.out.println("Invalid user ID");
                    }
                    break;

                case 5:
                    library.displayBooks();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
