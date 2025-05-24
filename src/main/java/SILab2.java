
import java.util.List;
class Item {
    String name;
    int quantity; //numerical
    int price;
    double discount;

    public Item(String name, int quantity, int price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static double checkCart(List<Item> allItems, String cardNumber){ //1
        if (allItems == null){ //2
            throw new RuntimeException("allItems list can't be null!"); //3
        }

        double sum = 0; //4
        //5.1        //5.2            //5.3
        for (int i = 0; i < allItems.size(); i++){ //5
            Item item = allItems.get(i); //6
            if (item.getName() == null || item.getName().length() == 0){ //7
                throw new RuntimeException("Invalid item!"); //8
            }

            if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10){ //9
                sum -= 30; //10
            }

            if (item.getDiscount() > 0){ //11
                sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity(); //12
            }
            else { //13
                sum += item.getPrice()*item.getQuantity(); //14
            }

        } //15
        if (cardNumber != null && cardNumber.length() == 16) { //16
            String allowed = "0123456789"; //17
            char[] chars = cardNumber.toCharArray(); //18
            //19.1       19.2                19.3
            for (int j = 0; j < cardNumber.length(); j++) { //19
                char c = cardNumber.charAt(j); //20
                if (allowed.indexOf(c) == -1) { //21
                    throw new RuntimeException("Invalid character in card number!"); //22
                }
            } //23
        }
        else{ //24
            throw new RuntimeException("Invalid card number!"); //25
        }

        return sum; //26

    } //27
}