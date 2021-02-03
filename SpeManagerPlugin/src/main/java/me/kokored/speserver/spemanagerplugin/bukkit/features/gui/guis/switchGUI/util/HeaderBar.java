package me.kokored.speserver.spemanagerplugin.bukkit.features.gui.guis.switchGUI.util;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.features.gui.guis.switchGUI.SwitchGUI;
import me.kokored.speserver.spemanagerplugin.bukkit.features.player.DeathExp;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class HeaderBar implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    private static String page1Name = Message.getColorText("&d游戲玩法類");

    public HeaderBar() {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(page1Name)) {

            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            if (itemName.equals(page1Name)) {
                player.updateInventory();
                player.closeInventory();
                player.openInventory(SwitchGUI.getPage(1));
                Sound.playGUIClickSound(player);
            }

            event.setCancelled(true);

        }

    }

    public static void addToInv(Inventory inventory, Integer currentPage) {

        ItemStack page1 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);

        ItemMeta page1Meta = page1.getItemMeta();
        List<String> page1Lore = new ArrayList<>();
        page1Meta.setDisplayName(page1Name);
        if (currentPage == 1) {
            page1Meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        }
        page1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        page1Lore.add("");
        page1Lore.add(Message.getColorText("&b本頁主要是游戲玩法類的功能"));
        page1Lore.add(Message.getColorText("&b及通知選擇。"));
        page1Lore.add("");
        if (currentPage != 1) {
            page1Lore.add(Message.getColorText("&e點擊開啓"));
        }else if (currentPage == 1) {
            page1Lore.add(Message.getColorText("&a已開啓"));
        }
        page1Meta.setLore(page1Lore);
        page1.setItemMeta(page1Meta);
        inventory.setItem(4, page1);

    }

}
