package io.stoorx.katrix.views

import io.stoorx.katrix.Matrix
import io.stoorx.katrix.MutableMatrix
import io.stoorx.katrix.PackedMatrix

interface RowView<T> : Iterable<T> {
    val matrix: Matrix<T>
}

interface MutableRowView<T> : RowView<T> {
    override val matrix: MutableMatrix<T>
}

class PackedRowView<T>(
    override val matrix: PackedMatrix<T>,
    val rowIndex: Int
) : MutableRowView<T> {

    override fun iterator(): Iterator<T> = PackedColumnViewIterator()

    inner class PackedColumnViewIterator : Iterator<T> {
        var currentColumn: Int = 0

        override fun hasNext(): Boolean =
            currentColumn < this@PackedRowView.matrix.size.columns

        override fun next(): T = this@PackedRowView.matrix[rowIndex, currentColumn++]

    }
}