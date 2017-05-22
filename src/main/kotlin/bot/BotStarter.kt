// // Copyright 2016 riddles.io (developers@riddles.io)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot

import move.Move
import move.MoveType
import java.util.*

/**
 * bot.BotStarter

 * Magic happens here. You should edit this file, or more specifically
 * the makeTurn() method to make your bot do more than random moves.

 * @author Jim van Eeden <jim></jim>@riddles.io>
 */

class BotStarter {

  /**
   * Edit this method to make your bot smarter.
   * Currently returns a random, but valid move.
   * @return a Move object
   */
  fun doMove(state: BotState): Move {
    val moveType: MoveType

    val moves = state.field.getValidMoves(state.field.myPosition)
    if (moves.isNotEmpty()) {
      Collections.shuffle(moves)
      moveType = moves.first()
    } else {
      moveType = MoveType.PASS
    }

    if (moveType != MoveType.PASS) {
      state.lastDirection = moveType
    }

    return Move(moveType)
  }
}

/**
 * Main method for the bot. Creates a parser and runs it.
 */
fun main(args: Array<String>) {
  val parser = BotParser(BotStarter())
  parser.run()
}