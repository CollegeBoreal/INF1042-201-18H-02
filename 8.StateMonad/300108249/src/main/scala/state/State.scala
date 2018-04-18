import scalaz._
import Scalaz._


object StateMonad extends App {
  case class Trade(info: String)
  type Store = List[Trade]


  val newTrade = (newTrade: Trade) ⇒

    for (

      _ ← log("New Trade");

      accepted ← accept(newTrade);

      _ ← log("Hedging New Trade");

      hedged ← hedge(newTrade);

      _ ← log("Validating portfolio");

      portfolio ← validatePortfolio;

      _ ← log("New trade processed")

    ) yield (accepted.liftFailNel |@| hedged.liftFailNel |@| portfolio.liftFailNel) {_ + _ + _}



  println("Haven't done anything yet!")



  // assume some existing state

  var globalState: Store = Nil



  // exercise the newTrade function with the existing state

  val (newState, validation) = newTrade(Trade("Big Trade"))(globalState)



  // assign the new state to our global state if the validation says its ok

  globalState = validation.fold(failures ⇒ { println(failures); globalState }, msg ⇒ newState)



  println("Store contains " + globalState)



  // does nothing but print a messages and return the state its given

  def log(m: String) = state[Store, Unit](s ⇒ (s, println(m)))



  // accepts a trade putting it into the store

  def accept(newTrade: Trade) = state[Store, Validation[String, String]](s ⇒ (newTrade :: s, "trade accepted".success))



  // hedge against the new trade - apparently its all the rage

  def hedge(against: Trade) = state[Store, Validation[String, String]](s ⇒ (Trade("Hedge Trade against " + against) :: s, "hedge trade step".success))



  // validate the portfolio doing nothing with the state

  def validatePortfolio = state[Store, Validation[String, String]](s ⇒ {

    if (s.size > 10) (s, "Portolio is too big".fail)

    else (s, "All ok".success)
