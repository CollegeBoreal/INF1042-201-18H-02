package state

case class Player(name: String, move: Move) {
  override def toString = name
}
