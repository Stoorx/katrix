package io.stoorx.katrix

data class MatrixSize(
    val rows: Int,
    val columns: Int
) : Iterable<MatrixIndex> {
    fun elementsCount() = rows * columns
    fun transposed() = MatrixSize(columns, rows)

    override fun equals(other: Any?): Boolean =
        if (this === other) true
        else (other as? MatrixSize)?.let {
            this.rows == it.rows && this.columns == it.columns
        } ?: false

    override fun hashCode(): Int = 31 * rows + columns
    override fun iterator(): Iterator<MatrixIndex> = MatrixSizeIterator()

    inner class MatrixSizeIterator() : Iterator<MatrixIndex> {
        var current: MatrixIndex = MatrixIndex(0, 0)

        override fun hasNext(): Boolean =
            current.row < this@MatrixSize.rows && current.column < this@MatrixSize.columns

        override fun next(): MatrixIndex =
            current.also {
                current = MatrixIndex(
                    if (current.column < this@MatrixSize.columns - 1) current.row
                    else current.row + 1,
                    if (current.column < this@MatrixSize.columns - 1) current.column + 1
                    else 0
                )
            }
    }
}