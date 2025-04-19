
import java.util.Scanner;

public class BookSystem {
    private Book[] books;
    private int booksl; // 储存多少本书；

    public BookSystem() {
        books = new Book[100];
        booksl = 0;
    }

    public BookSystem(int n) {
        books = new Book[n];
        booksl = 0;
    }

    private boolean chongfujiancha(String cfid) {
        for (int i = 0; i < booksl; i++) {
            if (books[i].getId().equals(cfid)) {
                return true; // 找到重复的 ID，返回 true
            }
        }
        return false; // 没有找到重复的 ID，返回 false
    }

    public void add() {
        if (booksl < books.length) {
            Book newBook = new Book();
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入书名: ");
            String name = scanner.nextLine();
            System.out.println("请输入书的ID: ");
            String id = scanner.nextLine();
            while (chongfujiancha(id)) {
                System.out.println("该ID已存在,请重新输入一个不同的ID: ");
                id = scanner.nextLine();
            }
            System.out.println("请输入作者: ");
            String zuozhe = scanner.nextLine();
            System.out.println("请输入价格: ");
            double price = scanner.nextDouble();
            newBook.setBook(name, id, zuozhe, price);
            books[booksl] = newBook;
            System.out.println("储存完成");
            booksl++;
        } else {
            System.out.println("空间不足");
        }
    }

    public void findId(String id) {
        for (int i = 0; i < booksl; i++) {
            if (books[i].getId().equals(id)) {
                System.out.println("找到书籍：");
                System.out.println("书名: " + books[i].getName());
                System.out.println("ID: " + books[i].getId());
                System.out.println("作者: " + books[i].getZuozhe());
                System.out.println("价格: " + books[i].getPrice());
                return;
            }
        }
        System.out.println("未找到对应书号的书籍！");
    }

    public void lookBooks() {
        if (booksl == 0) {
            System.out.println("没有任何书籍。");
            return;
        }

        System.out.println("所有书籍的信息：");
        for (int i = 0; i < booksl; i++) {
            Book book = books[i];
            System.out.println("书名: " + book.getName());
            System.out.println("ID: " + book.getId());
            System.out.println("作者: " + book.getZuozhe());
            System.out.println("价格: " + book.getPrice());
            System.out.println("---------------------------");
        }
    }

    public void findName(String name) {
        boolean found = false;
        for (int i = 0; i < booksl; i++) {
            if (books[i].getName().equals(name)) {
                // 找到匹配书名的书
                if (!found) {
                    System.out.println("找到以下书籍：");
                }
                System.out.println("书名: " + books[i].getName());
                System.out.println("ID: " + books[i].getId());
                System.out.println("作者: " + books[i].getZuozhe());
                System.out.println("价格: " + books[i].getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到对应书名的书籍！");
        }
    }

    public void shanchu(String id) {
        for (int i = 0; i < booksl; i++) {
            if (books[i].getId().equals(id)) {
                for (int j = i; j < booksl - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[booksl - 1] = null;
                booksl--;
                System.out.println("图书已删除");
                return;
            }
        }
        System.out.println("未找到指定书号的图书！");
    }

    // 查看有多少书
    public int getbootsl() {
        return booksl;
    }

    // 返回查找id函数用，非外部用
    private Book findID(String id) {
        for (int i = 0; i < booksl; i++) {
            if (books[i].getId().equals(id)) {
                return books[i]; // 找到图书，返回该图书对象
            }
        }
        return null; // 未找到该图书
    }

    public void xiugaiBook(String id) {
        Book shu = findID(id);

        // 判断图书是否存在
        if (shu != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入新的书名: ");
            String newName = scanner.nextLine();
            shu.setName(newName);
            System.out.println("请输入新的作者: ");
            String newAuthor = scanner.nextLine();
            shu.setZuozhe(newAuthor);
            System.out.println("请输入新的价格: ");
            double newPrice = scanner.nextDouble();
            shu.setPrice(newPrice);
            System.out.println("图书内容修改完成！");
        } else {
            System.out.println("没有找到该图书！");
        }
    }

}