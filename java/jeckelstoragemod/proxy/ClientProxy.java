package jeckelstoragemod.proxy;

public class ClientProxy extends CommonProxy
{
	@Override public boolean isClient() { return true; }

	@Override public void initialize(final String modId)
	{
		super.initialize(modId);
	}
}
