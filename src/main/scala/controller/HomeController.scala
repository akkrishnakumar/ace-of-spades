package controllers

import play.api.mvc.BaseController
import javax.inject._
import play.api.mvc.ControllerComponents

@Singleton
class HomeController @Inject() (val controllerComponents: ControllerComponents)
  extends BaseController {

  def index() =
    Action {
      Ok("It works")
    }

}
