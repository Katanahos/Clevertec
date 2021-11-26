

public enum Product {

    /** База данных продуктов */
    Apple("Apple",1,10,false),
    Apricot("Apricot",2,21,false),
    Banana("Banana",3,15.00,false),
    Grapes("Grapes",4,17,false),
    Kiwi("Kiwi",5,19.50,false),
    Lemon("Lemon",6,12.50,false),
    Mango("Mango",7,25,false),
    Melon("Melon",8,14.25,true),
    Orange("Orange",9,11,false),
    Peach("Peach",10,16.75,false),
    Pear("Pear",11,12,true),
    Watermelon("Watermelon",12,8.50,true);

    public String productName; // наименование
    public int id;             // индификатор
    public double price;       // цена
    public boolean discount;   // акционный товар


    Product(String productName, int id, double price, boolean discount) {
        this.productName = spaceCreate(productName);
        this.id = id;
        this.price = price;
        this.discount = discount;
    }

    private String spaceCreate(String name){ // для красоты заполним пробелами
        StringBuilder result = new StringBuilder(name);
        while (result.length()<15){
            result.append(" ");
        }
        return result.toString();
    }

    public static Product fromNumber(Integer number) { // получаем элемент енума по полю id
        if (number != null) {
            for (Product product : Product.values()) {
                if (number== (product.id)) {
                    return product;
                }
            }
        }
        return null;
    }

}
