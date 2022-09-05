package fr.zoneecho.mod.mixin;

import com.jme3.bullet.collision.PhysicsRayTestResult;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Vector3f;
import fr.dynamx.api.physics.BulletShapeType;
import fr.dynamx.api.physics.EnumBulletShapeType;
import fr.dynamx.api.physics.IPhysicsWorld;
import fr.dynamx.common.DynamXContext;
import fr.dynamx.utils.maths.DynamXMath;
import fr.dynamx.utils.optimization.Vector3fPool;
import fr.dynamx.utils.physics.DynamXPhysicsHelper;
import fr.dynamx.utils.physics.PhysicsRaycastResult;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(DynamXPhysicsHelper.class)
public abstract class MixinDynamXPhysicsHelper {
    @Shadow
    private static Logger LOGGER;

    @Overwrite
    public static PhysicsRaycastResult castRay(Vector3f from, Vector3f dir, Predicate<EnumBulletShapeType> ignoredBody) {
        System.out.println("hellooooo");
        Vector3fPool.openPool();
        List<PhysicsRayTestResult> results = new LinkedList<>();
        IPhysicsWorld iPhysicsWorld = DynamXContext.getPhysicsWorld();

        if(iPhysicsWorld != null) {
            iPhysicsWorld.getDynamicsWorld().rayTest(from, dir, results);
        } else {
            LOGGER.warn("Couldn't get physics world");
        }


        for (PhysicsRayTestResult result : results) {

            if (!(result.getCollisionObject() instanceof PhysicsRigidBody))
                continue;
            if(!ignoredBody.test(((BulletShapeType<?>) result.getCollisionObject().getUserObject()).getType()))
                continue;

            Vector3f hitPosition = Vector3fPool.get();
            DynamXMath.interpolateLinear(result.getHitFraction(), from, dir, hitPosition);

            float distance = result.getHitFraction() * dir.length();

            Vector3f hitNormalInWorld = Vector3fPool.get();
            result.getHitNormalLocal(hitNormalInWorld);

            PhysicsRigidBody hitBody = (PhysicsRigidBody) result.getCollisionObject();

            return new PhysicsRaycastResult(from, dir, hitPosition, distance, hitNormalInWorld, hitBody);
        }
        Vector3fPool.closePool();

        return null;
    }
}
