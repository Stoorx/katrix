package io.stoorx.katrix

data class MatrixIndex(
    val row: Int,
    val column: Int
) {
    fun transposed() = MatrixIndex(column, row)

    override fun equals(other: Any?): Boolean =
        if (this === other) true
        else (other as? MatrixIndex)?.let {
            this.row == it.row && this.column == it.column
        } ?: false

    override fun hashCode(): Int = 31 * row + column
}
