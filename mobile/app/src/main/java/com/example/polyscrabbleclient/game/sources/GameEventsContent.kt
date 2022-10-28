package com.example.polyscrabbleclient.game.sources

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName
import com.example.polyscrabbleclient.lobby.sources.GameToken

data class Tile(
    val letterMultiplicator: Int,
    val wordMultiplicator: Int,
    val letterObject: Letter
)

data class Letter(
    val char: String,
    val value: Int
)

data class LightPlayer(
    val name: String,
    val points: Int,
    val letterRack: ArrayList<Letter>,
)

data class Position(
    val x: Int,
    val y: Int
)

enum class Direction(value: String) {
    @SerializedName("H")
    Horizontal("H"),

    @SerializedName("V")
    Vertical("V"),
}

data class PlacementSetting(
    val x: Int, // Column
    val y: Int, // Row
    val direction: Direction
) {
    fun adjustPlacementSetting(): PlacementSetting {
        // Indexes have to be adjusted for server board
        return PlacementSetting(x-1, y-1, direction)
    }
}

data class OnlineAction(
    val type: OnlineActionType,
    val placementSettings: PlacementSetting? = null,
    val letters: String? = null,
    val letterRack: ArrayList<Letter>? = null,
    val position: Position? = null
)

// No Union types in Kotlin
enum class OnlineActionType(value: String) {
    // OnlineActionType
    @SerializedName("place")
    Place("place"),

    @SerializedName("exchange")
    Exchange("exchange"),

    @SerializedName("pass")
    Pass("pass"),

    // OnlineMagicCardActionType
    @SerializedName("splitPoints")
    SplitPoints("splitPoints"),

    @SerializedName("exchangeALetter")
    ExchangeALetter("exchangeALetter"),

    @SerializedName("placeBonus")
    PlaceBonus("placeBonus"),

    @SerializedName("exchangeHorse")
    ExchangeHorse("exchangeHorse"),

    // TODO : ADD OTHERS
}


// Warning : Events Data Classes have to match the backend corresponding interfaces

typealias RemainingTime = Int

typealias StartTime = Int

data class GameState(
    val players: ArrayList<LightPlayer>,
    val activePlayerIndex: Int,
    val grid: ArrayList<ArrayList<Tile>>,
    val lettersRemaining: Int,
    val isEndOfGame: Boolean,
    val winnerIndex: ArrayList<Int>,
)

// TODO
typealias TransitionGameState = Nullable

typealias JoinGame = GameToken

typealias NextAction = OnlineAction

// TODO
typealias Disconnect = Nullable
