package utils

import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play.WsScalaTestClient
import play.api.libs.ws.WSClient

trait BaseIntergrationSpec extends BaseSpec with WsScalaTestClient with GuiceOneServerPerSuite {

  implicit val client = app.injector.instanceOf(classOf[WSClient])

}
