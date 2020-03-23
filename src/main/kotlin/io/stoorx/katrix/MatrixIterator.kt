package io.stoorx.katrix

interface MatrixIterator<T> : Iterator<T> {
    val matrix: Matrix<T>
}