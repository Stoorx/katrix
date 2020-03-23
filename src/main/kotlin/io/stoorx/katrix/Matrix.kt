package io.stoorx.katrix

import io.stoorx.katrix.views.ColumnView
import io.stoorx.katrix.views.MatrixView
import io.stoorx.katrix.views.RowView

interface Matrix<T> : Iterable<T> {
    /**
     * Size of matrix
     */
    val size: MatrixSize

    /**
     * Get slice if matrix
     * @param upperLeft Upper-left index of slice
     * @param lowerRight Lower-right index of slice
     * @param step Step of slicing
     * @return New matrix with is slice of this matrix
     */
    fun slice(
        upperLeft: MatrixIndex = MatrixIndex(0, 0),
        lowerRight: MatrixIndex = MatrixIndex(this.size.rows, this.size.columns),
        step: MatrixIndex = MatrixIndex(1, 1)
    ): Matrix<T>

    /**
     * Get element of matrix
     * @param row Row-index of element
     * @param column Column-index of element
     * @return Element of matrix
     */
    operator fun get(row: Int, column: Int): T

    /**
     * Get element of matrix
     * @param index Index of element
     * @return Element of matrix
     */
    operator fun get(index: MatrixIndex): T

    /**
     * Transpose matrix
     * @return New transposed matrix
     */
    fun transposed(): Matrix<T>

    /**
     * Get slice if matrix
     * @param upperLeft Upper-left index of slice
     * @param lowerRight Lower-right index of slice
     * @param step Step of slicing
     * @return New matrix with is slice of this matrix
     */
    fun view(
        upperLeft: MatrixIndex,
        lowerRight: MatrixIndex,
        step: MatrixIndex = MatrixIndex(1, 1)
    ): MatrixView<T>

    fun rowView(row: Int): RowView<T>
    fun columnView(column: Int): ColumnView<T>

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
    override fun toString(): String

    fun toStringRich(elementSeparator: String = ", ", lineSeparator: String = "\n"): String =
        this.foldIndexed(StringBuilder()) { index, sb, e ->
            when {
                index % this.size.columns != this.size.lastColumnIndex -> sb.append(e).append(elementSeparator)
                index != this.size.elementsCount() - 1 -> sb.append(e).append(lineSeparator)
                else -> sb.append(e)
            }
        }.toString()
}