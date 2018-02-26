package systems.tracethreat.awscore.ec2.remote.status

import scaldi.akka.AkkaInjectable
import akka.actor.ActorLogging
import akka.actor.Actor
import scaldi.Injector

object FeedStatusActorMessage{
    sealed trait FeedStatusActorMessage 
    case class GetFeedStatusActorStream(feedStatus : FeedStatusActorMessage) extends  
}

class FeedStatusActor(implicit inj : Injector)  extends Actor with AkkaInjectable with ActorLogging {
  l
}