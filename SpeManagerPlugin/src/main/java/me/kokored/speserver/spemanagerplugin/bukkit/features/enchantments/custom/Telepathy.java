package me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.custom;

import java.awt.Container;
import java.util.Collection;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.api.custom.Feature;
import me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.CustomEnchants;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Telepathy implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    public Telepathy() {
        if (!Feature.checkFeature(Feature.TELEPATHY()))
            return;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().equals(null))
            return;
        if (!(player.getInventory().getItemInMainHand().hasItemMeta()))
            return;
        if (!(testEnchant(player.getInventory().getItemInMainHand())))
            return;
        if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))
            return;
        if (player.getInventory().firstEmpty() == -1) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "你的背包已經沒有空間了..."));
            return;
        }
        if (event.getBlock().getState() instanceof Container)
            return;

        event.setDropItems(false);

        Collection<ItemStack> collection = event.getBlock().getDrops(player.getInventory().getItemInMainHand());
        if (!(collection.isEmpty())) {
            player.getInventory().addItem(collection.iterator().next());
        }
    }

    public static Boolean testEnchant(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            if (lore.contains(ChatColor.AQUA + "特殊: " + ChatColor.GRAY + "快速拾取")) {
                return true;
            }
        }
        if (item.getItemMeta().hasEnchant(CustomEnchants.TELEPATHY)) {
            return true;
        }
        return false;
    }

}
