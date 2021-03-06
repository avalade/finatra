package com.twitter.finatra.http.tests.integration.darktraffic.main

import com.twitter.conversions.time._
import com.twitter.finagle.http.Method.{Delete, Post}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.modules.DarkTrafficFilterModule
import com.twitter.util.Duration

object DarkServerTestModule extends DarkTrafficFilterModule {

  /**
   * Function to determine if the request should be "sampled", e.g.
   * sent to the dark service.
   */
  override val enableSampling: Request => Boolean = { request =>
    request.method match {
      case Post | Delete => false
      case _ => true
    }
  }
  override val acquisitionTimeout: Duration = 100.millis
  override val requestTimeout: Duration = 100.millis
}
