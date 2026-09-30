package com.opticores.lib.client.gpu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.ShaderOptions;

public class Environment {
    public static boolean isSodium() {
        return net.minecraft.client.renderer.ShaderProgram.class.getName().contains("sodium");
    }

    public static boolean isIris() {
        return net.minecraft.client.renderer.ShaderProgram.class.getName().contains("iris");
    }

    public static boolean isShaderActive() {
        Minecraft mc = Minecraft.getInstance();
        return mc.options.getShaderPack() != null && !mc.options.getShaderPack().equals("off");
    }

    public static boolean isVanilla() {
        return !isSodium() && !isIris();
    }
}
