package me.kokored.speserver.spemanagerplugin.bukkit.api;

import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultAPI {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    public static Boolean ecoStatus = false;

    private static Economy econ = null;

    public VaultAPI() {

        setupEconomy();

    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        ecoStatus = true;
        return econ != null;
    }

    public static Economy getEconomy() {
        if (ecoStatus = true) {
            return econ;
        }
        return null;
    }

}
