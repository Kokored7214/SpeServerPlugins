package me.kokored.speserver.spemanagerplugin.bukkit.features.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.api.VaultAPI;
import me.kokored.speserver.spemanagerplugin.bukkit.config.Configs;
import me.kokored.speserver.spemanagerplugin.bukkit.features.gui.guis.switchGUI.SwitchGUI;
import me.kokored.speserver.spemanagerplugin.bukkit.sql.MySQL;
import me.kokored.speserver.spemanagerplugin.bukkit.sql.table.Feature_switch;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Sound;
import me.kokored.speserver.spemanagerplugin.core.util.Date;
import me.kokored.speserver.spemanagerplugin.core.util.ErrorCode;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class DeathExp implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    private static String invTitle = Message.getColorText("&6&l無語系統 &7» &d死亡處罰選擇");
    private static String invConfirmTitle = Message.getColorText("&6&l無語系統 &7» &d死亡處罰確認");

    private static String expLevel = Message.getColorText("&d經驗等級");
    private static String money = Message.getColorText("&6玩家金錢");
    private static String confirm0 = Message.getColorText("&a確認 - &d經驗等級");
    private static String confirm1 = Message.getColorText("&a確認 - &6玩家金錢");
    private static String goBack = Message.getColorText("&7返回");

    public DeathExp() {

        if (Configs.get("config_bukkit").getBoolean("Features.DeathExp") == false) {
            return;
        }

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {

        Player player = event.getEntity();

        if (player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY) == false) {
            Message.consoleLog("info", "DeathExp has disabled... You can enable it at config_bukkit.yml file.");
            return;
        }

        if (MySQL.getDbStats() == false) {

            BaseComponent[] bcText = new ComponentBuilder()
                    .append("\n\n")
                    .append("在系統與你執行任務時發生錯誤, 請向伺服器管理人員回報此錯誤!").color(ChatColor.RED)
                    .append("\n")
                    .append("錯誤代碼: ").color(ChatColor.WHITE).append(ErrorCode.sql_no_sql_connect()).color(ChatColor.GRAY)
                    .append("\n")
                    .append("時間: ").color(ChatColor.WHITE).append(Date.getDate("yyyy/MM/dd HH:mm:ss")).color(ChatColor.GRAY)
                    .append("\n")
                    .append("玩家: ").color(ChatColor.WHITE).append(player.getName()).color(ChatColor.GRAY)
                    .append("\n\n")
                    .create();

            player.spigot().sendMessage(bcText);

            Message.consoleLog("sql_error", "MySQL connection not found!");
            Message.consoleLog("sql_error", "Error code: " + ErrorCode.sql_no_sql_connect());

            return;
        }

        if (Feature_switch.getDeathExp(player.getUniqueId().toString()) == 0) {
            //扣經驗
            Integer level = player.getLevel();

            if (level > 30) {
                Random random = new Random();
                int less = random.nextInt((level / 3) + 5);

                level -= less;
                player.setLevel(level);
                sendLessMessage(player, less, 0);

            } else if (level > 20 && level <= 30) {

                level -= 10;
                player.setLevel(level);
                sendLessMessage(player, 10, 0);

            } else if (level > 10 && level < 20) {

                level -= 5;
                player.setLevel(level);
                sendLessMessage(player, 5, 0);

            }

        } else if (Feature_switch.getDeathExp(player.getUniqueId().toString()) == 1) {
            //扣金錢

            //檢查VaultAPI是否有與伺服器經濟插件連接上
            if (VaultAPI.ecoStatus == false) {

                BaseComponent[] bcText = new ComponentBuilder()
                        .append("\n\n")
                        .append("在系統與你執行任務時發生錯誤, 請向伺服器管理人員回報此錯誤!").color(ChatColor.RED)
                        .append("\n")
                        .append("錯誤代碼: ").color(ChatColor.WHITE).append(ErrorCode.vault_eco_null()).color(ChatColor.GRAY)
                        .append("\n")
                        .append("時間: ").color(ChatColor.WHITE).append(Date.getDate("yyyy/MM/dd HH:mm:ss")).color(ChatColor.GRAY)
                        .append("\n")
                        .append("玩家: ").color(ChatColor.WHITE).append(player.getName()).color(ChatColor.GRAY)
                        .append("\n\n")
                        .create();

                player.spigot().sendMessage(bcText);

                Message.consoleLog("error", "[API] Economy plugin was not found or have an error!");
                Message.consoleLog("error", "[API] Error code: " + ErrorCode.vault_eco_null());

                return;
            }

            Double money = VaultAPI.getEconomy().getBalance(player);

            if (money >= 50000) {

                VaultAPI.getEconomy().withdrawPlayer(player, 60);
                sendLessMessage(player, 60, 1);

            }else if (money < 50000 &&money >= 10000) {

                VaultAPI.getEconomy().withdrawPlayer(player, 30);
                sendLessMessage(player, 30, 1);

            }else if (money < 10000 && money > 3000) {

                VaultAPI.getEconomy().withdrawPlayer(player, 15);
                sendLessMessage(player, 15, 1);

            }else if (money > 15 && money <= 3000) {

                VaultAPI.getEconomy().withdrawPlayer(player, 5);
                sendLessMessage(player, 5, 1);

            }

        }

    }

    //0 = 經驗
    //1 = 金錢
    //2 = 不夠經驗扣錢
    //3 = 不夠錢扣經驗
    public void sendLessMessage(Player player, Integer less, Integer which) {

        TextComponent tc = new TextComponent(ChatColor.GOLD + "點擊這裏");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/switch"));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("執行指令: ").color(ChatColor.AQUA)
                .append("/switch").color(ChatColor.GOLD)
                .create()));

        TextComponent nothing = new TextComponent("");
        nothing.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, ""));
        nothing.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));

        if (which == 0) {

            BaseComponent[] cb = new ComponentBuilder()
                    .append("\n")
                    .append("[").color(ChatColor.WHITE).append("系統").color(ChatColor.RED).append("]").color(ChatColor.WHITE)
                    .append(" » ").color(ChatColor.GRAY)
                    .append("你死亡了... ").color(ChatColor.LIGHT_PURPLE).append("并損失了經驗等級 ").color(ChatColor.DARK_PURPLE)
                    .append(less.toString()).color(ChatColor.YELLOW)
                    .append("\n")
                    .append("你可以").color(ChatColor.WHITE)
                    .append(tc)
                    .append(nothing)
                    .append("或使用指令").color(ChatColor.WHITE).append("/switch").color(ChatColor.AQUA)
                    .append("來").color(ChatColor.WHITE).append("選擇").color(ChatColor.AQUA).append("首選處罰項").color(ChatColor.WHITE)
                    .append("\n")

                    .create();

            player.spigot().sendMessage(cb);
            return;
        }
        if (which == 1) {

            BaseComponent[] cb = new ComponentBuilder()
                    .append("\n")
                    .append("[").color(ChatColor.WHITE).append("系統").color(ChatColor.RED).append("]").color(ChatColor.WHITE)
                    .append(" » ").color(ChatColor.GRAY)
                    .append("你死亡了... ").color(ChatColor.LIGHT_PURPLE).append("并損失了 ").color(ChatColor.DARK_PURPLE)
                    .append("無語幣:").color(ChatColor.GOLD).append("$" + less.toString()).color(ChatColor.GREEN)
                    .append("\n")
                    .append("你可以").color(ChatColor.WHITE)
                    .append(tc)
                    .append(nothing)
                    .append("或使用指令").color(ChatColor.WHITE).append("/switch").color(ChatColor.AQUA)
                    .append("來").color(ChatColor.WHITE).append("選擇").color(ChatColor.AQUA).append("首選處罰項").color(ChatColor.WHITE)
                    .append("\n")

                    .create();

            player.spigot().sendMessage(cb);
            return;
        }
        if (which == 2) {

            BaseComponent[] cb = new ComponentBuilder()
                    .append("\n")
                    .append("[").color(ChatColor.WHITE).append("系統").color(ChatColor.RED).append("]").color(ChatColor.WHITE)
                    .append(" » ").color(ChatColor.GRAY)
                    .append("你死亡了... ").color(ChatColor.LIGHT_PURPLE).append("由於你沒有足夠的經驗, ").append("系統扣除了你的 ").color(ChatColor.DARK_PURPLE)
                    .append("無語幣:").color(ChatColor.GOLD)
                    .append("$" + less.toString()).color(ChatColor.GREEN)
                    .append("\n")
                    .append("你可以").color(ChatColor.WHITE)
                    .append(tc)
                    .append(nothing)
                    .append("或使用指令").color(ChatColor.WHITE).append("/switch").color(ChatColor.AQUA)
                    .append("來").color(ChatColor.WHITE).append("選擇").color(ChatColor.AQUA).append("首選處罰項").color(ChatColor.WHITE)
                    .append("\n")

                    .create();

            player.spigot().sendMessage(cb);
            return;
        }
        if (which == 3) {

            BaseComponent[] cb = new ComponentBuilder()
                    .append("\n")
                    .append("[").color(ChatColor.WHITE).append("系統").color(ChatColor.RED).append("]").color(ChatColor.WHITE)
                    .append(" » ").color(ChatColor.GRAY)
                    .append("你死亡了... ").color(ChatColor.LIGHT_PURPLE).append("由於你沒有足夠的金錢,  ").append("系統扣除了你的 ").color(ChatColor.DARK_PURPLE)
                    .append("經驗等級: ").color(ChatColor.GOLD).append(less.toString()).color(ChatColor.YELLOW)
                    .append("\n")
                    .append("你可以").color(ChatColor.WHITE)
                    .append(tc)
                    .append(nothing)
                    .append("或使用指令").color(ChatColor.WHITE).append("/switch").color(ChatColor.AQUA)
                    .append("來").color(ChatColor.WHITE).append("選擇").color(ChatColor.AQUA).append("首選處罰項").color(ChatColor.WHITE)
                    .append("\n")

                    .create();

            player.spigot().sendMessage(cb);
            return;
        }

    }

    //GUI
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

        if (event.getView().getTitle().equals(invTitle)) {


            if (itemName.equals(expLevel)) {
                player.updateInventory();
                Sound.playGUIClickSound(player);

                openConfirmGUI(player, 0);
            }
            if (itemName.equals(money)) {
                player.updateInventory();
                Sound.playGUIClickSound(player);

                openConfirmGUI(player, 1);
            }
            if (itemName.equals(goBack)) {
                player.updateInventory();
                Sound.playGUIClickSound(player);

                player.openInventory(SwitchGUI.getPage(1));
            }

            event.setCancelled(true);

        }

        if (event.getView().getTitle().equals(invConfirmTitle)) {
            if (itemName.equals(confirm0)) {
                player.updateInventory();
                player.closeInventory();
                player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 1f);

                Feature_switch.updateDeathExp(player.getUniqueId().toString(), 0);
                player.sendMessage(Message.getColorText("&f[&c系統&f] &7» &e你選擇了 " + expLevel + " &e作為你的首選處罰項。"));
            }
            if (itemName.equals(confirm1)) {
                player.updateInventory();
                player.closeInventory();
                player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 1f);

                Feature_switch.updateDeathExp(player.getUniqueId().toString(), 1);
                player.sendMessage(Message.getColorText("&f[&c系統&f] &7» &e你選擇了 " + money + " &e作為你的首選處罰項。"));
            }
            if (itemName.equals(goBack)) {
                player.updateInventory();
                Sound.playGUIClickSound(player);

                player.openInventory(getSwitchGUI(player));
            }
        }

    }

    public static Inventory getSwitchGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player, 27, invTitle);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {

                ItemStack air = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta itemMeta = air.getItemMeta();
                itemMeta.setDisplayName(" ");
                air.setItemMeta(itemMeta);

                inventory.setItem(i, air);

            }
        }

        ItemStack switchExp = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta switchExpMeta = switchExp.getItemMeta();
        List<String> switchExpLore = new ArrayList<>();
        switchExpMeta.setDisplayName(expLevel);
        switchExpMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        switchExpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        switchExpLore.add("");
        switchExpLore.add(Message.getColorText("&a系統將會在你死亡後使用經"));
        switchExpLore.add(Message.getColorText("&a驗等級作為首選處罰項。"));
        switchExpLore.add("");
        switchExpLore.add(Message.getColorText("&e點擊選擇"));
        switchExpMeta.setLore(switchExpLore);
        switchExp.setItemMeta(switchExpMeta);
        inventory.setItem(11, switchExp);

        ItemStack switchMoney = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta switchMoneyMeta = switchMoney.getItemMeta();
        List<String> switchMoneyLore = new ArrayList<>();
        switchMoneyMeta.setDisplayName(money);
        switchMoneyMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        switchMoneyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        switchMoneyLore.add("");
        switchMoneyLore.add(Message.getColorText("&a系統將會在你死亡後使用 &6無語幣"));
        switchMoneyLore.add(Message.getColorText("&a作為首選處罰項。"));
        switchMoneyLore.add("");
        switchMoneyLore.add(Message.getColorText("&e點擊選擇"));
        switchMoneyMeta.setLore(switchMoneyLore);
        switchMoney.setItemMeta(switchMoneyMeta);
        inventory.setItem(13, switchMoney);

        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(goBack);
        back.setItemMeta(backMeta);
        inventory.setItem(15, back);

        return inventory;

    }

    private void openConfirmGUI(Player player, Integer switch_) {

        Inventory inventory = Bukkit.createInventory(player, 27, invConfirmTitle);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {

                ItemStack air = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta itemMeta = air.getItemMeta();
                itemMeta.setDisplayName(" ");
                air.setItemMeta(itemMeta);

                inventory.setItem(i, air);

            }
        }

        ItemStack switchItem = null;
        if (switch_ == 0) {
            switchItem = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        }else if (switch_ == 1) {
            switchItem = new ItemStack(Material.GOLD_INGOT, 1);
        }
        ItemMeta switchMeta = switchItem.getItemMeta();
        List<String> switchLore = new ArrayList<>();
        if (switch_ == 0) {
            switchMeta.setDisplayName(confirm0);
            switchLore.add("");
            switchLore.add(Message.getColorText("&a點擊確認選擇使用 &d經驗等級"));
            switchLore.add(Message.getColorText("&a作為首選處罰項。"));
        }else if (switch_ == 1) {
            switchMeta.setDisplayName(confirm1);
            switchLore.add("");
            switchLore.add(Message.getColorText("&a點擊確認選擇使用 &6玩家金錢"));
            switchLore.add(Message.getColorText("&a作為首選處罰項。"));
        }
        switchLore.add("");
        switchLore.add(Message.getColorText("&e點擊確認"));

        switchMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        switchMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        switchMeta.setLore(switchLore);
        switchItem.setItemMeta(switchMeta);
        inventory.setItem(11, switchItem);

        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(goBack);
        back.setItemMeta(backMeta);
        inventory.setItem(15, back);

        player.openInventory(inventory);

    }

}
