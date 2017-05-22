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

import player.Player
import java.util.*

/**
 * bot.BotParser

 * Class that will keep reading output from the engine.
 * Will either update the bot state or get actions.

 * @author Jim van Eeden <jim></jim>@riddles.io>
 */

class BotParser(val bot: BotStarter) {

  private val scan: Scanner = Scanner(System.`in`)

  private val currentState: BotState = BotState()

  /**

   * Run will keep reading output from the engine.
   * Will either update the bot state or get actions.
   */
  internal fun run() {
    while (scan.hasNextLine()) {
      val line = scan.nextLine()

      if (line.length == 0) continue

      val parts = line.split(" ")
      when (parts[0]) {
        "settings" -> parseSettings(parts[1], parts[2])
        "update" -> if (parts[1] == "game") {
          parseGameData(parts[2], parts[3])
        }
        "action" -> if (parts[1] == "move") {
          val move = this.bot.doMove(this.currentState)

          println(move.toString())
        }
        else -> System.err.println("Unknown command")
      }
    }
  }

  /**
   * Parses all the game settings given by the engine
   * @param key Type of setting given
   * *
   * @param value Value
   */
  private fun parseSettings(key: String, value: String) {
    try {
      when (key) {
        "timebank" -> {
          val time = value.toInt()
          this.currentState.maxTimebank = time
          this.currentState.timebank = time
        }
        "time_per_move" -> this.currentState.timePerMove = value.toInt()
        "player_names" -> {
          val playerNames = value.split(",")
          for (playerName in playerNames) {
            this.currentState.players.put(playerName, Player(playerName))
          }
        }
        "your_bot" -> this.currentState.myName = value
        "your_botid" -> {
          val myId = Integer.parseInt(value)
          this.currentState.field.myId = myId
        }
        "field_width" -> this.currentState.field.width = value.toInt()
        "field_height" -> this.currentState.field.height = value.toInt()
        "max_rounds" -> this.currentState.maxRound = value.toInt()
        else -> System.err.println(String.format(
            "Cannot parse settings input with key '%s'", key))
      }
    } catch (e: Exception) {
      System.err.println(String.format(
          "Cannot parse settings value '%s' for key '%s'", value, key))
      e.printStackTrace()
    }
  }

  /**
   * Parse data about the game given by the engine
   * @param key Type of game data given
   * *
   * @param value Value
   */
  private fun parseGameData(key: String, value: String) {
    try {
      when (key) {
        "round" -> this.currentState.roundNumber = value.toInt()
        "field" -> {
          this.currentState.field.initField()
          this.currentState.field.parseFromString(value)
        }
        else -> System.err.println(String.format(
            "Cannot parse game data input with key '%s'", key))
      }
    } catch (e: Exception) {
      System.err.println(String.format(
          "Cannot parse game data value '%s' for key '%s'", value, key))
      e.printStackTrace()
    }
  }
}