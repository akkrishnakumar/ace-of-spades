package auth

import javax.inject._
import com.google.inject.ImplementedBy
import models.User
import models.AuthenticatedUser
import models.UnAuthenticatedUser
import models.Credentials
import play.api.mvc.Request
import play.api.mvc.AnyContent
import play.api.libs.json.JsValue
import utils.extensions.RequestExtensions._
import exceptions.Err
import exceptions.AuthenticationFailed
import exceptions.InValidCredentials

@ImplementedBy(classOf[DefaultUserAuthenticater])
trait UserAuthenticater {

  def authenticate(request: Request[JsValue]): Either[Err, User]

}

class DefaultUserAuthenticater extends UserAuthenticater {

  val dataStore = Map(
    "Akhil_Pass@123" -> ("Akhil", 1000.00)
  )

  override def authenticate(request: Request[JsValue]): Either[Err, User] =
    request.credentials match {
      case Some(creds) =>
        fetchUser(creds) match {
          case Some(user) => Right(AuthenticatedUser(user._1, user._2))
          case None =>
            Left(AuthenticationFailed("Not Authorized", UnAuthenticatedUser(creds.uname)))
        }
      case None =>
        Left(InValidCredentials("Invalid Credentials"))
    }

  private def fetchUser(creds: Credentials) =
    dataStore.get(s"${creds.uname}_${creds.pass}")

}
