package me.kokored.speserver.sperulesplugin.rulesManager.rulesItem;

import java.util.ArrayList;
import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.PlayRulesGUIBuilder;
import me.kokored.speserver.sperulesplugin.rulesManager.RulesUtil;
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

public class PlayRules implements Listener {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    public PlayRules() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&9&l無語伺服器 &6A章 &f- &6游玩規章"))) {


            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(colorText("&6A章 第六條"))
                    && event.getCurrentItem().getType().equals(Material.BOOK)
                    && event.getClick().isRightClick()) {
                player.closeInventory();

                player.openInventory(PlayRulesGUIBuilder.getPlayRulesConfirmGUI(player));
            }

            event.setCancelled(true);

        }
        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6A章 &f- &6游玩規章 &b最終確認"))) {


            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(colorText("&a確認"))
                    && event.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {

                String name = player.getName();
                String uuid = player.getUniqueId().toString();

                if (MySqlAPI.playRulesConfirmed(uuid) == false) {
                    plugin.getLogger().info(colorText("[MySQL] Player " + name + " Agreed Rules A - PlayRules"));
                    plugin.getLogger().info(colorText("[MySQL] Saving player " + name + "'s data..."));
                    MySqlAPI.setPlayRulesData(uuid, name, RulesUtil.getDate(), true);
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
                    player.sendMessage(colorText("&d恭喜你同意了 &6A章 &f- &6游玩規章"));
                    player.sendMessage(colorText("&b你可以使用指令 &b/rules &b再次打開 &6A章 &b來查看規章"));
                }

            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(colorText("&c取消"))
                    && event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                player.closeInventory();
            }

            event.setCancelled(true);

        }

        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6A章 &f- &6游玩規章"))) {

            event.setCancelled(true);

        }

    }

    public static ItemStack A() {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 &f- &6游玩規章"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add(colorText(""));
        itemLore.add(colorText("&6A章 &f- &6游玩規章 &f(&d/rules&f)"));
        itemLore.add(colorText("&6B章 &f- &6聊天規章 &f(&d/chatrules&f)"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack NeedToKnown() {
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&e提醒"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f【&b請珍惜每個領地，好好善用&f】"));
        itemLore.add("");
        itemLore.add(colorText("&f【&b請玩家保持良好的遊玩態度、維護遊戲環境是大家的責任。"));
        itemLore.add(colorText("&b請不要以身試法、當一個優質的玩家、"));
        itemLore.add(colorText("&b不想被別人偷、拆、那麼自己也不要做&f】"));
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
        itemLore.add(colorText("&d如你同意本規章, 請&6右鍵點擊&d規章 &dA6 &d來&a同意"));
        itemLore.add(colorText("&b點擊後將會&a同意 &9&l無語伺服器 &f- &6規則A章"));
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
    public static ItemStack A8() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 8);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第八條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f請勿&c惡意騷擾&f其他玩家(如岩漿浴、方塊掩埋、陷阱等)"));
        itemLore.add(colorText("&f如不認識或未獲得對方同意，&c請勿這樣做"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A9() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 9);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第九條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止&c&l惡意&f使用紅石對伺服器造成卡頓的現象。"));
        itemLore.add(colorText("&f例如：紅石連閃器"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A10() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 10);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第十條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f禁止偷竊他人容器的物品或者將他人容器鎖起佔為己有"));
        itemLore.add(colorText("&d(未鎖容器被偷或者具有公用性質都不會還原)"));
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack A11() {
        ItemStack itemStack = new ItemStack(Material.BOOK, 11);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemMeta.setDisplayName(colorText("&6A章 第十一條"));
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemLore.add("");
        itemLore.add(colorText("&f玩家請善用領地"));
        itemLore.add(colorText("&b- (使用木鋤選擇兩個對角後使用/res create <領地名>)"));
        itemLore.add(colorText("&f鎖箱子"));
        itemLore.add(colorText("&b- (使用告示牌對需上鎖之&d&l容器&b點擊右鍵)"));
        itemLore.add(colorText("&f這些是唯一的保護途徑"));
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
