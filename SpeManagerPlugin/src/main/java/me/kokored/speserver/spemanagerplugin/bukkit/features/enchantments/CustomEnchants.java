package me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments.custom.Telepathy;
import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {

    private static ArrayList<Enchantment> customEnchants = new ArrayList<>();

    public static final Enchantment TELEPATHY = new EnchantmentWrapper("smp_enchant_telepathy", "快速拾取", 0);

    public CustomEnchants() {
        register();

        new Telepathy();
    }

    public void register() {
        customEnchants.add(TELEPATHY);
        for (Enchantment enchantment : customEnchants) {
            boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment);
            if (!(registered)) {
                regEnchantment(enchantment);
            }
        }
    }

    public void regEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);

            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
    }

}
