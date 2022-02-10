package utils.extensions

import play.api.libs.ws.WSResponse

object ResponseExtensionsSpec {

  implicit class ResponseOps(self: WSResponse) {

    def cookieValue(name: String) =
      self.cookie(name) match {
        case Some(cookie) => cookie.value
        case None         => ""
      }

  }

}
