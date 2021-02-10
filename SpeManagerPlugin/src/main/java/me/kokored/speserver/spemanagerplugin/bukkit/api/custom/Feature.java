package me.kokored.speserver.spemanagerplugin.bukkit.api.custom;

import me.kokored.speserver.spemanagerplugin.bukkit.api.config.Configs;

public class Feature {

    public static Boolean checkFeature(String feature) {
        return Configs.get("config_bukkit").getBoolean(feature);
    }

    public static String DEATH_EXP() {
        return "Features.DeathExp";
    }

    //CUSTOM ENCHANTMENTS

    public static String TELEPATHY() {
        return "Features.enchantments.Telepathy";
    }

    //CUSTOM ITEMS

    public static String QUICK_PICKUP_INSTRUMENT() {
        return "Features.items.QuickPickupInstrument";
    }

}
