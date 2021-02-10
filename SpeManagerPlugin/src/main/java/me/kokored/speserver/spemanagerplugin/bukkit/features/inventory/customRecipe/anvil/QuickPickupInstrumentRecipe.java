package me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.customRecipe.anvil;

import me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.customRecipe.Recipe;
import me.kokored.speserver.spemanagerplugin.bukkit.features.item.custom.QuickPickupInstrument;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuickPickupInstrumentRecipe {

    public static String title = Message.getColorText("&6合成表 &7» ") + QuickPickupInstrument.getItem().getItemMeta().getDisplayName();

    public static Inventory getRecipe(Player player) {
        Inventory recipe = Bukkit.createInventory(player, 54, title);

        for (int i = 0; i < recipe.getSize(); i++) {
            if (recipe.getItem(i) == null) {
                ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta backgroundMeta = background.getItemMeta();
                backgroundMeta.setDisplayName(" ");
                background.setItemMeta(backgroundMeta);
                recipe.setItem(i, background);
            }
        }

        ItemStack A = new ItemStack(Material.DAYLIGHT_DETECTOR, 1);
        ItemStack B = new ItemStack(Material.HOPPER, 1);
        ItemStack C = new ItemStack(Material.DAYLIGHT_DETECTOR, 1);

        ItemStack D = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemStack E = new ItemStack(Material.ENDER_CHEST, 1);
        ItemStack F = new ItemStack(Material.REDSTONE_BLOCK, 1);

        ItemStack G = new ItemStack(Material.HOPPER, 1);
        ItemStack H = new ItemStack(Material.LEVER, 1);
        ItemStack I = new ItemStack(Material.HOPPER, 1);

        ItemStack InvType = new ItemStack(Material.CRAFTING_TABLE, 1);

        recipe.setItem(10, A);
        recipe.setItem(11, B);
        recipe.setItem(12, C);

        recipe.setItem(19, D);
        recipe.setItem(20, E);
        recipe.setItem(21, F);

        recipe.setItem(28, G);
        recipe.setItem(29, H);
        recipe.setItem(30, I);

        recipe.setItem(23, InvType);
        recipe.setItem(25, QuickPickupInstrument.getItem());

        recipe.setItem(48, Recipe.getBackItem());
        recipe.setItem(49, Recipe.getCloseItem());

        return recipe;
    }

}
