package com.builtbroken.atomic.map.thermal.node;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

/**
 *
 * Created by Dark(DarkGuardsman, Robert) on 9/21/2018.
 */
public class ThermalSourceTile<E extends TileEntity> extends ThermalSource<E>
{
    private final IntSupplier heatFunction;
    private final BooleanSupplier activeFunction;

    public ThermalSourceTile(E host, IntSupplier heatFunction, BooleanSupplier activeFunction)
    {
        super(host);
        this.heatFunction = heatFunction;
        this.activeFunction = activeFunction;
    }

    @Override
    public boolean canGeneratingHeat()
    {
        return getHeatGenerated() > 0 && activeFunction.getAsBoolean();
    }

    @Override
    public boolean doesSourceExist()
    {
        return world() != null && host != null && !host.isInvalid();
    }

    @Override
    public int getHeatGenerated()
    {
        return heatFunction.getAsInt();
    }

    @Override
    public World world()
    {
        return host.getWorld();
    }

    @Override
    public double z()
    {
        return host.getPos().getZ() + 0.5;
    }

    @Override
    public double x()
    {
        return host.getPos().getX() + 0.5;
    }

    @Override
    public double y()
    {
        return host.getPos().getY() + 0.5;
    }

    @Override
    public int zi()
    {
        return host.getPos().getZ();
    }

    @Override
    public int xi()
    {
        return host.getPos().getX();
    }

    @Override
    public int yi()
    {
        return host.getPos().getY();
    }

    @Override
    public boolean equals(Object object)
    {
        if(object == this)
        {
            return true;
        }
        if(object instanceof ThermalSourceTile)
        {
            if(host != null && ((ThermalSourceTile)object).host != null)
            {
                return host.getWorld() == ((TileEntity)((ThermalSourceTile)object).host).getWorld() && host.getPos() == ((TileEntity)((ThermalSourceTile)object).host).getPos();
            }
            return ((ThermalSourceTile)object).host == host;
        }
        return false;
    }

    @Override
    protected String getDebugName()
    {
        return "ThermalSourceTile";
    }
}
