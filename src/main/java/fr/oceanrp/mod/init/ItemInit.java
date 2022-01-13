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
    public static final Item TabletteVoiture = new ItemBase("tablettevoiture");

    //Nourritures & Plats

    //Aliments
    public static final Item Champignon = new ItemNourriture("champignon");
    public static final Item ChampignonCoupe = new ItemNourriture("champignoncouper");
    public static final Item CremeFraiche = new ItemNourriture("cremefraiche");
    public static final Item DemiFromage = new ItemNourriture("demifromage");
    public static final Item FarineBle = new ItemNourriture("farineble");
    public static final Item Fromage = new ItemNourriture("fromage");
    public static final Item FromageChevre = new ItemNourriture("fromagechevre");
    public static final Item FromageRape = new ItemNourriture("fromagerape");
    public static final Item Haran = new ItemNourriture("haran");
    public static final Item HuileOlive = new ItemNourriture("huileolive");
    public static final Item Levure = new ItemNourriture("levure");
    public static final Item Mozzarella = new ItemNourriture("mozzarella");
    public static final Item MozzarellaCoupe = new ItemNourriture("mozzarellacoupe");
    public static final Item Oignon = new ItemNourriture("oignon");
    public static final Item OignonCoupe = new ItemNourriture("oignoncoupe");
    public static final Item Olive = new ItemNourriture("Olive");
    public static final Item Patate = new ItemNourriture("patate");
    public static final Item PatateCoupe = new ItemNourriture("patatecoupe");
    public static final Item Pate = new ItemNourriture("pate");
    public static final Item Poivron = new ItemNourriture("poivron");
    public static final Item Roblochon = new ItemNourriture("roblochon");
    public static final Item SauceTomate = new ItemNourriture("saucetomate");
    public static final Item Sel = new ItemNourriture("sel");
    public static final Item Steak = new ItemNourriture("steak");
    public static final Item SteakHache = new ItemNourriture("steakhache");
    public static final Item Thym = new ItemNourriture("thym");
    public static final Item Tomate = new ItemNourriture("tomate");

    //Boisson
    public static final Item BouteilleEau = new ItemDrink("bouteilleeau");

    // Identity
    public static final Item IdentityCard = new ItemIdentity("identitycard");
}
