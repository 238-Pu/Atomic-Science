package com.builtbroken.atomic.content.machines.pipe.reactor.inv;

import com.builtbroken.atomic.AtomicScience;
import com.builtbroken.atomic.content.prefab.BlockPrefab;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 *
 * Created by Dark(DarkGuardsman, Robert) on 10/10/2018.
 */
public class BlockRodPipeInv extends BlockPrefab implements ITileEntityProvider
{
    public BlockRodPipeInv()
    {
        super(Material.IRON);
        setRegistryName(AtomicScience.PREFIX + "rod_pipe_inv");
        setTranslationKey(AtomicScience.PREFIX + "pipe.rod.inv");
        setCreativeTab(AtomicScience.creativeTab);
        setHardness(10);
        setResistance(10);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!(playerIn.getHeldItem(hand).getItem() instanceof ItemBlock))
        {
            if (!worldIn.isRemote)
            {
                playerIn.openGui(AtomicScience.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityRodPipeInv();
    }

    //-----------------------------------------------
    //-------- Properties ---------------------------
    //----------------------------------------------

    @Override
    public boolean isBlockNormalCube(IBlockState state)
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state)
    {
        return false;
    }
}
