package me.kokored.speserver.sperulesplugin.rulesManager.rulesItem;

import java.util.ArrayList;
import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ChatRules {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RulesGUI.GUITitle")))) {

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b3.&6&l伺服器游玩及使用規則"))) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&d伺服器游玩及使用規則:\n&6 https://kokored.wixsite.com/speechlessmc/rules"));
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&c&l拒絕 &6同意條款"))) {

            }

            event.setCancelled(true);

        }

    }

    public ItemStack A() {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();

        itemMeta.setDisplayName(colorText("&6A章 &f- &6基本須知"));

        itemLore.add(colorText("&6A章 &f- &6基本須知 &f(&d/rules&f)"));
        itemLore.add(colorText("&6B章 &f- &6聊天須知 &f(&d/chatrules&f)"));

        return itemStack;
    }

    private String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private Material getMaterial(String data) {
        Material material = Material.valueOf(data);
        return material;
    }

    void ContractGUI(Player p) {
        Inventory contract = Bukkit.createInventory(p, 54,
                ChatColor.translateAlternateColorCodes('&', "&9&l無語伺服器 &6&l玩家須知&b及&6&l條約"));

        ItemStack needtoknow_1 = new ItemStack(Material.BOOK, 1);
        ItemStack needtoknow_2 = new ItemStack(Material.BOOK, 2);
        ItemStack needtoknow_3 = new ItemStack(Material.BOOK, 3);
        ItemStack Contract_1 = new ItemStack(Material.PAPER, 1);
        ItemStack Refuse = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemStack Agree = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);

        ItemMeta needtoknow_1_meta = needtoknow_1.getItemMeta();
        ArrayList<String> needtoknow_1_lore = new ArrayList<>();
        needtoknow_1_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b1.&6&l分身"));
        needtoknow_1_lore.add(ChatColor.translateAlternateColorCodes('&', " &b• &d每一位玩家只能有一個帳號游玩本服,"));
        needtoknow_1_lore.add(ChatColor.translateAlternateColorCodes('&', "   &r- &6未事先申請的同IP帳號會視為分身處理!"));
        needtoknow_1_lore.add(ChatColor.translateAlternateColorCodes('&', "   &r- &6如有特別原因 例:兄弟姐妹(同居) 請&d向服主&l本人&d申請&r(&9沉默&a#6082&r)"));
        needtoknow_1_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        needtoknow_1_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        needtoknow_1_meta.setLore(needtoknow_1_lore);
        needtoknow_1.setItemMeta(needtoknow_1_meta);
        contract.setItem(0, needtoknow_1);

        ItemMeta needtoknow_2_meta = needtoknow_2.getItemMeta();
        ArrayList<String> needtoknow_2_lore = new ArrayList<>();
        needtoknow_2_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b2.&6&l非正規賬號"));
        needtoknow_2_lore.add(ChatColor.translateAlternateColorCodes('&', "&d非正規帳號例如:使用 黑卡/帳號生成器或其他途徑 來獲取的帳號"));
        needtoknow_2_lore.add(ChatColor.translateAlternateColorCodes('&', "&d如果您使用這些帳號來游玩本服,您需要特別注意以下的事項."));
        needtoknow_2_lore.add(ChatColor.translateAlternateColorCodes('&', "&b • &6您的帳號有可能會被MoJang官方發現並刪除."));
        needtoknow_2_lore.add(ChatColor.translateAlternateColorCodes('&', "&b • &6如果帳號被刪除, 你的任何損失(物資/財產/領地/...)"));
        needtoknow_2_lore.add(ChatColor.translateAlternateColorCodes('&', "     &6伺服方將不會負任何責任."));
        needtoknow_2_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        needtoknow_2_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        needtoknow_2_meta.setLore(needtoknow_2_lore);
        needtoknow_2.setItemMeta(needtoknow_2_meta);
        contract.setItem(1, needtoknow_2);

        ItemMeta needtoknow_3_meta = needtoknow_3.getItemMeta();
        ArrayList<String> needtoknow_3_lore = new ArrayList<>();
        needtoknow_3_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b3.&6&l伺服器游玩及使用規則"));
        needtoknow_3_lore.add(ChatColor.translateAlternateColorCodes('&', "&d請前往以下鏈接(點擊這個物品)來閲讀"));
        needtoknow_3_lore.add(ChatColor.translateAlternateColorCodes('&', "&6https://kokored.wixsite.com/speechlessmc/rules"));
        needtoknow_3_lore.add("");
        needtoknow_3_lore.add(ChatColor.translateAlternateColorCodes('&', "&d全新的規則網站製作中(非上面網站), 敬請期待&6！"));
        needtoknow_3_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        needtoknow_3_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        needtoknow_3_meta.setLore(needtoknow_3_lore);
        needtoknow_3.setItemMeta(needtoknow_3_meta);
        contract.setItem(2, needtoknow_3);

        ItemMeta Contract_1_meta = Contract_1.getItemMeta();
        ArrayList<String> Contract_1_lore = new ArrayList<>();
        Contract_1_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a同意&6&l條款"));
        Contract_1_lore.add(ChatColor.translateAlternateColorCodes('&', "&6如果你 &a&l同意 &d并&a&l願意遵守&6所有的 &b玩家須知/規章"));
        Contract_1_lore.add(ChatColor.translateAlternateColorCodes('&', "&6請點擊選單右下角的 &a綠 &6玻璃片"));
        Contract_1_lore.add("");
        Contract_1_lore.add(ChatColor.translateAlternateColorCodes('&', "&6請注意! &d如果你&a同意&r/&c拒絕&d了此條款"));
        Contract_1_lore.add(ChatColor.translateAlternateColorCodes('&', "&d系統會&b&l自動&e記錄&d并&e發送給伺服器管理員&d來做&6&l雙重記錄."));
        Contract_1_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        Contract_1_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Contract_1_meta.setLore(Contract_1_lore);
        Contract_1.setItemMeta(Contract_1_meta);
        contract.setItem(4, Contract_1);

        ItemMeta Refuse_meta = Refuse.getItemMeta();
        ArrayList<String> Refuse_lore = new ArrayList<>();
        Refuse_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l拒絕 &6同意條款"));
        Refuse_lore.add(ChatColor.translateAlternateColorCodes('&', "&6點我立即 &c拒絕 &6同意條款."));
        Refuse_lore.add("");
        Refuse_lore.add(ChatColor.translateAlternateColorCodes('&', "&6請注意! &d如果你&a同意&r/&c拒絕&d了此條款"));
        Refuse_lore.add(ChatColor.translateAlternateColorCodes('&', "&d系統會&b&l自動&e記錄&d并&e發送給伺服器管理員&d來做&6&l雙重記錄."));
        Refuse_meta.setLore(Refuse_lore);
        Refuse.setItemMeta(Refuse_meta);
        contract.setItem(45, Refuse);

        ItemMeta Agree_meta = Agree.getItemMeta();
        ArrayList<String> Agree_lore = new ArrayList<>();
        Agree_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l同意 &6條款"));
        Agree_lore.add(ChatColor.translateAlternateColorCodes('&', "&6點我立即 &a同意 &6條款."));
        Agree_lore.add("");
        Agree_lore.add(ChatColor.translateAlternateColorCodes('&', "&6請注意! &d如果你&a同意&r/&c拒絕&d了此條款"));
        Agree_lore.add(ChatColor.translateAlternateColorCodes('&', "&d系統會&b&l自動&e記錄&d并&e發送給伺服器管理員&d來做&6&l雙重記錄."));
        Agree_meta.setLore(Agree_lore);
        Agree.setItemMeta(Agree_meta);
        contract.setItem(53, Agree);

        p.openInventory(contract);
    }

}
