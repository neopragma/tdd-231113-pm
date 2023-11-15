package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }
            if (isNormalItem(item)) {
                changeQualityValue(-1, item);
            } else if (item.name.equals("Conjured Mana Cake")) {
                changeQualityValue(-2, item);
            } else {
                changeQualityValue(1, item);
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        changeQualityValue(1, item);
                    }
                    if (item.sellIn < 6) {
                        changeQualityValue(1, item);
                    }
                }
            }
            item.sellIn--;

            if (item.sellIn < 0) {
                if (isNormalItem(item)) {
                    changeQualityValue(-1, item);
                } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else {
                    changeQualityValue(1, item);
                }
            }
        }
    }

    private boolean isNormalItem(Item item) {
        return ((!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
                && (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                && (!item.name.equals("Conjured Mana Cake")));

    }

    private void changeQualityValue(int amount, Item item) {
        item.quality += amount;
        if (amount < 0 && item.quality < 0) {
            item.quality = 0;
        }
        if (amount > 0 && item.quality > 50) {
            item.quality = 50;
        }
    }
}