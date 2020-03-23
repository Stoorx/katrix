package io.stoorx.katrix

data class MatrixSize(
    val rows: Int,
    val columns: Int
) : Iterable<MatrixIndex> {
    val lastRowIndex: Int
        get() = rows - 1

    val lastColumnIndex: Int
        get() = columns - 1

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
        private var current: MatrixIndex = MatrixIndex(0, 0)

        override fun hasNext(): Boolean =
            current.row < this@MatrixSize.rows && current.column < this@MatrixSize.columns

        override fun next(): MatrixIndex =
            current.also {
                current = MatrixIndex(
                    if (current.column < this@MatrixSize.lastColumnIndex) current.row
                    else current.row + 1,
                    if (current.column < this@MatrixSize.lastColumnIndex) current.column + 1
                    else 0
                )
            }
    }
}