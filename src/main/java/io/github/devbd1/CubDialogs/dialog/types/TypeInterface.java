
package io.github.devbd1.CubDialogs.dialog.types;

import io.papermc.paper.registry.data.dialog.type.DialogType;
import org.bukkit.configuration.ConfigurationSection;

public interface TypeInterface {
    DialogType buildDialogType(ConfigurationSection config);
    String getTypeName();
}
