package io.stoorx.katrix.views

import io.stoorx.katrix.Matrix
import io.stoorx.katrix.MutableMatrix
import io.stoorx.katrix.PackedMatrix

interface ColumnView<T> : Iterable<T> {
    val matrix: Matrix<T>
}

interface MutableColumnView<T> : ColumnView<T> {
    override val matrix: MutableMatrix<T>
}

class PackedColumnView<T>(
    override val matrix: PackedMatrix<T>,
    val columnIndex: Int
) : MutableColumnView<T> {
    override fun iterator(): Iterator<T> = PackedColumnViewIterator()

    inner class PackedColumnViewIterator : Iterator<T> {
        var currentRow: Int = 0

        override fun hasNext(): Boolean =
            currentRow < this@PackedColumnView.matrix.size.rows

        override fun next(): T = this@PackedColumnView.matrix[currentRow++, columnIndex]

    }
}