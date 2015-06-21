package net.einsteinsci.betterbeginnings.client;

import net.einsteinsci.betterbeginnings.tileentity.TileEntityInfusionRepair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class InfusionRender extends TileEntitySpecialRenderer
{
	// private RenderEntityItem itemRender;
	private RenderManager renderManager;

	public InfusionRender(RenderManager rm)
	{
		renderManager = rm;
	}

	public void renderTool(TileEntityInfusionRepair te, double x, double y, double z)
	{
		ItemStack stack = te.stackTool();

		if (stack == null)
		{
			return;
		}

		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate(x, y, z);

		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.translate(1.5F, 0.3F, 0.5F); // Y
		GlStateManager.rotate(0F, 0F, 1F, 0F);
		GlStateManager.translate(-0.5F, 0F, 0.5F); // X & Z
		GlStateManager.rotate(0F, 0F, 1F, 0F);
		GlStateManager.translate(0D, 0D, 0F);

		EntityItem customItem = new EntityItem(te.getWorld(), te.getPos().getX(), te.getPos().getY(),
			te.getPos().getZ() + 1, stack);

		customItem.hoverStart = 0.0F;

		GlStateManager.scale(1.8F, 1.8F, 1.8F);

		GlStateManager.translate(-0.02f, 0.98f, -1.32f);

		GlStateManager.rotate(90.0f, 90.0F, 1.0F, 0.0F);

		renderManager.doRenderEntity(customItem, 0, 1, 0, 0, 0, false);

		GlStateManager.popMatrix();
		GlStateManager.disableAlpha();
		GlStateManager.pushMatrix();
		//GlStateManager.translate(0.5F, 1.8F, 0.5F);
		GlStateManager.rotate(0.0F, 1F, 0F, 1F);
		GlStateManager.popMatrix();
		GlStateManager.enableAlpha();
		GlStateManager.popMatrix();
	}

	public void renderIngredients(TileEntityInfusionRepair te, double x, double y, double z)
	{
		final float short_ = 0.4f;
		final float far = 1.15f;


		for (int storedIngredientID = 0; storedIngredientID < 8; storedIngredientID++)
		{
			ItemStack stack = te.stackInput(storedIngredientID);

			if (stack == null)
			{
				continue;
			}

			GlStateManager.pushMatrix();
			GlStateManager.color(1F, 1F, 1F, 1F);
			GlStateManager.translate(x, y, z);

			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(1.5F, 0.3F, 0.5F); // Y
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			GlStateManager.translate(-0.5F, 0F, 0.5F); // X & Z
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			GlStateManager.translate(0D, 0D, 0F);

			float degreesRot = (te.getTicksAge() * 90.0f) / 30.0f;
			float hoverPos = (float)(Math.sin(te.getTicksAge() * 0.5 * Math.PI / 20.0) * 0.08);

			EntityItem customItem = new EntityItem(te.getWorld(), te.getPos().getX(), te.getPos().getY(),
				te.getPos().getZ() + 1, stack);

			customItem.hoverStart = 0.0F;

			float xItem = 0.0f;
			float zItem = 0.0f;

			switch (storedIngredientID)
			{
				case 0:
					zItem = -far;
					xItem = -short_;
					break;
				case 1:
					zItem = -short_;
					xItem = -far;
					break;
				case 2:
					zItem = short_;
					xItem = -far;
					break;
				case 3:
					zItem = far;
					xItem = -short_;
					break;
				case 4:
					zItem = far;
					xItem = short_;
					break;
				case 5:
					zItem = short_;
					xItem = far;
					break;
				case 6:
					zItem = -short_;
					xItem = far;
					break;
				case 7:
					zItem = -far;
					xItem = short_;
					break;
			}

			GlStateManager.scale(0.6F, 0.6F, 0.6F);
			GlStateManager.translate(xItem, 2.0f + hoverPos, zItem);
			GlStateManager.rotate(degreesRot, 0.0f, 1.0f, 0.0f);

			GlStateManager.rotate(0.0f, 0.0f, 0.0f, 0.0F);

			renderManager.doRenderEntity(customItem, 0, 1, 0, 0, 0, false);

			GlStateManager.popMatrix();
			GlStateManager.disableAlpha();
			GlStateManager.pushMatrix();
			GlStateManager.rotate(0.0F, 1F, 0F, 1F);
			GlStateManager.popMatrix();
			GlStateManager.enableAlpha();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float param8, int param9)
	{
		renderTool((TileEntityInfusionRepair)tile, x, y, z);
		renderIngredients((TileEntityInfusionRepair)tile, x, y, z);
	}
}
