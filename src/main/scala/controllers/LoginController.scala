package controllers

import play.api.mvc._
import javax.inject._
import models.User
import auth.UserAuthenticater
import utils.extensions.RequestExtensions._
import play.api.libs.json.JsValue
import exceptions.Err
import exceptions.AuthenticationFailed
import models.AuthenticatedUser

class LoginController @Inject() (
  val controllerComponents: ControllerComponents,
  val userAuthenticater: UserAuthenticater,
  val jsonParser: PlayBodyParsers
) extends BaseController {

  def login =
    Action(jsonParser.json) { request =>
      authenticate(request)
        .fold(toErrorResponse, toSuccessResponse)
    }

  def authenticate(request: Request[JsValue]): Either[Err, User] =
    userAuthenticater.authenticate(request)

  def toSuccessResponse(user: User) =
    Ok.withCookies(createCookies(user.asInstanceOf[AuthenticatedUser]): _*)

  def toErrorResponse(err: Err) =
    err match {
      case AuthenticationFailed(reason, user) =>
        Unauthorized(s"Reason: $reason. User: ${user.name}")
      case _ => NotFound
    }

  def createCookies(user: AuthenticatedUser): List[Cookie] =
    List(
      Cookie("player-name", user.name),
      Cookie("player-balance", user.balance.toString())
    )

}
