package controllers

import play.api.mvc._
import javax.inject._
import utils.extensions.RequestExtensions._

class LoginController @Inject() (
  val controllerComponents: ControllerComponents
) extends BaseController {

  def login =
    Action {
      Ok
    }

}
