package com.builtbroken.atomic.lib.gui.slot;

import com.builtbroken.atomic.lib.gui.GuiContainerBase;
import com.builtbroken.atomic.lib.gui.ISlotRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 5/30/2018.
 */
public class MachineSlot extends Slot implements ISlotRender
{
    protected Color edgeColor = null;

    public MachineSlot(IInventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    public MachineSlot setColor(Color color)
    {
        edgeColor = color;
        return this;
    }

    @Override
    public void renderSlotOverlay(Gui gui, int x, int y)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(GuiContainerBase.GUI_COMPONENTS);
        if(edgeColor != null)
        {
            GL11.glColor4f(edgeColor.getRed() / 255f, edgeColor.getGreen() / 255f, edgeColor.getBlue() / 255f, edgeColor.getAlpha() / 255f);
            gui.drawTexturedModalRect(x, y, 0, 0, 18, 18);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            gui.drawTexturedModalRect(x + 1, y + 1, 1, 1, 16, 16);
        }
        else
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            gui.drawTexturedModalRect(x, y, 0, 0, 18, 18);
        }
        if (!getHasStack())
        {
            drawIcon(gui, x, y);
        }
    }

    protected void drawIcon(Gui gui, int x, int y)
    {

    }
}
