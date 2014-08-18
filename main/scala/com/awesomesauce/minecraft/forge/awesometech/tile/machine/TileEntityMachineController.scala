package com.awesomesauce.minecraft.forge.awesometech.tile.machine

import cofh.api.energy.{EnergyStorage, IEnergyHandler}
import com.awesomesauce.minecraft.forge.awesometech.api.{TMachine, TMachineComponent}
import com.awesomesauce.minecraft.forge.core.lib.MultiFluidTank
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids.FluidStack

class TileEntityMachineController extends TileEntity with TMachine with IEnergyHandler {
  var storage: EnergyStorage = new EnergyStorage(100000)
  var machineTanks = new MultiFluidTank(5, 3000)
  var checkCounter = 400
  var xLength: Int = 0
  var yLength: Int = 0
  var zLength: Int = 0

  override def updateEntity(): Unit = {
    if (checkCounter > 0) {
      checkCounter -= 1
      return
    }
    checkCounter = 400
    for (x <- Range(xCoord - xLength, xCoord + xLength)) {
      for (y <- Range(yCoord - yLength, yCoord + yLength)) {
        for (z <- Range(zCoord - zLength, zCoord + zLength)) {
          val te = worldObj.getTileEntity(x, y, z)
          if (te.isInstanceOf[TMachineComponent]) {
            te.asInstanceOf[TMachineComponent].setMachine(this)
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

  def drainFluid(fluid: FluidStack, simulate: Boolean): FluidStack = machineTanks.drainFluid(fluid, simulate)

  def drainItem(item: ItemStack, simulate: Boolean): ItemStack = null

  def drainRF(amount: Int, simulate: Boolean): Int = storage.extractEnergy(amount, simulate)

  def insertFluid(fluid: FluidStack, simulate: Boolean): FluidStack = machineTanks.insertFluid(fluid, simulate)

  def insertItem(item: ItemStack, simulate: Boolean): ItemStack = null

  def insertRF(amount: Int, simulate: Boolean): Int = storage.receiveEnergy(amount, simulate)

  /*
   * End TMachine
   */
  /*
   * Start IEnergyHandler
   */
  def receiveEnergy(from: ForgeDirection, maxReceive: Int, simulate: Boolean): Int = storage.receiveEnergy(maxReceive, simulate)

  def extractEnergy(from: ForgeDirection, maxExtract: Int, simulate: Boolean): Int = storage.extractEnergy(maxExtract, simulate)

  def getEnergyStored(from: ForgeDirection): Int = storage.getEnergyStored

  def getMaxEnergyStored(from: ForgeDirection): Int = storage.getMaxEnergyStored

  def canConnectEnergy(from: ForgeDirection): Boolean = true

  /*
   * End IEnergyHandler
   */
}
