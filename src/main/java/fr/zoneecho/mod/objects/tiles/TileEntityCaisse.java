package fr.zoneecho.mod.objects.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.Objects;

public class TileEntityCaisse extends TileEntity implements IInventory {
    private ItemStack[] contents = new ItemStack[27];
    private String customName;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound); // exécute ce qui se trouve dans la fonction readFromNBT de la classe mère (lecture de la position du tile entity)
        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) // si un tag custom name de type string existe
        {
            this.customName = compound.getString("CustomName"); // on le lit
        }

        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nommée Items
        this.contents = new ItemStack[this.getSizeInventory()]; // on réinitialise le tableau
        for(int i = 0; i < nbttaglist.tagCount(); ++i) // i varie de 0 à la taille la liste
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i); // on lit le tag nbt
            int j = nbttagcompound1.getByte("Slot") & 255; // on lit à quel slot se trouve l'item stack

            if(j >= 0 && j < this.contents.length)
            {
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound); // exécute se qui se trouve dans la fonction writeToNBT de la classe mère (écriture de la position du tile entity)
        if(!Objects.equals(this.customName, "")) // s'il y a un nom custom
        {
            compound.setString("CustomName", this.customName); // on le met dans le tag nbt
        }

        NBTTagList nbttaglist = new NBTTagList(); // on créé une nouvelle liste de tags
        for(int i = 0; i < this.contents.length; ++i) // i varie de 0 à la taille de notre tableau
        {
            if(this.contents[ i] != null) // si l'item stack à l'emplacement i du tableau n'est pas null
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on créé un tag nbt
                nbttagcompound1.setByte("Slot", (byte)i); // on enregistre son emplacement dans le tableau
                this.contents[ i].writeToNBT(nbttagcompound1); // on écrit l'item dans le tag
                nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab à la liste
            }
        }
        compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt
        return compound;
    }

    @Override
    public int getSizeInventory()
    {
        return this.contents.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.contents[slotIndex];
    }
    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if(this.contents[slotIndex] != null) // si le contenu dans l'emplacement n'est pas null
        {
            ItemStack itemstack;

            if(this.contents[slotIndex].getCount() <= amount) // si la quantité est inférieur où égale à ce qu'on souhaite retirer
            {
                itemstack = this.contents[slotIndex]; // la variable itemstack prends la valeur du contenu
                this.contents[slotIndex] = null; // on retire ce qui est dans la variable contents
                this.markDirty(); // met à jour le tile entity
                return itemstack; // renvoie itemstack
            }
            else // sinon
            {
                itemstack = this.contents[slotIndex].splitStack(amount); // la fonction splitStack(quantité) retire dans this.contents[slotIndex] le contenu et le met dans itemstack

                if(this.contents[slotIndex].getCount() == 0) // au cas où la quantité passe à 0 (ce qui ne devrait pas arriver en temps normal)
                {
                    this.contents[slotIndex] = null; // on met sur null, ça évite de se retrouver avec des itemstack bugué qui contiennent 0
                }
                this.markDirty(); // met à jour le tile entity
                return itemstack; // renvoie itemstack
            }
        }
        else // sinon si le contenu dans cette emplacement est null
        {
            return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
