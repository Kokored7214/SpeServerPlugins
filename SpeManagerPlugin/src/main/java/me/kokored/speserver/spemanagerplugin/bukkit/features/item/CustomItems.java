package me.kokored.speserver.spemanagerplugin.bukkit.features.item;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bukkit.features.item.custom.QuickPickupInstrument;
import me.kokored.speserver.spemanagerplugin.bukkit.features.item.use.anvil.QuickPickupTools;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

public class CustomItems {

    public static List<NamespacedKey> recipes = new ArrayList<>();

    //Custom
    public static QuickPickupInstrument quickPickupInstrument;
    //Use_Anvil
    public static QuickPickupTools quickPickupTools;

    public CustomItems() {

        custom();
        anvil();

    }

    private void custom() {

        quickPickupInstrument = new QuickPickupInstrument();

    }

    private void anvil() {

        quickPickupTools = new QuickPickupTools();

    }

    public static void disable() {
        for (NamespacedKey recipe : recipes) {
            Bukkit.removeRecipe(recipe);
        }
    }

}
