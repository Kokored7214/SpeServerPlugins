package me.kokored.speserver.spemanagerplugin.bukkit.features.item.custom;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.api.custom.Feature;
import me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.CustomEnchants;
import me.kokored.speserver.spemanagerplugin.bukkit.features.item.CustomItems;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class QuickPickupInstrument implements Listener {

    static Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    public QuickPickupInstrument() {
        if (!Feature.checkFeature(Feature.QUICK_PICKUP_INSTRUMENT()))
            return;

        Bukkit.getPluginManager().registerEvents(this, plugin);

        addRecipe();
    }

    public void addRecipe() {
        NamespacedKey key = new NamespacedKey(plugin, "smp_item_quick_pickup_instrument");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());
        recipe.shape("DHD", "RER", "HLH");
        recipe.setIngredient('D', Material.DAYLIGHT_DETECTOR);
        recipe.setIngredient('H', Material.HOPPER);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('E', Material.ENDER_CHEST);
        recipe.setIngredient('L', Material.LEVER);
        Bukkit.addRecipe(recipe);
        CustomItems.recipes.add(key);
    }

    public static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName(Message.getColorText("&6&l快速拾取儀 &7✪"));
        itemMeta.addEnchant(CustomEnchants.TELEPATHY, 1, true);
        itemLore.add("");
        itemLore.add(Message.getColorText("&d在挖掘方塊時, 該方塊不會掉落在地上,"));
        itemLore.add(Message.getColorText("&d而是直接進入玩家的背包。"));
        itemLore.add("");
        itemLore.add(Message.getColorText("&9需求:"));
        itemLore.add(Message.getColorText("&b • &7&l石稿&9或以上的工具&8(目前只限稿子)"));
        itemLore.add("");
        itemLore.add(Message.getColorText("&8使用&7&l鐵砧&8合并在稿子上"));

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (testItem(player.getInventory().getItemInMainHand())) {
            event.setCancelled(true);
        }
        if (testItem(player.getInventory().getItemInOffHand())) {
            event.setCancelled(true);
        }
    }

    public static Boolean testItem(ItemStack itemStack) {
        if (itemStack.equals(getItem())) {
            return true;
        }
        if (itemStack.getItemMeta() != null) {
            if (itemStack.getItemMeta().getDisplayName().equals(getItem().getItemMeta().getDisplayName())
                    && itemStack.getItemMeta().getLore().equals(getItem().getItemMeta().getLore())) {
                return true;
            }
        }
        return false;
    }

}
