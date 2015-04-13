package jeckelstoragemod.core;

import jeckelcorelibrary.core.configs.ConfigHandlerValues;
import net.minecraftforge.common.config.Configuration;

public class ConfigValues extends ConfigHandlerValues
{
	private static final long serialVersionUID = 7153923911952910240L;

	public ConfigValues()
	{
		this.add(this._updateChecking);
		this.add(this._barrelQuickFill);
	}

	public boolean isUpdateCheckingEnabled() { return this._updateChecking.getValue(); }
	protected final ConfigValueBoolean _updateChecking = new ConfigValueBoolean("EnableUpdateChecking", Configuration.CATEGORY_GENERAL,
			"Control automatic update checking.\n.Setting this option to false will disable version checking.",
			true);

	public boolean isBarrelQuickFillEnabled() { return this._updateChecking.getValue(); }
	protected final ConfigValueBoolean _barrelQuickFill = new ConfigValueBoolean("Enable Barrel Quick-Fill", Configuration.CATEGORY_GENERAL,
			"When enabled, right-clicking a barrel with an appropriate fluid container will deposit the item's contents into the barrel.",
			true);

	@Override public void update(final Configuration config)
	{
		super.update(config);

		Refs.getUpdateChecker().enable(this.isUpdateCheckingEnabled());
	}
}
