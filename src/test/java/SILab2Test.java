import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Item> createList(Item... elems){
        return new ArrayList<>(Arrays.asList(elems));
    }
    @Test
    void everyBranchTest() {
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null,200));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(new Item("itemName",null,400,0)),300));
        assertTrue(ex.getMessage().contains("No barcode!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(new Item("itemName","23@ab",150, 0.2F)),300));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        boolean result1 = SILab2.checkCart(createList(new Item("","060",400, 0.3F),new Item("itemName","234",500,0)), 1000);
        assertTrue(result1, "This test should return true");

        boolean result2 = SILab2.checkCart(createList(new Item("","060",400, 0.5F),new Item("itemName","234",400,0)), 300);
        assertFalse(result2, "This test should return false");

    }

    private boolean condition(Item item){
        return item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0';
    }

    @Test
    void testMultipleConditions(){

        Item item1 = new Item("item1","012",400, 0.3F);
        Item item2 = new Item("item2","123",500, 0.4F);
        Item item3 = new Item("item3","011",600, 0.0F);
        Item item4 = new Item("item4","2323",400, 0.0F);
        Item item5 = new Item("item5","0123",250, 0.3F);
        Item item6 = new Item("item6","4453",150, 0.5F);
        Item item7 = new Item("item7","08799",100, 0.0F);
        Item item8 = new Item("item8","1123",120, 0.0F);

        assertTrue(condition(item1), "This test for " + item1 + " should be true");
        assertFalse(condition(item2), "This test for " + item2 + " should be false");
        assertFalse(condition(item3), "This test for " + item3 + " should be false");
        assertFalse(condition(item4), "This test for " + item4 + " should be false");
        assertFalse(condition(item5), "This test for " + item5 + " should be false");
        assertFalse(condition(item6), "This test for " + item6 + " should be false");
        assertFalse(condition(item7), "This test for " + item7 + " should be false");
        assertFalse(condition(item8), "This test for " + item8 + " should be false");
    }
}