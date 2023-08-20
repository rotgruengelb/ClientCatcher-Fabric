package net.rotgruengelb.clientcatcher;

import com.mojang.authlib.GameProfile;

public class Api {
    public static String getPlayerBrand(GameProfile gameProfile) {
        String brand = ClientCatcher.clientBrands.get(gameProfile.getId());
        if (brand == null) brand = "[brand not found]";
        return brand;
    }
}
