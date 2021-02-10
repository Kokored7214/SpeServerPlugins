package me.kokored.speserver.spemanagerplugin.bukkit.features.inventory;

import me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.customRecipe.Recipe;
import me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.switchGui.SwitchGUI;
import me.kokored.speserver.spemanagerplugin.bukkit.features.inventory.switchGui.HeaderBar;

public class CustomInventory {

    public CustomInventory() {

        new SwitchGUI();
        new HeaderBar();

        new Recipe();

        //拼裝選單, 尚未完成
        //new AssembleMusterGUI();

    }

}
