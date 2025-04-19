import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建一个 BookSystem 对象，最多支持 100 本书
        BookSystem bookSystem = new BookSystem(100);

        // 创建一个 Scanner 对象用于输入
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎使用图书管理系统！");
            System.out.println("1. 添加图书");
            System.out.println("2. 查找图书（通过 ID）");
            System.out.println("3. 查找图书（通过书名）");
            System.out.println("4. 修改图书信息");
            System.out.println("5. 删除图书");
            System.out.println("6. 查看所有书籍");
            System.out.println("7. 退出系统");
            System.out.print("请输入操作选项: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 吸收换行符

            switch (choice) {
                case 1 -> // 添加图书
                    bookSystem.add();
                case 2 -> {
                    // 通过 ID 查找图书
                    System.out.print("请输入要查找的书籍 ID: ");
                    String idToFind = scanner.nextLine();
                    bookSystem.findId(idToFind);
                }
                case 3 -> {
                    // 通过书名查找图书
                    System.out.print("请输入要查找的书名: ");
                    String nameToFind = scanner.nextLine();
                    bookSystem.findName(nameToFind);
                }
                case 4 -> {
                    // 修改图书信息
                    System.out.print("请输入要修改的书籍 ID: ");
                    String idToModify = scanner.nextLine();
                    bookSystem.xiugaiBook(idToModify);
                }
                case 5 -> {
                    // 删除图书
                    System.out.print("请输入要删除的书籍 ID: ");
                    String idToDelete = scanner.nextLine();
                    bookSystem.shanchu(idToDelete);
                }
                case 6 -> // 查看所有书籍
                    bookSystem.lookBooks();
                case 7 -> {
                    // 退出系统
                    System.out.println("感谢使用图书管理系统，退出中...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("无效的选项，请重新选择！");
            }
        }
    }
}
