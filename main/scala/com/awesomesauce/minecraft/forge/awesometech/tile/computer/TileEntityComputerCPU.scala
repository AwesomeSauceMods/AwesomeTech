package com.awesomesauce.minecraft.forge.awesometech.tile.computer

import com.awesomesauce.minecraft.forge.awesometech.api._
import com.awesomesauce.minecraft.forge.awesometech.api.prefab.TileEntityComputerLocatable


class TileEntityComputerCPU(maxEffectors: Int, maxData: Int, maxRange: Int) extends TileEntityComputerLocatable with TComputerCPU {
  val data = scala.collection.mutable.Map[String, TComputerData]()
  val effectors = scala.collection.mutable.Map[String, TComputerEffector]()

  def addData(name: String, nudata: TComputerData): Boolean =
    if (data.size < maxData || data.contains(name)) {
      data(name) = nudata
      true
    }
    else {
      false
    }

  def addEffector(name: String, effector: TComputerEffector): Boolean =
    if (effectors.size < maxEffectors || effectors.contains(name)) {
      effectors(name) = effector
      true
    }
    else {
      false
    }

  def callEffector(name: String): Boolean =
    if (effectors.contains(name)) {
      effectors(name).activate()
    }
    else {
      false
    }

  def getData(name: String): TComputerData =
    if (data.contains(name)) {
      data(name)
    }
    else {
      null
    }
}
