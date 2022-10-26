import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Library {
    public static void main(String[] args) {
        LibraryOperations libOps = new LibraryOperations();
        List<Book> allBooks = new ArrayList<>();
        List<Issue> allIssuedBooks = new ArrayList<>();

        while(true) {
            System.out.println("Choose an operation: ");
            System.out.println("1. To add book, type add");
            System.out.println("2. To show books, type show");
            System.out.println("3. To delete book, type delete");
            System.out.println("4. Search for book by book name, type searchByBookName");
            System.out.println("5. Search for book by writer name, type searchByWriterName");
            System.out.println("6. To issue a book, type issue");
            System.out.println("7. To return a book, type return");
            System.out.println("8. To show all the issued books, type allIssuedBooks");
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Enter the option: ");
            String option = sc1.nextLine();

            switch (option) {
                case "add":
                    libOps.addBook(allBooks);
                    break;
                case "show":
                    libOps.showBook(allBooks);
                    break;
                case "delete":
                    libOps.deleteBook(allBooks);
                    break;
                case "searchByBookName":
                    System.out.println("Enter the book name to search for: ");
                    String bookName = sc1.nextLine();
                    libOps.searchByBookName(allBooks, bookName);
                    break;
                case "issue":
                    libOps.issueBook(allIssuedBooks, allBooks);
                    break;
                case "return":
                    libOps.returnBook(allIssuedBooks, allBooks);
                    break;
                case "allIssuedBooks":
                    libOps.showAllIssuedBooks(allIssuedBooks);
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }
        }
    }
}
