public class Book{
    //属性
    private String name;
    private String id;
    private String zuozhe;
    private double price;
    //构造函数
    public Book(){
        name="";
        id="null";
        zuozhe="";
        price=0;
    }
    //全部设置
    public void setBook(String name, String id, String zuozhe, double price) {
        this.name = name;
        this.id = id;
        this.zuozhe = zuozhe;
        this.price = price;
    }
    //单个获取
    public String getName() {
        return name;
    }
    //单个设置
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public String getZuozhe() {
        return zuozhe;
    }
    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}