package com.awesomesauce.minecraft.forge.awesometech

import com.awesomesauce.minecraft.forge.awesometech.item.ItemToolBinder
import com.awesomesauce.minecraft.forge.awesometech.tile.computer.{TileEntityComputerCPU, TileEntityComputerFunctionStart, TileEntityStoneBlockPlacer}
import com.awesomesauce.minecraft.forge.core.components.AwesomeSauceComponents
import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.item.ItemDescription
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.{Mod, ModMetadata}
import net.minecraft.block.Block
import net.minecraft.block.material.Material

@Mod(modid = "AwesomeTech", name = "AwesomeTech", modLanguage = "scala")
object AwesomeTech extends TAwesomeSauceMod {

  @Mod.Metadata("AwesomeTech")
  var metadata: ModMetadata = null
  var stoneComputerCPU: Block = null
  var ironComputerCPU: Block = null
  var diamondComputerCPU: Block = null
  var enderComputerCPU: Block = null
  var computerFunctionStart: Block = null
  var computerStoneBlockPlacer: Block = null
  var computerImportItem: Block = null
  var computerExportItem: Block = null
  var toolBinder: ItemDescription = null
  var toolReader: ItemDescription = null
  var toolDataStorage: ItemDescription = null

  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)

  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)

  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)

  def getModID = "AwesomeTech"

  def getModName = "AwesomeTech"

  def getTextureDomain = "awesometech"

  def getTabIconItem = () => AwesomeSauceComponents.ingotPureAwesomeite

  def init() = {
    stoneComputerCPU = ItemUtil.makeBlock(this, "stoneComputerCPU", Material.rock, () => new TileEntityComputerCPU(3, 3, 1))
    ironComputerCPU = ItemUtil.makeBlock(this, "ironComputerCPU", Material.iron, () => new TileEntityComputerCPU(5, 10, 3))
    diamondComputerCPU = ItemUtil.makeBlock(this, "diamondComputerCPU", Material.iron, () => new TileEntityComputerCPU(20, 30, 7))
    enderComputerCPU = ItemUtil.makeBlock(this, "enderComputerCPU", Material.iron, () => new TileEntityComputerCPU(100, 100, 100))

    computerFunctionStart = ItemUtil.makeBlock(this, "computerFunctionStart", Material.iron, () => new TileEntityComputerFunctionStart)

    computerStoneBlockPlacer = ItemUtil.makeBlock(this, "computerStoneBlockPlacer", Material.rock, () => new TileEntityStoneBlockPlacer)

    toolBinder = ItemUtil.makeItem(this, "toolBinder", new ItemToolBinder).asInstanceOf[ItemDescription].setShowUsage(true).addUsage("Right Click", "On a cpu, stores the cpu, on a other block, binds the block to the cpu.")
  }

  def preInit() = {

  }

  def postInit() = {


  }
}
