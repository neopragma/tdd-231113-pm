package com.gildedrose;

import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void Sulfuras_maintains_quality_of_80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForQualityCheck")
    void quality_decreases_by_1_for_normal_items(Item[] items, int expectedQuality) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedQuality, app.items[0].quality);
    }

    private static Stream<Arguments> provideValuesForQualityCheck() {
        return Stream.of(
                Arguments.of(new Item[] { new Item("itemname", 5, 0)}, 0),
                Arguments.of(new Item[] { new Item("itemname", 5, 6)}, 5),
                Arguments.of(new Item[] { new Item("itemname", 5, 50)}, 49),
                Arguments.of(new Item[] { new Item("itemname", -1, 4)}, 2),
                Arguments.of(new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50)}, 50),
                Arguments.of(new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)}, 50),
                Arguments.of(new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)}, 12),
                Arguments.of(new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)}, 13),
                Arguments.of(new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10)}, 0),
                Arguments.of(new Item[] { new Item("Aged Brie", -1, 10)}, 12),
                Arguments.of(new Item[] { new Item("Aged Brie", -1, 50)}, 50),
                Arguments.of(new Item[] { new Item("Conjured Mana Cake", 5, 8)}, 6),
                Arguments.of(new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) }, 80)
        );

    }


}
