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

package move

import java.util.*

/**
 * move.MoveType

 * Enumerator for all move types

 * @author Jim van Eeden <jim></jim>@riddles.io>
 */
enum class MoveType {
  UP,
  DOWN,
  LEFT,
  RIGHT,
  PASS;

  val opposite: MoveType
    get() {
      when (this) {
        UP -> return DOWN
        DOWN -> return UP
        LEFT -> return RIGHT
        RIGHT -> return LEFT
        else -> return this
      }
    }

  override fun toString(): String {
    return this.name.toLowerCase()
  }
}
