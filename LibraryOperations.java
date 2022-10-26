import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LibraryOperations {
    // add a book to the library!
    public void addBook(List<Book> allBooks) {    
        Book book1 = new Book();

        Scanner sc = new Scanner(System.in);

        // Enter book details!
        System.out.println("Enter book details");
        System.out.println("Enter book name: ");
        String bookName = sc.nextLine();
        System.out.println("Enter writer name: ");
        String writerName = sc.nextLine();
        System.out.println("Enter price: ");
        long price = sc.nextLong();
        System.out.println("Enter quantity: ");
        long quantity = sc.nextLong();

        if(bookName.isEmpty() || writerName.isEmpty() || price == 0 || quantity == 0) {
            System.out.println("Some of the fields are absent!");
        } else {
            boolean checkBook = false;
            for (Book ele : allBooks) {
                if(ele.getBookName() == bookName) {
                    checkBook = true;
                    break;
                }
            }

            if(checkBook) {
                System.out.println("---------------The book already exists!------------------------");
            } else {
                // generating id!
                String uniqueID = UUID.randomUUID().toString();

                book1.setBookId(uniqueID);
                book1.setBookName(bookName);
                book1.setWriterName(writerName);
                book1.setPrice(price);
                book1.setQuantity(quantity);
                allBooks.add(book1);
                System.out.println("Book added to collection!");
            }
        }
    }

    // show all the books in the library!
    public void showBook(List<Book> allBooks) {
        if(allBooks.size() > 0) {
            for (Book ele : allBooks) {
                System.out.println("---------------------Here are the book details------------------------");
                System.out.println("Book id: " + ele.getBookId());
                System.out.println();
                System.out.print("Book name: " + ele.getBookName());
                System.out.println();
                System.out.print("Writer name: " + ele.getWriterName());
                System.out.println();
                System.out.print("Book price: " + ele.getPrice());
                System.out.println();
                System.out.print("Quantity of book: " + ele.getQuantity());
                System.out.println();
                System.out.println("----------------------------------------------------------------------");
            }
        } else {
            System.out.println("No book exists in the library");
        }
    }

    // delete a book from the library!
    public void deleteBook(List<Book> allBooks) {
        if(allBooks.size() == 0) {
            System.out.println("---------------------------There are not books!---------------------------");
        } else {
            System.out.println("Enter book name to delete: ");
            Scanner sc1 = new Scanner(System.in);
            String bName = sc1.nextLine();
            boolean flag = false;
            for (Book ele : allBooks) {
                if(ele.getBookName().equals(bName)) {
                    allBooks.remove(ele);
                    flag = true;
                    break;
                }
            }
            if(flag) {
                System.out.println("Book deleted!");
            } else {
                System.out.println("Book does not exist!");
            }
        }
    }

    // search by book name
    public void searchByBookName(List<Book> allBooks, String bookName) {
        boolean check = false;
        for (Book ele : allBooks) {
            if(ele.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                System.out.println("---------------------Here are the book details------------------------");
                System.out.println("Book id: " + ele.getBookId());
                System.out.println();
                System.out.print("Book name: " + ele.getBookName());
                System.out.println();
                System.out.print("Writer name: " + ele.getWriterName());
                System.out.println();
                System.out.print("Book price: " + ele.getPrice());
                System.out.println();
                System.out.print("Quantity of book: " + ele.getQuantity());
                System.out.println();
                System.out.println("----------------------------------------------------------------------");
                check = true;
                break;
            } else if(ele.getBookName().toLowerCase().contains(bookName.toLowerCase())) {
                System.out.println("---------------------Here are the book details------------------------");
                System.out.println("Book id: " + ele.getBookId());
                System.out.println();
                System.out.print("Book name: " + ele.getBookName());
                System.out.println();
                System.out.print("Writer name: " + ele.getWriterName());
                System.out.println();
                System.out.print("Book price: " + ele.getPrice());
                System.out.println();
                System.out.print("Quantity of book: " + ele.getQuantity());
                System.out.println();
                System.out.println("----------------------------------------------------------------------");
                check = true;
                break;
            } else {
                check = false;
            }

            if(check == false) {
                System.out.println("Book Absent");
            }
        }
    }

    // issue book
    public void issueBook(List<Issue> allIssuedBooks, List<Book> allBooks) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the book name you want to issue: ");
        String bookName = sc.nextLine();

        System.out.println("Enter the name of the person: ");
        String userName = sc.nextLine();
        
        for (Book ele : allBooks) {
            Issue issuedBook = new Issue();
            if(ele.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                ele.setQuantity(ele.getQuantity() - 1);

                issuedBook.setBookId(ele.getBookId());
                issuedBook.setUserName(userName);
                issuedBook.setBookName(ele.getBookName());
                issuedBook.setIssuedDate(new Date());
            }
            allIssuedBooks.add(issuedBook);
        }
        System.out.println("------------------------Book issued----------------------");
    }

    // return book
    public void returnBook(List<Issue> allIssuedBooks, List<Book> allBooks) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the book name you want to return: ");
        String bookName = sc.nextLine();

        System.out.println("Enter the name of the person: ");
        String userName = sc.nextLine();
        
        for(Issue ele: allIssuedBooks) {
            if(ele.getBookName().toLowerCase().equals(bookName.toLowerCase()) && ele.getUserName().equals(userName.toLowerCase())) {
                for(Book ele1: allBooks) {
                    if(ele1.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                        ele1.setQuantity(ele1.getQuantity() + 1);
                        allIssuedBooks.remove(ele);
                    }
                }
            }
        }
        System.out.println("------------------------Book returned----------------------");
    }

    public void showAllIssuedBooks(List<Issue> allIssuedBooks) {
        if(allIssuedBooks.size() == 0) {
            System.out.println("------------------There are no issued books--------------------------");
        } else {
            for (Issue issue : allIssuedBooks) {
                System.out.println("---------------------Here are the book details------------------------");
                System.out.print("Book id: " + issue.getBookId());
                System.out.println();
                System.out.print("Person name: " + issue.getUserName());
                System.out.println();
                System.out.print("Book Name: " + issue.getBookName());
                System.out.println();
                System.out.print("Issued date: " + issue.getIssuedDate());
                System.out.println();
                System.out.println("----------------------------------------------------------------------");
            }
        }
    }
}
