package com.awesomesauce.minecraft.forge.awesometech

import com.awesomesauce.minecraft.forge.awesometech.tile.machine.TileEntityMachineController
import com.awesomesauce.minecraft.forge.core.components.AwesomeSauceComponents
import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.{Mod, ModMetadata}
import net.minecraft.block.Block
import net.minecraft.block.material.Material

@Mod(modid = "AwesomeTech", name = "AwesomeTech", modLanguage = "scala")
object AwesomeTech extends TAwesomeSauceMod {

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

  @Mod.Metadata("AwesomeTech")
  var metadata: ModMetadata = null

  var machineInput: Block = null
  var machineOutput: Block = null
  var machineBorder: Block = null
  var machineGlassBorder : Block = null
  var machineRFAcceptor: Block = null
  var machineController: Block = null

  var machineCrafter: Block = null
  def init() = {
    //machineBorder = ItemUtil.makeBlock(this, "machineBorder", Material.iron, () => new TileEntityMachineBorder)
    machineController = ItemUtil.makeBlock(this, "machineController", Material.iron, () => new TileEntityMachineController)
  }

  def preInit() = {

  }

  def postInit() = {


  }
}
