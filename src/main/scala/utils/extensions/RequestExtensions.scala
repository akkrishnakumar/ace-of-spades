package utils.extensions

import models.Credentials
import play.api.mvc.Request
import play.api.libs.json.Json
import play.api.mvc.AnyContent
import play.api.libs.json.JsValue

object RequestExtensions {

  implicit class RequestOps(self: Request[JsValue]) {

    def credentials: Option[Credentials] =
      uNameFrom(self) -> passFrom(self) match {
        case (Some(uname), Some(pass)) => Some(Credentials(uname, pass))
        case _                         => None
      }

    private def uNameFrom(request: Request[JsValue]) =
      (request.body \ "uname").asOpt[String]

    private def passFrom(request: Request[JsValue]) =
      (request.body \ "pass").asOpt[String]

  }

}
