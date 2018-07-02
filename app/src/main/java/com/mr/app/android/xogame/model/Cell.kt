package com.mr.app.android.xogame.model

import android.text.TextUtils

/**
 * Created by Marioara Rus on 6/30/2018.
 */
class Cell(var player: Player?) {
    fun isEmpty() = player == null || TextUtils.isEmpty(player?.value)
}