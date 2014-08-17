package com.awesomesauce.minecraft.forge.awesometech.api

import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

trait TMachine {
  def drainRF(amount: Int, simulate: Boolean) : Int
  def drainFluid(fluid: FluidStack, simulate: Boolean): FluidStack

  def drainItem(item: ItemStack, simulate: Boolean): ItemStack

  def insertRF(amount: Int, simulate: Boolean): Int
  def insertFluid(fluid: FluidStack, simulate: Boolean): FluidStack

  def insertItem(item: ItemStack, simulate: Boolean): ItemStack
  def getX: Int
  def getY: Int
  def getZ: Int
}
