package mixins;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

public class LoadingScreenModifier implements IClassTransformer {
    public static boolean runtimeDeobfuscationEnabled;

    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("net.minecraftforge.fml.client.SplashProgress")) {
            System.out.println("Lets do it !");
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept((ClassVisitor)classNode, 8);
            MethodNode mnode = null;
            for (MethodNode node : classNode.methods) {
                if (node.name.equals("start")) {
                    mnode = node;
                    break;
                }
            }
            if (mnode == null) {
                System.out.println("Cannot find start.");
                return basicClass;
            }
            InsnList instr = mnode.instructions;
            instr.insert((AbstractInsnNode)new InsnNode(177));
            instr.insert((AbstractInsnNode)new MethodInsnNode(184, "be/fa/simplylife/client/loadingscreen/SplashProgressCustomized", "start", "()V", false));
            mnode = null;
            for (MethodNode node : classNode.methods) {
                if (node.name.equals("finish")) {
                    mnode = node;
                    break;
                }
            }
            if (mnode == null) {
                System.out.println("Cannot find finish.");
                return basicClass;
            }
            instr = mnode.instructions;
            instr.insert((AbstractInsnNode)new InsnNode(177));
            instr.insert((AbstractInsnNode)new MethodInsnNode(184, "be/fa/simplylife/client/loadingscreen/SplashProgressCustomized", "finish", "()V", false));
            mnode = null;
            for (MethodNode node : classNode.methods) {
                if (node.name.equals("pause")) {
                    mnode = node;
                    break;
                }
            }
            if (mnode == null) {
                System.out.println("Cannot found pause.");
                return basicClass;
            }
            instr = mnode.instructions;
            instr.insert((AbstractInsnNode)new InsnNode(177));
            instr.insert((AbstractInsnNode)new MethodInsnNode(184, "be/fa/simplylife/client/loadingscreen/SplashProgressCustomized", "pause", "()V", false));
            mnode = null;
            for (MethodNode node : classNode.methods) {
                if (node.name.equals("resume")) {
                    mnode = node;
                    break;
                }
            }
            if (mnode == null) {
                System.out.println("Cannot find resume");
                return basicClass;
            }
            instr = mnode.instructions;
            instr.insert((AbstractInsnNode)new InsnNode(177));
            instr.insert((AbstractInsnNode)new MethodInsnNode(184, "be/fa/simplylife/client/loadingscreen/SplashProgressCustomized", "resume", "()V", false));
            ClassWriter cw = new ClassWriter(3);
            classNode.accept(cw);
            System.out.println("Should be OK. ");
            return cw.toByteArray();
        }
        return basicClass;
    }
}
