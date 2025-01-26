@file:Suppress("FunctionName")

package org.alexcawl.sculptor.contract.common

val Number.dp: Dp
    get() = Dp.Number(number = this.toFloat())

fun RoundedCorner(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp
): Shape = Shape.RoundedCorner.DPixel(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)

fun RoundedCorner(
    topStart: Int,
    topEnd: Int,
    bottomEnd: Int,
    bottomStart: Int
): Shape = Shape.RoundedCorner.Percent(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)

fun CutCorner(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp
): Shape = Shape.CutCorner.DPixel(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)

fun CutCorner(
    topStart: Int,
    topEnd: Int,
    bottomEnd: Int,
    bottomStart: Int
): Shape = Shape.CutCorner.Percent(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)
