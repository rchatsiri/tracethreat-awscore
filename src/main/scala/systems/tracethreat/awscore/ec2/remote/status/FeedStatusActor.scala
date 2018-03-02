package systems.tracethreat.awscore.ec2.remote.status

import scaldi.akka.AkkaInjectable
import akka.actor.ActorLogging
import akka.actor.Actor
import scaldi.Injector
import com.amazonaws.regions.Regions
import com.amazonaws.regions.Region
import systems.tracethreat.awscore.utils.AWSConfig
import awscala._, ec2._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Flow, Source }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.HttpResponse
import scala.concurrent.duration._
import akka.actor.Cancellable
import scala.util.Success
import scala.util.Failure

case object FeedRefreshStatus
case object FeedFailStatus

object FeedStatusActorMessage {
  sealed trait FeedStatusActorMessage
  //case class GetFeedStatusActorStream(feedStatus : FeedStatusActorMessage) extends  
}

class FeedStatusActor(implicit inj: Injector) extends Actor with AkkaInjectable with ActorLogging {

  val awsConf = AWSConfig
  val conf = awsConf.readFileConfig()

  val preiodConf = awsConf.getDuration(conf)
  val urlTargetConf = awsConf.getTargetName(conf)
  val actorNameConf = awsConf.getActorName(conf)

  val period = Duration.apply(preiodConf).asInstanceOf[FiniteDuration]

  var timerCancellable: Option[Cancellable] = None

  import scala.concurrent._
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val aeonServiceChecker: ActorSystem = ActorSystem(actorNameConf)
  implicit val meterializer = ActorMaterializer()

  def sendReqMsg() {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = urlTargetConf))

    timerCancellable = Some(
      context.system.scheduler.scheduleOnce(
        period, context.self, responseFuture))

  }

  override def preStart() = sendReqMsg()

  def receive = {
    case resp: Future[HttpResponse] =>
      val respMsg: Future[String] = resp.flatMap {
        case httpResp: HttpResponse => Future(httpResp.status.value)
      }
      respMsg.onComplete {
        case Success(res) => log.info("Refersh send msg : " + res)
        case Failure(_) => log.info("Error on system : " + sys.error("Found error on system."))
      }
      sendReqMsg()
  }

}