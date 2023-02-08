package fr.zoneecho.mod.server.utils;

import fr.zoneecho.mod.Ref;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class InventorySaver {
    private Map<Integer, ItemStack> inventory = new HashMap<>();

    public InventorySaver(Map<Integer, ItemStack> inventory) {
    }


    public InventorySaver() {
    }


    public void addItem(Integer index, ItemStack item) {
        System.out.println("Adding item " + item + " at index " + index);
        inventory.put(index, item);
    }

    public void removeItem(Integer index) {
        inventory.remove(index);
    }

    public void setItem(Integer index, ItemStack item) {
        System.out.println("Adding item " + item + " at index " + index);
        inventory.replace(index, item);
    }

    public ItemStack getItem(Integer index) {
        return inventory.get(index);
    }

    public NBTTagCompound transformToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        for (Map.Entry<Integer, ItemStack> entry : inventory.entrySet()) {
            nbt.setTag(entry.getKey().toString(), entry.getValue().serializeNBT());
        }
        return nbt;
    }

    public static InventorySaver unseralizeFromNBT(NBTTagCompound nbt) {
        Map<Integer, ItemStack> inventory = new HashMap<>();
        for (int i = 0; i < Ref.MAX_INVENTORY_SIZE; i++) {
            inventory.put(i, new ItemStack(nbt.getCompoundTag(i + "")));
        }
        return new InventorySaver(inventory);
    }

    @Override
    public String toString() {
        return "InventorySaver{" +
                "inventory=" + inventory +
        '}';
    }
}
