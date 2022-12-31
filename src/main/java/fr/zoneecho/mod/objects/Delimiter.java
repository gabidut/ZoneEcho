package fr.zoneecho.mod.objects;

import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class Delimiter {
    private List<AxisAlignedBB> delimiter;

    public Delimiter(List<AxisAlignedBB> delimiter) {
        this.delimiter = delimiter;
    }

    public List<AxisAlignedBB> getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(List<AxisAlignedBB> delimiter) {
        this.delimiter = delimiter;
    }

    public void addDelimiter(AxisAlignedBB delimiter) {
        this.delimiter.add(delimiter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AxisAlignedBB axisAlignedBB : delimiter) {
            sb.append(axisAlignedBB.minX).append("$").append(axisAlignedBB.minY).append("$").append(axisAlignedBB.minZ).append("$").append(axisAlignedBB.maxX).append("$").append(axisAlignedBB.maxY).append("$").append(axisAlignedBB.maxZ).append("$");
        }
        return sb.toString();
    }

    public static Delimiter fromString(String s) {
        String[] split = s.split("\\$");
        List<AxisAlignedBB> del = new java.util.ArrayList<>();
        for (int i = 0; i < split.length; i+=6) {
            del.add(new AxisAlignedBB(Double.parseDouble(split[i]), Double.parseDouble(split[i+1]), Double.parseDouble(split[i+2]), Double.parseDouble(split[i+3]), Double.parseDouble(split[i+4]), Double.parseDouble(split[i+5])));
        }
        return new Delimiter(del);
    }
}
