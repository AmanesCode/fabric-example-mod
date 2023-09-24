package com.example;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    // アイテムデータのリスト
    private static final List<ItemData> ITEM_DATA_LIST = new ArrayList<>();

    // アイテムデータのクラス
    public static class ItemData {
        public final String name;
        public final Item.Settings settings;
        public final String texturePath;

        public ItemData(String name, Item.Settings settings, String texturePath) {
            this.name = name;
            this.settings = settings;
            this.texturePath = texturePath;
        }
    }

    // アイテムデータを追加するメソッド
    public static void addItemData(ItemData itemData) {
        ITEM_DATA_LIST.add(itemData);
    }

    // アイテムデータを削除するメソッド
    public static void removeItemData(ItemData itemData) {
        ITEM_DATA_LIST.remove(itemData);
    }

    // アイテムデータのリストを取得するメソッド
    public static List<ItemData> getItemDataList() {
        return ITEM_DATA_LIST;
    }
}
