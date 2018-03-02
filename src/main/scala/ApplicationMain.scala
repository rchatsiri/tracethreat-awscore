import scaldi.Module
import scaldi.Injectable._
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config
import scaldi.akka.AkkaInjectable
import systems.tracethreat.awscore.ec2.remote.status.FeedStatusActor
import systems.tracethreat.awscore.ec2.remote.status.FeedStatusActor
import systems.tracethreat.awscore.utils.ServicesDefine

class AkkaModule extends Module {
  bind[ActorSystem] to ActorSystem(ServicesDefine.BaseActorSystemService)
}

class ApplicationServiceModule extends Module {
  binding toProvider new FeedStatusActor
}

object ApplicationMain extends App {

  implicit val appModule = new ApplicationServiceModule  :: new AkkaModule  // :: new MalwareInfoModule :: new MalwareStatisticInfoModule :: new AkkaModule 
  //Initial listener
  val conf: Config = ConfigFactory.load()
  implicit val system = inject[ActorSystem]
  system.logConfiguration
  //Malware information Actor 
  system.actorOf(AkkaInjectable.injectActorProps[FeedStatusActor], ServicesDefine.FeedStatusService)
}