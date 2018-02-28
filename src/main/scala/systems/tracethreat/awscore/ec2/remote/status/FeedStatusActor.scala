package systems.tracethreat.awscore.ec2.remote.status

import scaldi.akka.AkkaInjectable
import akka.actor.ActorLogging
import akka.actor.Actor
import scaldi.Injector
import com.amazonaws.regions.Regions
import com.amazonaws.regions.Region
import systems.tracethreat.awscore.utils.AWSConfig
import awscala._, ec2._

object FeedStatusActorMessage {
  sealed trait FeedStatusActorMessage
  //case class GetFeedStatusActorStream(feedStatus : FeedStatusActorMessage) extends  
}

class FeedStatusActor(implicit inj: Injector) extends Actor with AkkaInjectable with ActorLogging {
  implicit val ec2 = EC2.at(Region.getRegion(Regions.AP_SOUTHEAST_1))

  import scala.concurrent._
  import scala.concurrent.ExecutionContext.Implicits.global
  val awsConfig = AWSConfig
  val config = awsConfig.readFileConfig()
  val amiNo: List[String] = awsConfig.getAllInstances(config)
  val keyGroup = awsConfig.getInstanceGroup(config)
  for (ami <- amiNo) {
    Future(ec2.runAndAwait(ami, ec2.keyPairs.head)).flatMap {
      case inst: Instance => inst.withKeyPair(new java.io.File("key_pair_file")) { i =>
        i.ssh { ssh =>
          ssh.exec("ls -la").right.map { result =>
            log.info("Instance ID : " + inst.instanceId + ", Result : " + result.stdOutAsString())
          }
        }
        Future(i)
      }
      case _ =>
        Future(log.info("Cannot found instance "))
    }
  } // for

}