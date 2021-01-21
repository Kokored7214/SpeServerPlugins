package me.kokored.speserver.sperulesplugin.rulesManager.rulesItem;

import java.util.ArrayList;
import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import me.kokored.speserver.sperulesplugin.rulesManager.RulesUtil;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.ChatRulesGUIBuilder;
import me.kokored.speserver.sperulesplugin.sql.MySqlAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ChatRules implements Listener {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    public ChatRules() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&9&l無語伺服器 &6B章 &f- &6聊天規章"))) {


            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(colorText("&6B章 第二條"))
                    && event.getCurrentItem().getType().equals(Material.BOOK)
                    && event.getClick().isRightClick()) {
                player.closeInventory();

                player.openInventory(ChatRulesGUIBuilder.getChatRulesConfirmGUI(player));
            }

            event.setCancelled(true);

        }
        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6B章 &f- &6聊天規章 &b最終確認"))) {


            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(colorText("&a確認"))
                    && event.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {

                String name = player.getName();
                String uuid = player.getUniqueId().toString();

                if (MySqlAPI.chatRulesConfirmed(uuid) == false) {
                    plugin.getLogger().info(colorText("[MySQL] Player " + name + " Agreed Rules B - ChatRules"));
                    plugin.getLogger().info(colorText("[MySQL] Saving player " + name + "'s data..."));
                    MySqlAPI.setChatRulesData(uuid, name, RulesUtil.getDate(), true);
                    plugin.getLogger().info(colorText("[MySQL] Player " + name + "'s data is now saved."));

                    player.closeInventory();

                    player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);

                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                        }
                    }, 5);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                        }
                    }, 10);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                        }
                    }, 15);

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 1f);
                    player.sendMessage(colorText("&d恭喜你同意了 &6B章 &f- &6聊天規章"));
                    player.sendMessage(colorText("&b你可以使用指令 &b/chatrules &b再次打開 &6B章 &b來查看規章"));
                }

            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(colorText("&c取消"))
                    && event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                player.closeInventory();
            }

            event.setCancelled(true);

        }

        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6B章 &f- &6聊天規章"))) {

            event.setCancelled(true);

        }

    }

    public static ItemStack B() {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 &f- &6聊天規章"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add(colorText(""));
        itemLore.add(colorText("&6A章 &f- &6游玩規章 &f(&d/rules&f)"));
        itemLore.add(colorText("&6B章 &f- &6聊天規章 &f(&d/chatrules&f)"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack Agree() {
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&a同意規章"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&d如你同意本規章, 請&6右鍵點擊&d規章 &dB2 &d來&a同意"));
        itemLore.add(colorText("&b點擊後將會&a同意 &9&l無語伺服器 &f- &6規則B章"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack B1() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 第一條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止&c惡意汙辱&f、&c騷擾&f、&c歧视&f伺服器管理人員以及玩家。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack B2() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 2);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 第二條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止&c詐欺&f玩家或對管理團隊之人員有&c謊報&f的行為。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack B3() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 3);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 第三條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止使用不雅或不適當之用戶名稱或暱稱。"));
        itemLore.add(colorText("&f(Discord用戶昵稱都會統一使用IGN)"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack B4() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 4);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 第四條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止出現有關&c種族主義&f、&c歧视&f、&c宗教&f、&c政治&f等相關的話題。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack B5() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 5);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 第五條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止在&b&l任何&f頻道惡意洗頻。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack B6() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 6);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6B章 第六條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止宣傳他人伺服器以及Discord伺服器。"));
        itemLore.add(colorText("&f除非这个文章经过管理员服主确认并且同意会由服主、副服主亲自發送"));
        itemLore.add(colorText("&f【如有其它人使用&b私聊方式來宣傳打擾你們&f，"));
        itemLore.add(colorText("&f請截圖到Discord &b#《\\uD83D\\uDCEB》客服&f 通知我們/自行聯係服主】"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack ConfirmInfo() {
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6關於本頁"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&b這是最終確認, 確認后將&c無法更改"));
        itemLore.add(colorText("&b規章會&c&l隨時更新&b請自行前往Discord確認最新規章."));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack ConfirmAgree() {
        ItemStack itemStack = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&a確認"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&b這是最終確認, 確認后將&c無法更改"));
        itemLore.add(colorText("&b規章會&c&l隨時更新&b請自行前往Discord確認最新規章."));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack ConfirmDeny() {
        ItemStack itemStack = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorText("&c取消"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    private static String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
