package fr.zoneecho.mod.common.tiles;

import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.zoneecho.mod.common.blocks.tiles.TileEntitySyncClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TETVSign extends TileEntitySyncClient implements ITickable {

    private BlockObject b;

    private static String text="test";
    private static String color="black";

    public TETVSign(){
        super(null);
    }

    public TETVSign(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
        this.b = blockObjectInfo;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        text = tagCompound.getString("text");
        color = tagCompound.getString("color");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setString("text",text);
        tagCompound.setString("color",color);
        return tagCompound;
    }

    public void setText(String text) {
        TETVSign.text = text;
        sync();
        markDirty();
    }

    public String getText() {
        return this.text;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        TETVSign.color = color;
        sync();
        markDirty();
    }

    @Override
    public void update() {
    }
}
