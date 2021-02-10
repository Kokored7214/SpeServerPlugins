package me.kokored.speserver.spemanagerplugin.bukkit.features.inventory;

import java.util.ArrayList;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AssembleMusterGUI implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    public static Inventory inventory;
    public ItemStack firstFusion = null;
    public ItemStack secondFusion = null;
    public ItemStack resultFusion = null;

    public AssembleMusterGUI() {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        inventory = Bukkit.createInventory(null, 54, Message.getColorText("&6&l組裝大師 &d&lAssemble Muster"));

        setupInv();

    }

    private void openAssembleInv(ItemStack first, ItemStack second, ItemStack results, Boolean pass) {
        firstFusion = first;
        secondFusion = second;
        resultFusion = results;
    }

    private void setItem() {

    }

    private void setCanFusionBar(Boolean canFusion) {
        if (canFusion == true) {
            ItemStack bar = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            ItemMeta barMeta = bar.getItemMeta();
            barMeta.setDisplayName(Message.getColorText("&a可被組裝"));
            bar.setItemMeta(barMeta);
            inventory.setItem(45, bar);
            inventory.setItem(46, bar);
            inventory.setItem(47, bar);
            inventory.setItem(48, bar);
            inventory.setItem(49, bar);
            inventory.setItem(50, bar);
            inventory.setItem(51, bar);
            inventory.setItem(52, bar);
            inventory.setItem(53, bar);
        }else if (canFusion == false) {
            ItemStack bar = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta barMeta = bar.getItemMeta();
            barMeta.setDisplayName(Message.getColorText("&c不可被組裝"));
            bar.setItemMeta(barMeta);
            inventory.setItem(45, bar);
            inventory.setItem(46, bar);
            inventory.setItem(47, bar);
            inventory.setItem(48, bar);
            inventory.setItem(49, bar);
            inventory.setItem(50, bar);
            inventory.setItem(51, bar);
            inventory.setItem(52, bar);
            inventory.setItem(53, bar);
        }
    }

    private void setupInv() {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta backgroundMeta = background.getItemMeta();
                backgroundMeta.setDisplayName(" ");
                background.setItemMeta(backgroundMeta);
                inventory.setItem(i, background);
            }
        }
        ItemStack line = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE, 1);
        ItemMeta lineMeta = line.getItemMeta();
        lineMeta.setDisplayName(" ");
        line.setItemMeta(lineMeta);
        inventory.setItem(10, line);
        inventory.setItem(11, line);
        inventory.setItem(12, line);
        inventory.setItem(14, line);
        inventory.setItem(15, line);
        inventory.setItem(16, line);
        inventory.setItem(19, line);
        inventory.setItem(25, line);

        ItemStack line1 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
        ItemMeta line1Meta = line.getItemMeta();
        line1Meta.setDisplayName(" ");
        line1.setItemMeta(line1Meta);
        inventory.setItem(22, line1);

        ItemStack air = new ItemStack(Material.AIR, 1);
        inventory.setItem(28, air);
        inventory.setItem(31, air);
        inventory.setItem(34, air);

        ItemStack midItem = new ItemStack(Material.BLAST_FURNACE, 1);
        ItemMeta midItemMeta = midItem.getItemMeta();
        ArrayList<String> midItemLore = new ArrayList<>();
        midItemMeta.setDisplayName(Message.getColorText("&e組裝物品"));
        midItemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        midItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        midItemLore.add(Message.getColorText("&7將需要拼裝的物品放入&6橘色&7傳輸鏈尾端空位"));
        midItemLore.add(Message.getColorText("&7即可在&e黃色&7傳輸鏈尾端見到物品預覽"));
        midItemLore.add(Message.getColorText("&7等待下方指示條變成&a綠色&7後即可點&6我&7進行組裝。"));
        midItemMeta.setLore(midItemLore);
        midItem.setItemMeta(midItemMeta);
        inventory.setItem(13, midItem);
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryView inv = event.getView();
        ItemStack item = event.getCurrentItem();

        if (inv.getTitle().equals(Message.getColorText("&6&l組裝大師 &d&lAssemble Muster"))) {
            try {

                firstFusion = event.getInventory().getItem(28);
                secondFusion = event.getInventory().getItem(34);

                player.sendMessage(firstFusion.getType().toString());
                player.sendMessage(secondFusion.getType().toString());
                setCanFusionBar(true);
                player.updateInventory();
            }catch (Exception e) { }

            if (item.hasItemMeta()) {
                event.setCancelled(true);
            }
        }
    }

}
