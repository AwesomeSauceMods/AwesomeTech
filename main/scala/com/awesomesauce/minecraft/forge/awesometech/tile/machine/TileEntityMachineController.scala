package com.awesomesauce.minecraft.forge.awesometech.tile.machine

import com.awesomesauce.minecraft.forge.awesometech.api.{TMachineBorder, TMachine}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

class TileEntityMachineController extends TileEntity with IInventory with TMachine {
  var awesomeBuffer = 0
  var checkCounter = 400
  var formed: Boolean = false
  var xOffset: Int = 0
  var xLength: Int = 0
  var zOffset: Int = 0
  var zLength: Int = 0
  var borders: scala.collection.mutable.Set[TMachineBorder] = scala.collection.mutable.Set[TMachineBorder]()
  override def updateEntity(): Unit = {
    if (checkCounter > 0) {
      checkCounter -= 1
      return
    }
    if (formed)
    {
      checkCounter = 600
    }
    else
    {
      checkCounter = 400
    }
    if (worldObj.getTileEntity(xCoord, yCoord+1, zCoord).isInstanceOf[TMachineBorder])
    {
      if (worldObj.getTileEntity(xCoord+1, yCoord, zCoord).isInstanceOf[TMachineBorder] && !worldObj.getTileEntity(xCoord-1, yCoord,zCoord).isInstanceOf[TMachineBorder])
      {
        xOffset = 1
      }
      else if (worldObj.getTileEntity(xCoord-1, yCoord, zCoord).isInstanceOf[TMachineBorder] && !worldObj.getTileEntity(xCoord+1, yCoord, zCoord).isInstanceOf[TMachineBorder])
      {
        xOffset = -1
      }
      else
      {
        xOffset = 0
      }
      if (worldObj.getTileEntity(xCoord, yCoord, zCoord+1).isInstanceOf[TMachineBorder] && !worldObj.getTileEntity(xCoord, yCoord,zCoord-1).isInstanceOf[TMachineBorder])
      {
        zOffset = 1
      }
      else if (worldObj.getTileEntity(xCoord, yCoord, zCoord-1).isInstanceOf[TMachineBorder] && !worldObj.getTileEntity(xCoord, yCoord, zCoord+1).isInstanceOf[TMachineBorder])
      {
        zOffset = -1
      }
      else
      {
        zOffset = 0
      }
    }
    if (xOffset == 0 || zOffset == 0)
    {
      return
    }
    xLength = xOffset
    zLength = xOffset
    while (worldObj.getTileEntity(xCoord+xLength, yCoord, zCoord).isInstanceOf[TMachineBorder])
    {
      xLength += 1
    }
    while (worldObj.getTileEntity(xCoord, yCoord, zCoord+zLength).isInstanceOf[TMachineBorder])
    {
      zLength += 1
    }
    for (ax <- Range(xCoord, xCoord+xLength+1))
    {
      for (az <- Range(zCoord, zCoord+zLength+1))
      {
        if (!(ax == xCoord && az == zCoord)) {
          if (worldObj.getTileEntity(ax, yCoord, az).isInstanceOf[TMachineBorder]) {
            val border = worldObj.getTileEntity(ax, yCoord, az).asInstanceOf[TMachineBorder]
            borders.add(border)
          }
          else
          {
            return
          }
        }
      }
    }

  }
  /*
   * Begin TMachine
   */
  def getX = xCoord
  def getY = yCoord
  def getZ = zCoord
  def drainFluid(fluid: FluidStack, simulate: Boolean): Boolean = false
  def drainItem(item: ItemStack, simulate:Boolean): Boolean = false
  def drainRF(amount:Int, simulate: Boolean): Int = 0
  def insertFluid(fluid: FluidStack, simulate: Boolean): Boolean = false
  def insertItem(item: ItemStack, simulate: Boolean): Boolean = false
  def insertRF(amount:Int, simulate: Boolean): Int = 0
  /*
   * End TMachine
   */
  /*
   * Begin IInventory
   */
  def openInventory() = {}
  def closeInventory() = {}
  def getInventoryName = "Machine Controller"
  def getInventoryStackLimit = 64
  def getSizeInventory = 1
  def getStackInSlot(i:Int) = null
  def getStackInSlotOnClosing(i:Int) = null
  def hasCustomInventoryName = false
  def isItemValidForSlot(i:Int, stack: ItemStack): Boolean = {
    for (i <- OreDictionary.getOres("ingotAwesomeite").toArray)
    {
      if (stack.isItemEqual(i.asInstanceOf[ItemStack]))
        return true
    }
    false
  }
  def decrStackSize(slot: Int, amount: Int): ItemStack = null
  def isUseableByPlayer(i:EntityPlayer) = true
  def setInventorySlotContents(i:Int, stack: ItemStack) = {
    for (i <- Range(0, stack.stackSize))
    {
      awesomeBuffer += 1
    }
  }
  /*
   * End IInventory
   */
}
