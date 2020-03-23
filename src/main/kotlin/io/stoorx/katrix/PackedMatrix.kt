package io.stoorx.katrix

class PackedMatrix<T> : MutableMatrix<T> {
    constructor(rows: Int, columns: Int, init: (index: MatrixIndex) -> T) : this(MatrixSize(rows, columns), init)
    constructor(size: MatrixSize, init: (index: MatrixIndex) -> T) {
        this._size = size
        this._data = ArrayList(size.elementsCount())
        size.forEach { mi -> _data.add(init(mi)) }
    }

    private val _data: MutableList<T>
    private val _size: MatrixSize
    private var _transposed: Boolean = false

    override fun set(row: Int, column: Int, element: T) {
        _data[
                linearIndex(
                    if (_transposed) MatrixIndex(row, column)
                    else MatrixIndex(column, row)
                )
        ] = element
    }

    override fun set(index: MatrixIndex, element: T) {
        _data[linearIndex(
            if (_transposed) index.transposed()
            else index
        )] = element
    }

    override fun transpose() {
        _transposed = !_transposed
    }

    override fun slice(upperLeft: MatrixIndex, lowerRight: MatrixIndex, step: MatrixIndex): MutableMatrix<T> =
        TODO()

    override val size: MatrixSize
        get() = _size

    override fun get(row: Int, column: Int): T = this[MatrixIndex(row, column)]

    override fun get(index: MatrixIndex): T =
        _data[linearIndex(
            if (_transposed) index.transposed()
            else index
        )]

    override fun transposed(): MutableMatrix<T> =
        PackedMatrix(size.transposed()) { this[it.transposed()] }

    override fun equals(other: Any?): Boolean {
        TODO()
    }

    override fun hashCode(): Int =
        _data.fold(0) { acc, t ->
            31 * acc + t.hashCode()
        }

    override fun iterator(): MatrixIterator<T> = PackedMatrixIterator()

    override fun toString(): String = this.toStringRich()

    override fun view(upperLeft: MatrixIndex, lowerRight: MatrixIndex, step: MatrixIndex): MutableMatrixView<T> {
        TODO("not implemented")
    }

    private fun linearIndex(matrixIndex: MatrixIndex): Int =
        matrixIndex.row * size.columns + matrixIndex.column

    private inner class PackedMatrixIterator<T>() : MatrixIterator<T> {
        private var currentIndex: MatrixIndex = MatrixIndex(0, 0)

        @Suppress("UNCHECKED_CAST")
        override val matrix: Matrix<T>
            get() = this@PackedMatrix as Matrix<T>

        override fun hasNext(): Boolean =
            currentIndex.column < this@PackedMatrix.size.columns && currentIndex.row < this@PackedMatrix.size.columns

        @Suppress("UNCHECKED_CAST")
        override fun next(): T =
            (this@PackedMatrix[currentIndex] as T)
                .also {
                    currentIndex = MatrixIndex(
                        if (currentIndex.column < this@PackedMatrix.size.columns - 1) currentIndex.row
                        else currentIndex.row + 1,
                        if (currentIndex.column < this@PackedMatrix.size.columns - 1) currentIndex.column + 1
                        else 0
                    )
                }
    }
}