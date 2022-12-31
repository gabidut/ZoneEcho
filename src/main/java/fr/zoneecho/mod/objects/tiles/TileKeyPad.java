package fr.zoneecho.mod.objects.tiles;

import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.zoneecho.mod.init.tiles.TileEntitySyncClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileKeyPad extends TileEntitySyncClient implements ITickable {

    private BlockObject b;

    private static String code="";

    public TileKeyPad(){
        super(null);
    }

    public TileKeyPad(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
        this.b = blockObjectInfo;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        code = tagCompound.getString("code");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setString("code",code);
        return tagCompound;
    }

    public void setCode(String code) {
        TileKeyPad.code = code;
    }

    public static String getCode() {
        return code;
    }

    @Override
    public void update() {
        System.out.println(getCode());
    }
}
