package controllers

import play.api.mvc._
import javax.inject._
import models.User
import auth.UserAuthenticater
import utils.extensions.RequestExtensions._
import play.api.libs.json.JsValue
import exceptions.Err

class LoginController @Inject() (
  val controllerComponents: ControllerComponents,
  val userAuthenticater: UserAuthenticater,
  val jsonParser: PlayBodyParsers
) extends BaseController {

  def login =
    Action(jsonParser.json) { request =>
      authenticate(request)
      Ok
    }

  def authenticate(request: Request[JsValue]): Either[Err, User] =
    userAuthenticater.authenticate(request)

}
