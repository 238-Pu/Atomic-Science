package com.builtbroken.atomic.map.exposure.node;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

/**
 *
 * Created by Dark(DarkGuardsman, Robert) on 9/21/2018.
 */
public class RadSourceTile<E extends TileEntity> extends RadiationSource<E>
{
    private final IntSupplier radFunction;
    private final BooleanSupplier activeFunction;

    public RadSourceTile(E host, IntSupplier radFunction, BooleanSupplier activeFunction)
    {
        super(host);
        this.radFunction = radFunction;
        this.activeFunction = activeFunction;
    }

    @Override
    public boolean isRadioactive()
    {
        return super.isRadioactive() && activeFunction.getAsBoolean();
    }

    @Override
    public int getRadioactiveMaterial()
    {
        return radFunction.getAsInt();
    }

    @Override
    public boolean doesSourceExist()
    {
        return world() != null && host != null && !host.isInvalid();
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
        if(object instanceof RadSourceTile)
        {
            if(host != null && ((RadSourceTile)object).host != null)
            {
                return host.getWorld() == ((TileEntity)((RadSourceTile)object).host).getWorld() && host.getPos() == ((TileEntity)((RadSourceTile)object).host).getPos();
            }
            return ((RadSourceTile)object).host == host;
        }
        return false;
    }

    @Override
    protected String getDebugName()
    {
        return "RadSourceTile";
    }
}
