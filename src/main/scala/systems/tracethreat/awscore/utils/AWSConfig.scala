package systems.tracethreat.awscore.utils

import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

object AWSConfig {
  def readFileConfig(): Config = { 
    val config = ConfigFactory.load("aws.conf");
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

}