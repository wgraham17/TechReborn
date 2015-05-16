package techreborn.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import techreborn.Core;
import techreborn.blocks.BlockMachineBase;
import techreborn.client.GuiHandler;
import techreborn.client.TechRebornCreativeTab;
import techreborn.tiles.TileAlloySmelter;
import techreborn.tiles.TileAssemblingMachine;
import techreborn.tiles.TileBlastFurnace;
import techreborn.tiles.TileMachineCasing;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAssemblingMachine extends BlockMachineBase {

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFrontOn;

	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;

	public BlockAssemblingMachine(Material material)
	{
		super(material);
		setBlockName("techreborn.assemblingmachine");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon)
	{
		this.blockIcon = icon.registerIcon("techreborn:machine/machine_side");
		this.iconFront = icon.registerIcon("techreborn:machine/assembling_machine_front_off");
		this.iconFrontOn = icon.registerIcon("techreborn:machine/assembling_machine_front_off");
		this.iconTop = icon.registerIcon("techreborn:machine/assembling_machine_top");
		this.iconBottom = icon.registerIcon("techreborn:machine/machine_bottom");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TileAssemblingMachine();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!player.isSneaking())
			player.openGui(Core.INSTANCE, GuiHandler.assemblingmachineID, world, x, y,
					z);
		return true;
	}

	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		TileAssemblingMachine tileAssemblingMachine = (TileAssemblingMachine) blockAccess.getTileEntity(x, y, z);
		if(side == metadata && tileAssemblingMachine.crafter.currentRecipe != null){
			return this.iconFrontOn;
		}
		return metadata == 0 && side == 3 ? this.iconFront
				: side == 1 ? this.iconTop : 
					side == 0 ? this.iconBottom: (side == 0 ? this.iconTop
						: (side == metadata ? this.iconFront : this.blockIcon));

	}

}
