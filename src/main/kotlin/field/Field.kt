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

package field

import move.MoveType


/**
 * field.Field

 * Handles everything that has to do with the field, such
 * as storing the current state and performing calculations
 * on the field.

 * @author Jim van Eeden <jim></jim>@riddles.io>
 */

data class Point(var x: Int, var y: Int) {
  fun next(move: MoveType): Point {
    return when (move) {
      MoveType.LEFT -> Point(x - 1, y)
      MoveType.RIGHT -> Point(x + 1, y)
      MoveType.UP -> Point(x, y - 1)
      MoveType.DOWN -> Point(x, y + 1)
      else -> {
        Point(x, y)
      }
    }
  }
}

enum class FieldType {
  EMPTY, WALL, MY_HEAD, ENEMY_HEAD
}

class Field {

  var myId = 0
  var width = 0
  var height = 0
  private var field: Array<Array<FieldType>> = emptyArray()

  var myPosition: Point = Point(0, 0)
    private set

  /**
   * Initializes and clears field
   * @throws Exception exception
   */
  fun initField() {
    this.field = Array(width) { Array(height, { FieldType.EMPTY }) }
  }

  /**
   * Clears the field
   */
  private fun clearField() {
    for (y in 0..this.height - 1) {
      for (x in 0..this.width - 1) {
        this.field[x][y] = FieldType.EMPTY
      }
    }
  }

  /**
   * Parse field from comma separated String
   * @param s input from engine
   */
  fun parseFromString(s: String) {
    clearField()

    val split = s.split(",")
    var x = 0
    var y = 0

    split.forEach {
      field[x][y] = when (it) {
        "." -> FieldType.EMPTY
        "x" -> FieldType.WALL
        myId.toString() -> {
          myPosition = Point(x, y)
          FieldType.MY_HEAD
        }
        else -> FieldType.ENEMY_HEAD
      }

      if (++x == this.width) {
        x = 0
        y++
      }
    }
  }

  fun getValidMoves(point: Point): List<MoveType> {
    return MoveType.values().filter {
      val next = point.next(it)
      isValid(next) && field[next.x][next.y] == FieldType.EMPTY
    }
  }

  fun isValid(point: Point): Boolean {
    return point.x in 0..(width - 1) && point.y in 0..(height - 1)
  }
}
