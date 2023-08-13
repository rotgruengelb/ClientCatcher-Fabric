package net.rotgruengelb.clientcatcher.util;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.command.ServerCommandSource;

import java.util.function.Predicate;

import static net.rotgruengelb.clientcatcher.ClientCatcher.MODID;

public class Util {

    public static Predicate<ServerCommandSource> combinedPermissionCheck(String type) {
        return source -> {
            int functionPermissionLevel = source.getServer().getFunctionPermissionLevel();
            return source.hasPermissionLevel(functionPermissionLevel) || source.hasPermissionLevel(functionPermissionLevel);
        };
    }
}