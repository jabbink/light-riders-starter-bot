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

import field.Field;
import move.MoveType
import player.Player

/**
 * bot.BotState

 * Keeps track of the complete game state, such as the field
 * and the players.

 * @author Jim van Eeden <jim></jim>@riddles.io>
 */

class BotState {

  var maxTimebank = 0
  var timePerMove = 0
  var maxRound = 0

  var roundNumber = 0
  var timebank = 0
  var myName: String? = null
  val players = mutableMapOf<String, Player>()

  val field = Field()

  var lastDirection: MoveType? = null
}