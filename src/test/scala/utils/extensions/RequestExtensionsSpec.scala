package utils.extensions

import utils.BaseSpec
import play.api.test.FakeRequest
import models.Credentials
import RequestExtensions._
import play.api.mvc.AnyContent
import play.api.mvc.Request
import play.api.libs.json.JsValue
import play.api.libs.json.Json

class RequestExtensionsSpec extends BaseSpec {

  "Request Ops" should "extract Credentials from request" in {
    val request = fakeRequest("""{ "uname": "Akhil", "pass": "Pass@123"}""")
    request.credentials shouldEqual Some(Credentials("Akhil", "Pass@123"))
  }

  it should "return None if the required properties are not found" in {
    val request = fakeRequest("""{}""")
    request.credentials shouldEqual None
  }

  def fakeRequest(body: String) =
    FakeRequest().withBody(Json.parse(body)).asInstanceOf[Request[JsValue]]

}
