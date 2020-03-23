package io.stoorx.katrix

import io.stoorx.katrix.views.MutableColumnView
import io.stoorx.katrix.views.MutableMatrixView
import io.stoorx.katrix.views.MutableRowView

interface MutableMatrix<T> : Matrix<T> {
    operator fun set(row: Int, column: Int, element: T)
    operator fun set(index: MatrixIndex, element: T)

    override fun rowView(row: Int): MutableRowView<T>
    override fun columnView(column: Int): MutableColumnView<T>

    fun transpose()

    override fun view(
        upperLeft: MatrixIndex,
        lowerRight: MatrixIndex,
        step: MatrixIndex
    ): MutableMatrixView<T>

    override fun slice(
        upperLeft: MatrixIndex,
        lowerRight: MatrixIndex,
        step: MatrixIndex
    ): Matrix<T>
}