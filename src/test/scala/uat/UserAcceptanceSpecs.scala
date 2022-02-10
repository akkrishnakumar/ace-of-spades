package uat

import utils.BaseIntergrationSpec
import scala.concurrent.Await
import scala.concurrent.duration._
import play.api.http.Status.OK
import utils.extensions.ResponseExtensionsSpec._

class UserAcceptanceSpecs extends BaseIntergrationSpec {

  "User" should "be able to see balance when logged in" in {
    // GIVEN
    val url   = "/login"
    val creds = """{ "uname": "Akhil", "pass": "Pass@123" }"""

    // WHEN
    val res = Await.result(
      wsUrl(url)
        .withHttpHeaders("Content-Type" -> "application/json")
        .post(creds),
      1.seconds
    )

    // THEN
    res.status shouldEqual OK
    res.cookieValue("player-name") shouldEqual "Akhil"
    res.cookieValue("player-balance") shouldEqual "1000.0"

  }

}
