import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testStatementCoverage() {
        //I
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "7777777777777777"));

        //II
        List<Item> items_test1 = List.of(new Item(null, 1, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items_test1, "1111111111111111"));

        //III
        List<Item> items_test3 = List.of(new Item("Item", 3, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items_test3, "A234567890123456"));

        //IV
        List<Item> items_test4 = List.of(new Item("Item", 3, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items_test4, null));

        //V
        List<Item> items_test5 = List.of(
                new Item("firstItem", 5, 140, 0.2),   // 5 * (140 - 28) = 5 * 112 = 560 -30
                new Item("secondItem", 15, 350, 0.0)  // 15 * 350 = 5250 - 30
        );
        //za firstItem i secondItem | firstItem -> -30+140*(1-0.2*5) = 530 (vo dvata if bidekji discount>0)
        //                          | secondItem-> 530 - 30 + 350 * 15  (discount==0 no cenata>300  zatoa vleuguva vo if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10){ //9
        //                sum -= 30; //10
        //            }
        // i vo else, pa:
        double expect = 5750;

        assertEquals(expect, SILab2.checkCart(items_test5, "8888888888888888"));
    }
    //item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10
    @Test
    void testMultipleConditions(){
        //USLOV: if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
        // I: T || X || X
        // II: F || T || X
        // III: F || F || T
        // IV: F || F || F

        // I -> item.getPrice() > 300 (true) i drugite moze da bidat true ili false -> if uslovot e ispolnet
        // pa spored logikata na kodot treba da se dobie ocekuvanata vrednost
        List<Item> items_test1 = List.of(new Item("item_test1", 1, 700, 0));
        double expected_test1 = -30 + 700 * 1;
        assertEquals(expected_test1, SILab2.checkCart(items_test1, "1111111111111111"));

        // II -> item.getPrice()<=300, item.getDiscount() >0 i item.getQuantity()  moze da bide true ili false bidekji sekako uslovot e ispolnet
        //poradi toa sto discount e true
        List<Item> items_test2 = List.of(new Item("item_test2", 1, 100, 0.1));
        // price <= 300, quantity <= 10 => поради discount > 0 се активира условот
        double expected_test2 = -30 + 100 * (1 - 0.1) * 1; // = -30 + 90 = 60
        assertEquals(expected_test2, SILab2.checkCart(items_test2, "2222222222222222"));

        // III -> item.getPrice() <=300, item.getDiscount()  <=0, item.getQuantity() > 10 -> vleguva vo if

        List<Item> items_test3 = List.of(new Item("item_test3", 15, 80, 0));
        double expected_test3 = -30 + 80 * 15; // = -30 + 1100 = 1070
        assertEquals(expected_test3, SILab2.checkCart(items_test3, "3333333333333333"));

        // IV site se false, ne se ispolnuva if uslovot
        List<Item> items_test4 = List.of(new Item("item_test4", 3, 50, 0));
        double expected_test4 = 50 * 3; //
        assertEquals(expected_test4, SILab2.checkCart(items_test4, "1234098733334444"));

    }

}