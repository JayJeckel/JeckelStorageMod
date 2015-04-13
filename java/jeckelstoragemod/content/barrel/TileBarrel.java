package jeckelstoragemod.content.barrel;

import java.util.ArrayList;
import java.util.List;

import jeckelcorelibrary.api.guis.ITileGuiActivator;
import jeckelcorelibrary.api.processes.ITickProcess;
import jeckelcorelibrary.api.tiles.ITileInteractable;
import jeckelcorelibrary.api.tiles.ITileProcessor;
import jeckelcorelibrary.api.tiles.ITileTanker;
import jeckelcorelibrary.base.tiles.ATileInventory;
import jeckelcorelibrary.core.fluids.BasicFluidTank;
import jeckelcorelibrary.core.processes.special.TankExchangeProcess;
import jeckelcorelibrary.utils.FluidUtil;
import jeckelstoragemod.core.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileBarrel
extends ATileInventory
implements ITileInteractable, ITileGuiActivator, IFluidHandler, ITileProcessor, ITileTanker
{
	public TileBarrel()
	{
		this(null, -1);
	}

	public TileBarrel(ItemStack stack, int tankCapacity)
	{
		super(2);
		if (stack != null) { this.setTileName(stack.getDisplayName()); }

		this._processes = new ArrayList<ITickProcess>();
		this._tanks = new ArrayList<FluidTank>();

		this._tank = new BasicFluidTank(tankCapacity);
		this._tanks.add(this._tank);
		this.tankExchanger = new TankExchangeProcess("tank_exchanger", 20, this, this.getTank(), 0, 1);
		this._processes.add(this.tankExchanger);
	}

	private final List<ITickProcess> _processes;

	private final List<FluidTank> _tanks;

	public final ITickProcess tankExchanger;

	@Override public void updateEntity()
	{
		boolean dirty = false;

		if (!this.worldObj.isRemote)
		{
			if (this.tankExchanger.updateProcess(this.worldObj)) { dirty = true; }
		}

		if (dirty) { this.markDirty(); }
	}


	// ##################################################
	//
	// Tank Capacity
	//
	// ##################################################

	private BasicFluidTank _tank = null;

	public BasicFluidTank getTank() { return this._tank; }

	public FluidStack getTankFluid() { return this._tank.getFluid(); }


	// ##################################################
	//
	// ITileInteractable
	//
	// ##################################################

	@Override public void interact(EntityPlayer player, World world, int x, int y, int z, int side)
	{
		if (player.isSneaking()) { return; }
		ItemStack stack = player.getHeldItem();
		if (Refs.getConfigValues().isBarrelQuickFillEnabled() && FluidUtil.isFilledContainer(stack))
		{
			FluidStack fluid = FluidUtil.getFluid(stack);
			if (this._tank.canFillAll(fluid))
			{
				this.fill(ForgeDirection.UP, fluid, true);
				if (!player.capabilities.isCreativeMode)
				{
					if (stack.stackSize > 1)
					{
						player.inventory.decrStackSize(player.inventory.currentItem, 1);
						player.inventory.addItemStackToInventory(FluidUtil.getEmptyContainer(stack));
					}
					else
					{
						player.setCurrentItemOrArmor(0, FluidUtil.getEmptyContainer(stack));
					}
				}
				return;
			}
		}
		player.openGui(Refs.getMod(), 0, world, x, y, z);
	}


	// ##################################################
	//
	// ITileGuiActivator
	//
	// ##################################################

	@Override public Object createContainer(EntityPlayer player) { return new ContainerBarrel(player, this); }

	@Override public Object createScreen(EntityPlayer player) { return new ScreenBarrel(player, this); }


	// ##################################################
	//
	// ITileProcessor
	//
	// ##################################################

	@Override public List<ITickProcess> getProcesses() { return this._processes; }


	// ##################################################
	//
	// ITileTanker
	//
	// ##################################################

	@Override public List<FluidTank> getTanks() { return this._tanks; }


	// ##################################################
	//
	// IFluidHandler
	//
	// ##################################################

	@Override public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		//int MAYBE = "This might also be a problem. Fill may not check if there is enough room.";
		int amountFilled = this._tank.fill(resource, doFill);
		if (doFill && amountFilled > 0) { this.markDirty(); }
		return amountFilled;
	}

	@Override public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		FluidStack drained = this._tank.drain(resource.amount, doDrain);
		if (doDrain && drained != null) { this.markDirty(); }
		return drained;
	}

	@Override public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		FluidStack drained = this._tank.drain(maxDrain, doDrain);
		if (doDrain && drained != null) { this.markDirty(); }
		return drained;
	}

	@Override public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		//int ERROR = "This is an error! This method should not always return true. Check stuff so barrels don't overfill.";
		return true;
	}

	@Override public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		//int ERROR = "This is an error! Again, why the hell aren't we checking if the barrel is empty!";
		return true;
	}

	@Override public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this._tank.getInfo() };
	}
}
