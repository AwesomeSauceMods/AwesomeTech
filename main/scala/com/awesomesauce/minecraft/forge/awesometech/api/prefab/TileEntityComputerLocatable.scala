package com.awesomesauce.minecraft.forge.awesometech.api.prefab

import com.awesomesauce.minecraft.forge.awesometech.api.TComputerLocatable
import net.minecraft.tileentity.TileEntity

abstract class TileEntityComputerLocatable extends TileEntity with TComputerLocatable {
  def getX = xCoord

  def getY = yCoord

  def getZ = zCoord
}
