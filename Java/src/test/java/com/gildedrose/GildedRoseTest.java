package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void testQualityDegradationTwiceAsFast()
    {
        Item[] items = new Item[] { new Item("foo", 2, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(7, app.items[0].quality);
        System.out.println(app.items[0]);

        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        System.out.println(app.items[0]);

        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        System.out.println(app.items[0]);

        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        System.out.println(app.items[0]);

    }

    @Test
    void testNeverNegative()
    {
        Item[] items = new Item[] { new Item("foo", 2, 8) };
        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 50; i++) {
            app.updateQuality();
            assertTrue(app.items[0].quality >= 0);
        }

    }

    @Test
    void testBrieQualIncreases()
    {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 8) };
        GildedRose app = new GildedRose(items);

        int prevQual = 8;
        for (int i = 0; app.items[0].quality < 50; i++) {
            app.updateQuality();
            assertTrue(app.items[0].quality > prevQual);
            System.out.println(app.items[0]);

            prevQual = app.items[0].quality;
        }

    }

    @Test
    void testNeverMoreThan50()
    {
        Item[] items = new Item[] { new Item("foo", 2, 8) };
        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 50; i++) {
            app.updateQuality();
            assertTrue(app.items[0].quality <= 50);
        }

    }

    @Test
    void testSulfurasDoesntChange()
    {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 2, 8) };
        GildedRose app = new GildedRose(items);

        String prevString = app.items[0].toString();
        for (int i = 0; i < 50; i++) {
            app.updateQuality();
            assertTrue(prevString.equals(app.items[0].toString()));
        }
    }


    @Test
    void testBackstagePasses ()
    {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 8) };
        GildedRose app = new GildedRose(items);

        int prevQual = app.items[0].quality;
        for (int i = 0; i < 50; i++) {
            app.updateQuality();
            if (app.items[0].sellIn < 0)
            {
                assertTrue(items[0].quality == 0);
            }
            else if(app.items[0].sellIn < 5)
            {
                assertTrue(items[0].quality == prevQual + 3);
            }
            else if(app.items[0].sellIn < 10)
            {
                assertTrue(items[0].quality == prevQual + 2);
            }

            prevQual = app.items[0].quality;
        }
    }

    @Test
    void testConjuredItems()
    {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
        GildedRose app = new GildedRose(items);

        int prevQual = app.items[0].quality;
        for (int i = 0; app.items[0].quality > 0; i++) {
            app.updateQuality();
            assertTrue(app.items[0].quality == prevQual-2);
            System.out.println(app.items[0]);

            prevQual = app.items[0].quality;
        }

    }

}
