package me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.customRecipe;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.customRecipe.anvil.QuickPickupInstrumentRecipe;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

public class Recipe implements CommandExecutor, Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);
    List<String> recipeNames = new ArrayList<>();

    static String QuickPickupInstrumentName = Message.getColorText("&6&l快速拾取儀 &7✪");

    private static String mainTitle = Message.getColorText("&d特殊合成表");

    public Recipe() {
        Bukkit.getPluginCommand("recipes").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);

        recipeNames.add(mainTitle);
        recipeNames.add(QuickPickupInstrumentRecipe.title);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        player.openInventory(mainGUI(player));

        return false;
    }

    public static Inventory mainGUI(Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, mainTitle);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta backgroundMeta = background.getItemMeta();
                backgroundMeta.setDisplayName(" ");
                background.setItemMeta(backgroundMeta);
                inv.setItem(i, background);
            }
        }

        ItemStack QuickPickupInstrument = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta QuickPickupInstrumentMeta = QuickPickupInstrument.getItemMeta();
        List<String> QuickPickupInstrumentLore = new ArrayList<>();
        QuickPickupInstrumentMeta.setDisplayName(QuickPickupInstrumentName);
        QuickPickupInstrumentMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        QuickPickupInstrumentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        QuickPickupInstrumentLore.add("");
        QuickPickupInstrumentLore.add(Message.getColorText("&e點擊查看"));
        QuickPickupInstrumentMeta.setLore(QuickPickupInstrumentLore);
        QuickPickupInstrument.setItemMeta(QuickPickupInstrumentMeta);

        inv.setItem(22, QuickPickupInstrument);

        return inv;
    }

    public static ItemStack getBackItem() {
        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(Message.getColorText("&d返回"));
        back.setItemMeta(backMeta);

        return back;
    }

    public static ItemStack getCloseItem() {
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(Message.getColorText("&c關閉"));
        close.setItemMeta(closeMeta);

        return close;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getCurrentItem() == null)
            return;
        if (!(recipeNames.contains(event.getView().getTitle())))
            return;

        Player player = (Player) event.getWhoClicked();
        String clickItem = event.getCurrentItem().getItemMeta().getDisplayName();

        if (clickItem.equals(Message.getColorText("&d返回"))) {
            player.updateInventory();
            player.openInventory(mainGUI(player));
        }
        if (clickItem.equals(Message.getColorText("&c關閉"))) {
            player.updateInventory();
            player.closeInventory();
        }
        if (clickItem.equals(QuickPickupInstrumentName)) {
            player.updateInventory();
            player.openInventory(QuickPickupInstrumentRecipe.getRecipe(player));
        }

        event.setCancelled(true);

    }

    /*
    10, 11, 12
    19, 20, 21 » 23 » 25
    28, 29, 30
    */

}
