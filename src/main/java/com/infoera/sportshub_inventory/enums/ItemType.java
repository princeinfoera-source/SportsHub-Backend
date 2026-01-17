package com.infoera.sportshub_inventory.enums;

public enum ItemType {
    FINISH_PRODUCT("FinishProduct"),
    CONSUMABLE("Consumable"),
    RAW_MATERIAL("RawMaterial"),
    PACKING_MATERIAL("PackingMaterial"),
    SCRAP("Scrap"),
    SEMI_FINISH("SemiFinish"),
    TRADE_GOODS("TradeGoods"),
    BOM_BASED_PRODUCT("BOMBasedProduct"),
    IMPORTED_ASSET("ImportedAsset");

    private final String label;

    ItemType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}