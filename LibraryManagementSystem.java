import java.util.*;

class Book {
    String title, isbn;
    boolean isIssued;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
        this.isIssued = false;
    }
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}

class Library {
    Map<String, User> users = new HashMap<>();
    Map<String, Book> books = new HashMap<>();
    Map<String, String> issuedBooks = new HashMap<>();

    void addUser(String name) {
        if (users.containsKey(name.toLowerCase())) {
            System.out.println("User already exists!");
        } else {
            users.put(name.toLowerCase(), new User(name));
            System.out.println("User " + name + " registered.");
        }
    }

    void addBook(String title, String isbn) {
        if (books.containsKey(isbn)) {
            System.out.println("Book with this ISBN already exists!");
        } else {
            books.put(isbn, new Book(title, isbn));
            System.out.println("Book added: " + title);
        }
    }

    void issueBook(String isbn, String user) {
        user = user.toLowerCase();
        if (!users.containsKey(user)) {
            System.out.println("User not registered!");
            return;
        }
        if (!books.containsKey(isbn)) {
            System.out.println("Book not found!");
            return;
        }
        if (books.get(isbn).isIssued) {
            System.out.println("Book already issued!");
            return;
        }
        books.get(isbn).isIssued = true;
        issuedBooks.put(isbn, user);
        System.out.println("Book issued to " + users.get(user).name);
    }

    void returnBook(String isbn, String user) {
        user = user.toLowerCase();
        if (!issuedBooks.containsKey(isbn) || !issuedBooks.get(isbn).equals(user)) {
            System.out.println("Book not issued to you!");
            return;
        }
        books.get(isbn).isIssued = false;
        issuedBooks.remove(isbn);
        System.out.println("Book returned by " + users.get(user).name);
    }

    void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book.title + " (ISBN: " + book.isbn + ") - " + (book.isIssued ? "Issued" : "Available"));
        }
    }

    // Case-insensitive book search
    Book findBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n1. Register User\n2. Add Book\n3. Issue Book\n4. Return Book\n5. Display Books\n6. Search Book\n7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String name = scanner.nextLine();
                    library.addUser(name);
                    break;
                case 2:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(title, isbn);
                    break;
                case 3:
                    System.out.print("Enter ISBN: ");
                    String issueIsbn = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String user = scanner.nextLine();
                    library.issueBook(issueIsbn, user);
                    break;
                case 4:
                    System.out.print("Enter ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String returnUser = scanner.nextLine();
                    library.returnBook(returnIsbn, returnUser);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.print("Enter book title: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = library.findBookByTitle(searchTitle);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook.title + " (ISBN: " + foundBook.isbn + ") - " + (foundBook.isIssued ? "Issued" : "Available"));
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
