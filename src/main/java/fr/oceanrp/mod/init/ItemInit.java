package fr.oceanrp.mod.init;

import fr.oceanrp.mod.objects.items.ItemBase;
import fr.oceanrp.mod.objects.items.ItemDrink;
import fr.oceanrp.mod.objects.items.ItemIdentity;
import fr.oceanrp.mod.objects.items.ItemNourriture;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static List<Item> ITEMS = new ArrayList<Item>();

    //Autres
    public static final Item Logo = new ItemBase("logo");
    public static final Item Logogn = new ItemBase("Logogn");
    public static final Item Logosp = new ItemBase("Logosp");
    public static final Item TabletteVoiture = new ItemBase("tablettevoiture");

    //Nourritures & Plats

    //Aliments



    //Boisson
    // public static final Item BouteilleEau = new ItemDrink("bouteilleeau");

    // Identity
    public static final Item IdentityCard = new ItemIdentity("identitycard");
}
