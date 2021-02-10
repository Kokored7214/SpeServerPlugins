package me.kokored.speserver.spemanagerplugin.bukkit.features.enchantments;

import me.kokored.speserver.spemanagerplugin.bukkit.features.item.use.anvil.QuickPickupTools;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class EnchantmentWrapper extends Enchantment {

    private final String name;
    private final int maxLevel;

    public EnchantmentWrapper(String key, String name, int level) {
        super(NamespacedKey.minecraft(key));
        this.name = name;
        this.maxLevel = level;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (name.equals("smp_enchant_telepathy"))
            return QuickPickupTools.testItem(item.getType());
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

}
