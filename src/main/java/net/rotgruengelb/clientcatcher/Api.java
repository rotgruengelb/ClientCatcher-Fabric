package net.rotgruengelb.clientcatcher;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.CactusBlock;

public class Api {
    public static String getPlayerBrand(GameProfile gameProfile) {
        String brand = ClientCatcher.clientBrands.get(gameProfile.getId());
        if (brand == null) brand = "[brand not found]";
        return brand;
    }
}
