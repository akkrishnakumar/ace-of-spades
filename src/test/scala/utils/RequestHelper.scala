package utils

import play.api.mvc.Request
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.test

object RequestHelper {

  def sampleRequest = fakeRequest("""{ "uname": "Akhil", "pass": "Pass@123"}""")

  def fakeRequest(body: String): Request[JsValue] =
    test.FakeRequest().withBody(Json.parse(body)).asInstanceOf[Request[JsValue]]

}
