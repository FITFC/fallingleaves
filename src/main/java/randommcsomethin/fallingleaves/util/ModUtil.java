package randommcsomethin.fallingleaves.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ModUtil {

    @Nullable
    public static String getModName(Block block) {
        if (block.equals(Blocks.AIR)) {
            return "Minecraft";
        }

        Identifier blockId = Registries.BLOCK.getId(block);
        if (blockId.equals(Registries.BLOCK.getDefaultId())) {
            return null;
        }

        return getModName(blockId.getNamespace());
    }

    @Nullable
    public static String getModName(String modId) {
        return FabricLoader.getInstance()
            .getAllMods()
            .stream()
            .map(ModContainer::getMetadata)
            .filter(m -> m.getId().equals(modId))
            .findFirst()
            .map(ModMetadata::getName)
            .orElse(null);
    }

}