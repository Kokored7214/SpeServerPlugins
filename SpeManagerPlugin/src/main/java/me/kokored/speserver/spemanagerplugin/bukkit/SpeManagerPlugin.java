package me.kokored.speserver.spemanagerplugin.bukkit;

import me.kokored.speserver.spemanagerplugin.bukkit.api.depend.VaultAPI;
import me.kokored.speserver.spemanagerplugin.bukkit.api.config.Configs;
import me.kokored.speserver.spemanagerplugin.bukkit.api.sql.MySQL;
import me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.CustomEnchants;
import me.kokored.speserver.spemanagerplugin.bukkit.features.DeathExp;
import me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.CustomInventory;
import me.kokored.speserver.spemanagerplugin.bukkit.features.item.CustomItems;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeManagerPlugin extends JavaPlugin {

    Configs configs;

    VaultAPI vaultAPI;

    MySQL mySQL;

    @Override
    public void onEnable() {

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            Message.consoleLog("warning", "[API] Plugin Vault was not found! Pls install this plugin!");
            return;
        }

        init();

        new CustomEnchants();
        new CustomInventory();
        new CustomItems();
        new DeathExp();

    }

    @Override
    public void onDisable() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getOpenInventory() != null)
                player.closeInventory();
        }

        CustomItems.disable();
        mySQL.disconnect();

    }

    private void init() {

        if (!(getDataFolder().exists())) {
            getDataFolder().mkdir();
        }

        configs = new Configs();

        vaultAPI = new VaultAPI();
        mySQL = new MySQL();

    }

}
