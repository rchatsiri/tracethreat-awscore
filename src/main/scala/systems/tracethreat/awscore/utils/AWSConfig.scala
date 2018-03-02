package systems.tracethreat.awscore.utils

import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

object AWSConfig {

  def readFileConfig(): Config = {
    val awsFallback = ConfigFactory.load("aws.conf")
    val config = ConfigFactory.load("application.conf").withFallback(awsFallback);
    config
  }

  def getInstanceGroup(config: Config): String = {
    val group = config.getString("aws.group.name")
    group
  }

  def getAllInstances(config: Config): List[String] = {
    val instances = config.getString("aws.group.instances")
    instances.split(":").toList
  }

  def getActorName(config: Config): String = {
    val actorName = config.getString("aws.actor.name")
    actorName
  }

  def getTargetName(config: Config): String = {
    val targetName = config.getString("aws.actor.target")
    targetName
  }

  def getDuration(config: Config): String = {
    val duration: String = config.getString("aws.actor.duration").toString()
    duration
  }

}