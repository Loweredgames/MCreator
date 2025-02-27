/*
 * MCreator (https://mcreator.net/)
 * Copyright (C) 2020 Pylo and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.mcreator.ui.dialogs.tools;

import com.google.common.base.CaseFormat;
import net.mcreator.element.ModElementType;
import net.mcreator.element.types.Tag;
import net.mcreator.generator.GeneratorConfiguration;
import net.mcreator.generator.GeneratorStats;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.action.ActionRegistry;
import net.mcreator.ui.action.BasicAction;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.dialogs.MCreatorDialog;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;
import net.mcreator.util.image.ImageUtils;
import net.mcreator.workspace.Workspace;
import net.mcreator.workspace.elements.ModElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class InjectTagsTool {

	private static void open(MCreator mcreator) {
		MCreatorDialog dialog = new MCreatorDialog(mcreator, L10N.t("dialog.tools.inject_tags.title"), true);
		dialog.setLayout(new BorderLayout(10, 10));
		dialog.setIconImage(UIRES.get("16px.injecttags").getImage());

		dialog.add("North", PanelUtils.join(FlowLayout.LEFT, L10N.label("dialog.tools.inject_tags.text_top")));

		JPanel props = new JPanel(new GridLayout(0, 1, 2, 2));

		JScrollPane scrollPane = new JScrollPane(props);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);

		dialog.add("Center", scrollPane);

		JButton ok = L10N.button("dialog.tools.inject_tags.confirm");
		JButton cancel = new JButton(UIManager.getString("OptionPane.cancelButtonText"));
		cancel.addActionListener(e -> dialog.setVisible(false));
		dialog.add("South", PanelUtils.join(ok, cancel));

		List<Consumer<Boolean>> callables = new ArrayList<>();

		callables.add(addTag(mcreator, props, "dirt", "minecraft", "Blocks", true));
		callables.add(addTag(mcreator, props, "logs", "minecraft", "Blocks", true));
		callables.add(addTag(mcreator, props, "fences", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "wooden_fences", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "walls", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "small_flowers", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "tall_flowers", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "bee_growables", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "valid_spawn", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "impermeable", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "beacon_base_blocks", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "leaves", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "climbable", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "fire", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "dragon_immune", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "wither_immune", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "animals_spawnable_on", "minecraft", "Blocks", false));
		callables.add(addTag(mcreator, props, "prevent_mob_spawning_inside", "minecraft", "Blocks", false));

		callables.add(addTag(mcreator, props, "arrows", "minecraft", "Items", false));
		callables.add(addTag(mcreator, props, "planks", "minecraft", "Items", false));
		callables.add(addTag(mcreator, props, "flowers", "minecraft", "Items", false));
		callables.add(addTag(mcreator, props, "small_flowers", "minecraft", "Items", false));

		callables.add(addTag(mcreator, props, "arrows", "minecraft", "Entities", false));
		callables.add(addTag(mcreator, props, "impact_projectiles", "minecraft", "Entities", false));
		callables.add(addTag(mcreator, props, "beehive_inhabitors", "minecraft", "Entities", false));
		callables.add(addTag(mcreator, props, "raiders", "minecraft", "Entities", false));
		callables.add(addTag(mcreator, props, "skeletons", "minecraft", "Entities", false));

		callables.add(addTag(mcreator, props, "is_overworld", "minecraft", "Biomes", true));
		callables.add(addTag(mcreator, props, "is_nether", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_end", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_ocean", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_mountain", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_river", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_hill", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_forest", "minecraft", "Biomes", false));
		callables.add(addTag(mcreator, props, "is_savanna", "minecraft", "Biomes", false));

		callables.add(addTag(mcreator, props, "tick", "minecraft", "Functions", false));
		callables.add(addTag(mcreator, props, "load", "minecraft", "Functions", false));

		callables.add(addTag(mcreator, props, "is_drowning", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "is_explosion", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "is_fall", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "is_fire", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "is_freezing", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "is_projectile", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "bypasses_armor", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "bypasses_cooldown", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "bypasses_effects", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "bypasses_enchantments", "minecraft", "Damage types", false));
		callables.add(addTag(mcreator, props, "bypasses_shield", "minecraft", "Damage types", false));

		ok.addActionListener(e -> {
			dialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			callables.forEach(c -> c.accept(false));
			mcreator.mv.reloadElementsInCurrentTab();
			dialog.setCursor(Cursor.getDefaultCursor());
			dialog.setVisible(false);
		});

		dialog.getRootPane().setDefaultButton(ok);
		dialog.setSize(740, 420);
		dialog.setLocationRelativeTo(mcreator);
		dialog.setVisible(true);
	}

	private static Consumer<Boolean> addTag(MCreator mcreator, JPanel panel, String name, String namespace, String type,
			boolean checked) {
		boolean existing = mcreator.getWorkspace().containsModElement(getNameForTag(name, type));

		JCheckBox box = new JCheckBox(
				"<html><kbd>" + namespace + ":" + name + (existing ? (" -> " + getNameForTag(name, type)) : "")
						+ "</kbd><small><br>" + L10N.t(
						"dialog.tools.inject_tags.tag." + type.toLowerCase(Locale.ENGLISH).replace(' ', '_') + "."
								+ namespace + "." + name));
		box.setSelected(checked);

		JLabel icon = new JLabel();
		switch (type) {
		case "Blocks" ->
				icon.setIcon(new ImageIcon(ImageUtils.resizeAA(ModElementType.BLOCK.getIcon().getImage(), 32)));
		case "Items" -> icon.setIcon(new ImageIcon(ImageUtils.resizeAA(ModElementType.ITEM.getIcon().getImage(), 32)));
		case "Functions" ->
				icon.setIcon(new ImageIcon(ImageUtils.resizeAA(ModElementType.FUNCTION.getIcon().getImage(), 32)));
		case "Entities" ->
				icon.setIcon(new ImageIcon(ImageUtils.resizeAA(ModElementType.LIVINGENTITY.getIcon().getImage(), 32)));
		case "Biomes" ->
				icon.setIcon(new ImageIcon(ImageUtils.resizeAA(ModElementType.BIOME.getIcon().getImage(), 32)));
		case "Damage types" ->
				icon.setIcon(new ImageIcon(ImageUtils.resizeAA(ModElementType.DAMAGETYPE.getIcon().getImage(), 32)));
		}

		panel.add(PanelUtils.centerAndEastElement(box, icon));

		if (existing)
			box.setEnabled(false);

		return altcondition -> {
			if (box.isSelected() || altcondition)
				injectTagToWorkspace(mcreator, name, namespace, type);
		};
	}

	private static String getNameForTag(String name, String type) {
		if (name.endsWith("s"))
			name = name.substring(0, name.length() - 1);

		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name.replace("_blocks", ""))
				+ ("Damage types".equals(type) ? "DamageTypes" : type);
	}

	private static void injectTagToWorkspace(MCreator mcreator, String name, String namespace, String type) {
		String modElementName = getNameForTag(name, type);
		Workspace workspace = mcreator.getWorkspace();

		if (!workspace.containsModElement(modElementName)) {
			Tag tag = new Tag(new ModElement(workspace, modElementName, ModElementType.TAG));
			tag.name = name;
			tag.namespace = namespace;
			tag.type = type;

			tag.blocks = Collections.emptyList();
			tag.items = Collections.emptyList();
			tag.functions = Collections.emptyList();
			tag.entities = Collections.emptyList();
			tag.biomes = Collections.emptyList();
			tag.damageTypes = Collections.emptyList();

			workspace.getModElementManager().storeModElementPicture(tag);
			workspace.addModElement(tag.getModElement());
			workspace.getGenerator().generateElement(tag);
			workspace.getModElementManager().storeModElement(tag);
		}
	}

	public static BasicAction getAction(ActionRegistry actionRegistry) {
		return new BasicAction(actionRegistry, L10N.t("action.pack_tools.tag"),
				e -> open(actionRegistry.getMCreator())) {
			@Override public boolean isEnabled() {
				GeneratorConfiguration gc = actionRegistry.getMCreator().getGeneratorConfiguration();
				return gc.getGeneratorStats().getModElementTypeCoverageInfo().get(ModElementType.TAG)
						!= GeneratorStats.CoverageStatus.NONE;
			}
		}.setIcon(UIRES.get("16px.injecttags"));
	}

}
