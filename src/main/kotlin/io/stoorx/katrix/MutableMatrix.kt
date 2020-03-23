package io.stoorx.katrix

interface MutableMatrix<T> : Matrix<T> {
    operator fun set(row: Int, column: Int, element: T)
    operator fun set(index: MatrixIndex, element: T)

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