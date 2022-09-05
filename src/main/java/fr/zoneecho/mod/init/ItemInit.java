package fr.zoneecho.mod.init;

import fr.zoneecho.mod.objects.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static List<Item> ITEMS = new ArrayList<Item>();
    public List<String> staffProtected = new ArrayList<>();


    // OTHER
    public static final Item LOGO = new ItemHRP("logo");
    public static final Item DEBUG_TOOL = new ItemStaff("debug_tool");
    public static final Item WRENCH = new ItemWrench("wrench");
    public static final Item UNIT_STORAGE_BUS = new ItemStorageUnit("storage_unit");

    // KEYCARDS

    public static final Item KEYCARD0 = new ItemBase("keycard0");
    public static final Item KEYCARD1 = new ItemBase("keycard1");
    public static final Item KEYCARD2 = new ItemBase("keycard2");
    public static final Item KEYCARD3 = new ItemBase("keycard3");
    public static final Item KEYCARD4 = new ItemBase("keycard4");
    public static final Item KEYCARD5 = new ItemBase("keycard5");


    // HACK TOOL
    public static final Item HACK_TOOL = new ItemBase("hack");
    public static final Item RADIOCOMPONANT = new ItemBase("plaqueradio"); // Carte mère de radio
    public static final Item COMPONANTBASE = new ItemBase("componant"); // Composant de base
    public static final Item COMPONANTPLUG = new ItemBase("plugmodule"); // Composant de connectable


}
