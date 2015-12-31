import play.api.{GlobalSettings, Application}
import anorm._
import play.api.db.DB
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by vramos on 12/31/15.
  */
object Global extends GlobalSettings {
  override def onStart(app: Application) = {
    import play.api.Play.current

    DB.withConnection { implicit connection =>
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Falco', 'falco@gmail')").execute()
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Fox', 'fox@gmail')").execute()
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Marth', 'marth@gmail')").execute()
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Peach', 'peach@gmail')").execute()
    }
  }

  override def onHandlerNotFound(request: RequestHeader) = {
    Future(Results.NotFound(views.html.notFound()))
  }
}
