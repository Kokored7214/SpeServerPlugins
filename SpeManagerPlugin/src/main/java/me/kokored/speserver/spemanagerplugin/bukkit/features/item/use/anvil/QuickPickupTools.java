package me.kokored.speserver.spemanagerplugin.bukkit.features.item.use.anvil;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.CustomEnchants;
import me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.custom.Telepathy;
import me.kokored.speserver.spemanagerplugin.bukkit.features.item.custom.QuickPickupInstrument;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class QuickPickupTools implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    public QuickPickupTools() {
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryType invType = event.getInventory().getType();

        if (invType == InventoryType.ANVIL) {
            AnvilInventory anvilInv = (AnvilInventory) event.getInventory();
            if (anvilInv.getItem(0) == null || anvilInv.getItem(1) == null || anvilInv.getItem(2) == null)
                return;
            if (!(anvilInv.getItem(2) == QuickPickupInstrument.getItem() || !(anvilInv.getItem(2).equals(QuickPickupInstrument.getItem()))))
                return;
            /*
            if (!(event.getSlot() == 2))
                return;

            ItemStack item = anvilInv.getItem(2);

             */

            /*
            player.getInventory().addItem(item);
            player.setLevel(player.getLevel() - 10);
            anvilInv.setItem(0, null);
            anvilInv.setItem(1, null);
            anvilInv.setItem(2, null);
            */

            if (event.getSlot() == 2) {
                ItemStack item = anvilInv.getItem(2);

                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {

                        if (anvilInv.getItem(0) != null && anvilInv.getItem(1) != null) {
                            player.getInventory().addItem(item);

                            anvilInv.setItem(0, null);
                            anvilInv.setItem(1, null);
                            anvilInv.setItem(2, null);

                            player.sendMessage(" ");
                            player.sendMessage(Message.getColorText("&f[&c系統&f] &7» &b因配合系統特殊物品原因, 系統已自動將合并後的物品放入你了你物品欄。"));
                            player.sendMessage(" ");
                        }

                    }
                }, 2);
            }

            return;
        }
        if (invType == InventoryType.GRINDSTONE) {
            GrindstoneInventory grindstoneInv = (GrindstoneInventory) event.getInventory();
            grindstoneInv.setItem(2, getGrindstoneResultItem(grindstoneInv.getItem(0), grindstoneInv.getItem(1)));
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    grindstoneInv.setItem(2, getGrindstoneResultItem(grindstoneInv.getItem(0), grindstoneInv.getItem(1)));
                }
            }, 1);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    grindstoneInv.setItem(2, getGrindstoneResultItem(grindstoneInv.getItem(0), grindstoneInv.getItem(1)));
                }
            }, 2);
        }
    }

    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent event) {
        ItemStack firstItem = event.getInventory().getItem(0);
        ItemStack secondItem = event.getInventory().getItem(1);
        if (firstItem == null)
             return;
        if (secondItem == null)
            return;
        if (firstItem.getType() == Material.AIR && secondItem.getType() == Material.AIR)
            return;
        if (firstItem.getItemMeta() == null && secondItem.getItemMeta() == null)
            return;
        if (!(testItem(firstItem.getType())))
            return;
        if (secondItem.getAmount() != 1)
            return;
        if (!(QuickPickupInstrument.testItem(secondItem)))
            return;
        if (Telepathy.testEnchant(firstItem))
            return;

        event.getInventory().setRepairCost(10);
        event.setResult(getAnvilResultItem(firstItem));
    }

    private ItemStack getAnvilResultItem(ItemStack item) {
        Material itemType = item.getType();
        ItemMeta itemMeta = item.getItemMeta();

        ItemStack recipe = new ItemStack(itemType, 1);
        ItemMeta recipeMeta = itemMeta;
        List<String> recipeLore = new ArrayList<>();

        recipeMeta.addEnchant(CustomEnchants.TELEPATHY, 1, true);
        if (itemMeta.hasLore()) {
            for (String lore : itemMeta.getLore()) {
                recipeLore.add(lore);
            }
        }
        recipeLore.add(ChatColor.AQUA + "特殊: " + ChatColor.GRAY + "快速拾取");

        recipeMeta.setLore(recipeLore);
        recipe.setItemMeta(recipeMeta);

        return recipe;
    }

    private ItemStack getGrindstoneResultItem(ItemStack firstItem, ItemStack secItem) {
        if (firstItem != null) {
            ItemStack recipe = new ItemStack(firstItem.getType(), 1);
            ItemMeta recipeMeta = recipe.getItemMeta();
            if (recipeMeta.hasEnchant(CustomEnchants.TELEPATHY))
                recipeMeta.removeEnchant(CustomEnchants.TELEPATHY);
            recipeMeta.setLore(null);
            recipe.setItemMeta(recipeMeta);
            return recipe;
        }
        if (secItem != null) {
            ItemStack recipe = new ItemStack(secItem.getType(), 1);
            ItemMeta recipeMeta = recipe.getItemMeta();
            if (recipeMeta.hasEnchant(CustomEnchants.TELEPATHY))
                recipeMeta.removeEnchant(CustomEnchants.TELEPATHY);
            recipeMeta.setLore(null);
            recipe.setItemMeta(recipeMeta);
            return recipe;
        }

        return null;
    }

    public static Boolean testItem(Material m) {
        Material stone_Pickaxe = Material.STONE_PICKAXE;
        Material iron_pickaxe = Material.IRON_PICKAXE;
        Material golden_pickaxe = Material.GOLDEN_PICKAXE;
        Material diamond_pickaxe = Material.DIAMOND_PICKAXE;
        Material netherite_pickaxe = Material.NETHERITE_PICKAXE;

        if (m == stone_Pickaxe || m == iron_pickaxe
                || m == golden_pickaxe || m == diamond_pickaxe
                || m == netherite_pickaxe) {

            return true;
        }
        return false;
    }

}
