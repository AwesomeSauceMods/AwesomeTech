package com.awesomesauce.minecraft.forge.awesometech.api

trait TComputerCPU extends TComputerLocatable {
  def addData(name: String, nudata: TComputerData): Boolean

  def addEffector(name: String, effector: TComputerEffector): Boolean

  def callEffector(name: String): Boolean

  def getData(name: String): TComputerData
}
