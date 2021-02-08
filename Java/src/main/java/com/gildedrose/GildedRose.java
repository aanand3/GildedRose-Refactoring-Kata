package com.gildedrose;

class GildedRose {
    Item[] items; // dont change

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality()
    {
        for (int i = 0; i < items.length; i++)
        {
            // if it's not Brie or backstage passes
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert"))
            {
                if (items[i].quality > 0) // still has some quality
                {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros"))
                    { // regular degradation for every item
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else // if it IS brie or backstage passes
                {
                if (items[i].quality < 50)
                {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (items[i].sellIn < 11)
                        {
                            if (items[i].quality < 50)
                            {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6)
                        {
                            if (items[i].quality < 50)
                            {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros"))
            {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0 || items[i].name.equals("Conjured Mana Cake")) // its past the sellby date OR its Conjured Mana Cake
            {
                if (!items[i].name.equals("Aged Brie")) // not aged brie
                { // and NOT backstage passess
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (items[i].quality > 0)
                        { // and not Sulfuras
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros"))
                            { // then it'll degrade by 1 AGAIN
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else // if it IS backstage passes, go to 0
                        {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                }
                else // if IT IS aged brie
                    {
                        if (items[i].quality < 50)
                        {
                            items[i].quality = items[i].quality + 1;
                        }
                    }
            }
        }
    }
}