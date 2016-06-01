package ru.hse.transformer

sealed trait Grammar {
  sealed abstract class Symbol(val name: Char)
  case class TerminalSymbol(override val name: Char) extends Symbol(name)
  case class NonTerminalSymbol(override val name: Char) extends Symbol(name)
  case class Rule(leftHandSide : NonTerminalSymbol, rightHandSide : Array[Symbol])
  val startingSymbol : Symbol
  val rules : IndexedSeq[Rule]
  def getSymbol(c : Char) = if (c.isUpper) NonTerminalSymbol(c) else TerminalSymbol(c)
}

object Grammar {
  private class GrammarImpl(startingSymbolChar : Char) extends Grammar {
    override var startingSymbol: Symbol = getSymbol(startingSymbolChar)
    override val rules: IndexedSeq[Rule] = _
  }
}
