package me.kokored.speserver.sperulesplugin.rulesManager.rulesItem;

import java.util.ArrayList;
import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesUtil;
import me.kokored.speserver.sperulesplugin.sql.MySqlAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class PlayRules implements Listener {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    public PlayRules() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RulesGUI.GUITitle")))) {


            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6A章 &f- &6游玩須知"))) {
                player.closeInventory();
                player.sendMessage(colorText("&aplay rules confirmed \n" + rulesUtil.getDate()));
                MySqlAPI.setLinkData(player.getUniqueId().toString(), player.getName(), rulesUtil.getDate(), true);
            }
            /*
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&c&l拒絕 &6同意條款"))) {

            }
            */

            event.setCancelled(true);

        }

    }

    public static ItemStack A() {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 &f- &6游玩須知"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add(colorText(""));
        itemLore.add(colorText("&6A章 &f- &6游玩須知 &f(&d/rules&f)"));
        itemLore.add(colorText("&6B章 &f- &6聊天須知 &f(&d/chatrules&f)"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A1() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第一條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止在未經任何許可下進行破壞"));
        itemLore.add(colorText("&f不管有無領地皆不可,自然生成建築不在此限、擅自增建"));
        itemLore.add(colorText("&f、將他人建築納為私人領地(自然生成建築不在此限)"));
        itemLore.add("");
        itemLore.add(colorText("&b請玩家善用&6res&b設置領地保護自己的建築物"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A2() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 2);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第二條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止在未經任何許可下偷竊他人財產"));
        itemLore.add(colorText("&f若玩家對你的領地或非領地進行未經批准的破壞偷竊，"));
        itemLore.add(colorText("&f但問題不是非常嚴重請自己私下解決，"));
        itemLore.add("");
        itemLore.add(colorText("&c若無法自行處理請聯係管理員取得幫助。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A3() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 3);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第三條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f請勿在他人的領地周圍(前後左右20格)設置保護區"));
        itemLore.add(colorText("&f除非得到雙方同意。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A4() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 4);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第四條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f請勿在本伺服器使用任何作弊客戶端或模組遊玩，"));
        itemLore.add(colorText("&f(連點器除外 &c&l但CPS不能高于&b&l30&f)"));
        itemLore.add(colorText("&f其他外掛皆不可使用。"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A5() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 5);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第五條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&c請勿&f在本伺服器&c公然約&f他人遊玩&b其他minecraft伺服器"));
        itemLore.add(colorText("&f、宣傳其他伺服器&bDC&f等"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A6() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 6);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第六條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f請勿嘗試使用&cbug&f游玩，違例者之財產將會全數沒收&c(個人洗白)"));
        itemLore.add(colorText("&d[如果發現bug請你在Discord #《\uD83D\uDCEB》客服 反映]"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A7() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 7);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第七條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f如盜用他人賬號遊玩本伺服器被證實後將立即被永久封禁，"));
        itemLore.add(colorText("&f被盜竊(賬號)的玩家只能請你重新購買新號再次登入伺服器進行賠償"));
        itemLore.add(colorText("&c&l請玩家不要輕易給予他人自己的遊戲賬號或密碼"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private Material getMaterial(String data) {
        Material material = Material.valueOf(data);
        return material;
    }

}
