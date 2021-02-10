package me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.switchGui;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.features.DeathExp;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Page1 implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    Inventory inv;

    //GUI
    private final String invTitle = Message.getColorText("&d游戲玩法類");
    //Item
    private final String ItemDeathExp = Message.getColorText("&c死亡&d處罰");

    public Page1() {

        Bukkit.getPluginManager().registerEvents(this, plugin);

        inv = Bukkit.createInventory(null, 36, invTitle);

        initItem();

    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(invTitle)) {

            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            if (itemName.equals(ItemDeathExp)) {
                player.updateInventory();
                player.openInventory(DeathExp.getSwitchGUI(player));
                Sound.playGUIClickSound(player);
            }

            event.setCancelled(true);

        }

    }

    private void initItem() {

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                ItemStack air = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta itemMeta = air.getItemMeta();
                itemMeta.setDisplayName(" ");
                air.setItemMeta(itemMeta);

                inv.setItem(i, air);
            }
        }

        HeaderBar.addToInv(inv, 1);

        //死亡經驗處罰
        ItemStack deathExp = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta deathExpMeta = deathExp.getItemMeta();
        List<String> deathExpLore = new ArrayList<>();
        deathExpMeta.setDisplayName(ItemDeathExp);
        deathExpLore.add("");
        deathExpLore.add(Message.getColorText("&b這裏可以選擇死亡處罰的首選處罰"));
        deathExpLore.add(Message.getColorText("&b及通知開關"));
        deathExpLore.add("");
        deathExpLore.add(Message.getColorText("&e點擊開啓"));
        deathExpMeta.setLore(deathExpLore);
        deathExp.setItemMeta(deathExpMeta);

        inv.setItem(22, deathExp);

    }

    public Inventory getPage1() {
        return inv;
    }

}
