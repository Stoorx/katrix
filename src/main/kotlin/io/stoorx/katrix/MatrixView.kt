package io.stoorx.katrix

interface MatrixView<T> : Matrix<T> {

}

interface MutableMatrixView<T> : MatrixView<T>, MutableMatrix<T> {

}